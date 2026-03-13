package farn.pacman_arcade.block;

import farn.pacman_arcade.PacmanMain;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Random;

public class PacmanArcadeBlockTop extends TemplateBlockWithEntity implements PacmanArcade {
    public PacmanArcadeBlockTop(Identifier id, int texture) {
        super(id, texture, PacmanMain.metalMaterial);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int md) {
        if (world.getBlockId(x, y - 1, z) != PacmanMain.pacmanArcadeBottom.id) {
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public int getDroppedItemId(int par1, Random par2Random) {
        return PacmanMain.pacmanItem.id;
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
    public BlockEntity createBlockEntity() {
        return new PacManBlockEntity();
    }

    @Environment(EnvType.CLIENT)
    public int getRenderType() {
        return -1;
    }

    @Override
    public List<ItemStack> getDropList(World world, int x, int y, int z, BlockState state, int meta) {
        return List.of(new ItemStack(PacmanMain.pacmanItem));
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if(!world.isRemote)
            this.openArcadeScreen(player);
        return false;
    }
}
