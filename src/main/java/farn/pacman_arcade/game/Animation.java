package farn.pacman_arcade.game;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.opengl.GL11;

public class Animation {
    public int pacmanTick;
    public int ghostTick;
    public int deathTick;
    public int menuTick;

    public void updateDeathAnimation() {
        if (this.deathTick < 30)
            ++this.deathTick;
    }

    public void updatePacmanAnimation() {
        if (this.pacmanTick < 10)
            ++this.pacmanTick;
        else
            this.pacmanTick = 0;
    }

    public void updateGhostAnimation() {
        if (this.ghostTick < 10)
            ++this.ghostTick;
        else
            this.ghostTick = 0;
    }

    public void updateMenuAnimation() {
        if (this.menuTick < 864)
            ++this.menuTick;
        else
            this.menuTick = 0;
    }

    public void animateFlashingGhost(Minecraft mc, Screen gui, int direction, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, direction * 16 + this.pacmanTick / 5 * 8, 48 + this.ghostTick / 5 * 8, 8, 8);
        GL11.glDisable(3042);
    }

    public void animateDeadGhost(Minecraft mc, Screen gui, int direction, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, direction * 16 + this.pacmanTick / 5 * 8, 64, 8, 8);
        GL11.glDisable(3042);
    }

    public void animateEnergizedGhost(Minecraft mc, Screen gui, int direction, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, direction * 16 + this.pacmanTick / 5 * 8, 48, 8, 8);
        GL11.glDisable(3042);
    }

    public void animateGhost(Minecraft mc, Screen gui, int type, int direction, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, direction * 16 + this.pacmanTick / 5 * 8, (type + 1) * 8, 8, 8);
        GL11.glDisable(3042);
    }

    public void pacmanDeathAnimation(Minecraft mc, Screen gui, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, this.deathTick / 3 * 8, 40, 8, 8);
        GL11.glDisable(3042);
    }

    public void animateMenu(Minecraft mc, Screen gui, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/animation.png"));
        gui.drawTexture(gui.width / 2 - 84 + x, gui.height / 2 - 4 + y, 0, this.menuTick / 4 * 8, 168, 8);
        GL11.glDisable(3042);
    }

    public void animatePacman(Minecraft mc, Screen gui, int direction, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glBindTexture(3553, mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
        gui.drawTexture(gui.width / 2 - 4 + x, gui.height / 2 - 4 + y, direction != 4 ? direction * 16 + this.pacmanTick / 5 * 8 : 64, 0, 8, 8);
        GL11.glDisable(3042);
    }
}
