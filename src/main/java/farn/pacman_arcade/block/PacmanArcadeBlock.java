package farn.pacman_arcade.block;

import farn.pacman_arcade.PacManTextureListener;
import farn.pacman_arcade.PacmanMain;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import farn.pacman_arcade.block.material.ArcadeMaterial;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.item.ItemPlacementContext;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import java.util.List;

public class PacmanArcadeBlock extends TemplateBlockWithEntity implements PacmanArcade {
    public PacmanArcadeBlock(Identifier id) {
        super(id, 0, ArcadeMaterial.ARCADE);
        setHardness(3.0F);
        setTranslationKey(PacmanMain.NAMESPACE, "pacman_arcade");
        disableAutoItemRegistration();
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity entity) {
        int meta = MathHelper.floor((double)(entity.yaw * 4.0f / 360.0f) + 0.5) & 3;
        world.setBlockMeta(x, y, z, meta);
        if(entity instanceof PlayerEntity player && world.getBlockEntity(x, y, z) instanceof PacManBlockEntity pacman) {
            pacman.placer = player.name;
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int meta) {
        if (world.getBlockId(x, y + 1, z) != PacmanMain.arcadeTop.id) {
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        super.onPlaced(world, x, y, z);
        BlockState newState = PacmanMain.arcadeTop.getDefaultState().with(Properties.HORIZONTAL_FACING, world.getBlockState(x,y,z).get(Properties.HORIZONTAL_FACING));
        world.setBlockStateWithNotify(x, y + 1, z, newState);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new PacManBlockEntity();
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z) {
        return super.canPlaceAt(world, x, y, z) && super.canPlaceAt(world, x, y + 1, z) && y + 1 < world.getTopY();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getTexture(int side, int meta) {
        if (side == 0 || side == 1) {
            return PacManTextureListener.bottomTexture;
        }
        return PacManTextureListener.textures[meta * 4 + (side - 2)];
    }

    @Override
    public List<ItemStack> getDropList(World world, int x, int y, int z, BlockState state, int meta) {
        return List.of(new ItemStack(PacmanMain.arcadeBlockItem));
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if(!world.isRemote && world.getBlockEntity(x, y, z) instanceof PacManBlockEntity pacman) {
            if(pacman.placer.equals(player.name) && player.isSneaking()) {
                int coinsLeft = pacman.coins;
                if(coinsLeft > 64) {
                    int coinsStacks = pacman.coins / 64;
                    coinsLeft = pacman.coins % 64;
                    for(int i = 0; i < coinsStacks; i++)
                        player.inventory.addStack(new ItemStack(PacmanMain.coins, 64));
                }
                if(coinsLeft > 0) {
                    player.inventory.addStack(new ItemStack(PacmanMain.coins, coinsLeft));
                }
                pacman.coins = 0;
                return true;
            } else {
                return pacman.openArcade(player);
            }
        }
        return false;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction direction = context.getHorizontalPlayerFacing().rotateClockwise(Direction.Axis.Y);

        return getDefaultState().with(Properties.HORIZONTAL_FACING, direction);
    }

    public void onBreak(World world, int x, int y, int z) {
        if(!world.isRemote && world.getBlockEntity(x,y,z) instanceof PacManBlockEntity pacman && pacman.coins > 0) {
            int coinsLeft = pacman.coins;
            if(coinsLeft > 64) {
                int coinsStacks = pacman.coins / 64;
                coinsLeft = pacman.coins % 64;
                for(int i = 0; i < coinsStacks; i++)
                    this.dropStack(world,x,y,z, new ItemStack(PacmanMain.coins, 64));
            }
            if(coinsLeft > 0)
                this.dropStack(world,x,y,z, new ItemStack(PacmanMain.coins, coinsLeft));
        }
        super.onBreak(world, x, y, z);
    }
}
