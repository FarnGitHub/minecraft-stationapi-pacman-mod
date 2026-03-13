package farn.pacman_arcade.block;

import farn.pacman_arcade.PacmanMain;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

public interface PacmanArcade {

    default void openArcadeScreen(PlayerEntity user) {
        PacketHelper.sendTo(user, new MessagePacket(PacmanMain.NAMESPACE.id("arcade_open")));
    }

}
