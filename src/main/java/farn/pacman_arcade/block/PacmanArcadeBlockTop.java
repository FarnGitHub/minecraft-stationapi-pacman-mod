package farn.pacman_arcade.block;

import farn.pacman_arcade.PacmanMain;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import farn.pacman_arcade.block.material.ArcadeMaterial;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class PacmanArcadeBlockTop extends TemplateBlock implements PacmanArcade {
    public PacmanArcadeBlockTop(Identifier id, int texture) {
        super(id, texture, ArcadeMaterial.ARCADE);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int meta) {
        if (world.getBlockId(x, y - 1, z) != PacmanMain.pacmanArcadeBottom.id) {
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public List<ItemStack> getDropList(World world, int x, int y, int z, BlockState state, int meta) {
        return List.of(new ItemStack(PacmanMain.pacmanItem));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public void BlockDropStack(World world, int x, int y, int z, ItemStack stack) {
        this.dropStack(world, x, y, z, stack);
    }

    public void onBreak(World world, int x, int y, int z) {
        dropCoins(world, x, y, z);
        super.onBreak(world, x, y, z);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if(world.getBlockId(x, y - 1, z) == PacmanMain.pacmanArcadeBottom.id)
            return PacmanMain.pacmanArcadeBottom.onUse(world, x, y - 1, z, player);
        return false;
    }
}
