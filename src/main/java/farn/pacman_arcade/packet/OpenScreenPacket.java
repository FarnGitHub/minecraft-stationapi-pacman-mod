package farn.pacman_arcade.packet;

import farn.pacman_arcade.game.gui.PacmanArcadeGUI;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;

public class OpenScreenPacket {

    public static void run() {
        Minecraft.INSTANCE.setScreen(new PacmanArcadeGUI(PlayerHelper.getPlayerFromGame()));
    }
}
