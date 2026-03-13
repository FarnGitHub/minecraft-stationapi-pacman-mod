package farn.pacman_arcade.block;

import farn.pacman_arcade.PacManTextureListener;
import farn.pacman_arcade.PacmanMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Random;

public class PacmanArcadeBlock extends TemplateBlock implements PacmanArcade {
    public PacmanArcadeBlock(Identifier id, int texture) {
        super(id, texture, PacmanMain.metalMaterial);
    }

    @Override
    public int getDroppedItemId(int par1, Random par2Random) {
        return PacmanMain.pacmanItem.id;
    }

    @Override
    public void onPlaced(World par1World, int par2, int par3, int par4, LivingEntity par5EntityLiving) {
        int var6 = MathHelper.floor((double)(par5EntityLiving.yaw * 4.0f / 360.0f) + 0.5) & 3;
        par1World.setBlockMeta(par2, par3, par4, var6);
        par1World.setBlockMeta(par2, par3 + 1, par4, var6);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int md) {
        if (world.getBlockId(x, y + 1, z) != PacmanMain.pacmanArcadeTop.id) {
            world.setBlock(x, y, z, 0);
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z) {
        world.setBlock(x, y + 1, z, PacmanMain.pacmanArcadeTop.id);
        StationAPI.LOGGER.info(PacmanMain.pacmanArcadeTop.id);
    }

    @Override
    public boolean canPlaceAt(World par1World, int par2, int par3, int par4) {
        return super.canPlaceAt(par1World, par2, par3, par4) && super.canPlaceAt(par1World, par2, par3 + 1, par4) && par3 + 1 < par1World.getTopY();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getTexture(int side, int md) {
        if (side == 0 || side == 1) {
            return PacManTextureListener.bottomTexture;
        }
        return PacManTextureListener.textures[md * 4 + (side - 2)];
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
