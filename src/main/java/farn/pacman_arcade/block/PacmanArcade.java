package farn.pacman_arcade.block;

import farn.pacman_arcade.PacmanMain;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

public interface PacmanArcade {

    default void openArcadeScreen(PlayerEntity user) {
        PacketHelper.sendTo(user, new MessagePacket(PacmanMain.NAMESPACE.id("arcade_open")));
    }

    default void dropCoins(World world, int x, int y, int z) {
        if(world.getBlockEntity(x,y,z) instanceof PacManBlockEntity pacman && pacman.coins > 0) {
            int coinsLeft = pacman.coins;
            if(coinsLeft > 64) {
                int coinsStacks = pacman.coins / 64;
                coinsLeft = pacman.coins % 64;
                for(int i = 0; i < coinsStacks; i++)
                    this.BlockDropStack(world,x,y,z, new ItemStack(PacmanMain.pacmanCoins, 64));
            }
            if(coinsLeft > 0)
                this.BlockDropStack(world,x,y,z, new ItemStack(PacmanMain.pacmanCoins, coinsLeft));
        }
    }

    void BlockDropStack(World world, int x, int y, int z, ItemStack stack);
    

}
