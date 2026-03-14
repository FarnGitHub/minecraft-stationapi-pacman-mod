package farn.pacman_arcade.block.entity;

import farn.pacman_arcade.PacmanMain;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

public class PacManBlockEntity extends BlockEntity {
    public int coins = 0;
    public String placer = "";

    public boolean openArcade(PlayerEntity user) {
        if(canInsertCoins(user)) {
            ++coins;
            PacketHelper.sendTo(user, new MessagePacket(PacmanMain.NAMESPACE.id("arcade_open")));
            return true;
        }
        return false;
    }

    public boolean canInsertCoins(PlayerEntity user) {
        return user.inventory.getSelectedItem() != null
                && user.inventory.getSelectedItem().itemId == PacmanMain.coins.id
                && user.inventory.removeStack(user.inventory.selectedSlot, 1) != null;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        coins = nbt.getInt("pacman_coins");
        placer = nbt.getString("placer");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("pacman_coins", coins);
        if(!placer.isEmpty())
             nbt.putString("placer", placer);
    }
}
