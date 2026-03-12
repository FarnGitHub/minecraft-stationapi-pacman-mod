package farn.pacman_arcade.game.entity;

import farn.pacman_arcade.game.Animation;
import farn.pacman_arcade.game.GameMap;
import farn.pacman_arcade.game.gui.PacmanArcadeGUI;
import net.minecraft.client.Minecraft;

public class Pacman
extends Entity {
    public Animation animation = new Animation();
    public int prevEnergizerScore = 100;
    public boolean dead;

    public Pacman(String[] board, int startX, int startY) {
        super(board, startX, startY, 8, 8);
        this.speed = 2;
    }

    public boolean getDead() {
        return this.dead;
    }

    public void setDead(Minecraft mc) {
        this.dead = true;
        mc.soundManager.playSound("pacman_arcade:pacman.death", 1.0f, 1.0f);
    }

    @Override
    public void update(Minecraft mc, PacmanArcadeGUI gui, GameMap theMap) {
        super.update(mc, gui, theMap);
        if (!this.dead) {
            this.animation.updatePacmanAnimation();
            if (gui.tick % 6L == 0L && gui.started && gui.currentScreen == 2 && this.isMoving) {
                mc.soundManager.playSound("pacman_arcade:pacman.eat", 1.0f, 1.0f);
            }
        } else {
            this.animation.updateDeathAnimation();
        }
        if (this.currentTile == 0 && theMap.getDotTaken(this.currentX, this.currentY)) {
            theMap.setDotTaken(this.currentX, this.currentY);
            gui.currentScore += 10;
            --gui.dotsLeft;
        }
        if (this.currentTile == 14 && theMap.getEnergizerTaken(this.currentX, this.currentY)) {
            theMap.setEnergizerTaken(this.currentX, this.currentY);
            gui.currentScore += (this.prevEnergizerScore *= 2);
            gui.energized = true;
            gui.pinky.setMode(1);
            gui.inky.setMode(1);
            gui.clyde.setMode(1);
            gui.blinky.setMode(1);
            gui.energizerTick = 0L;
            --gui.dotsLeft;
        }
    }

    @Override
    public void render(Minecraft mc, PacmanArcadeGUI gui) {
        if (!this.dead) {
            this.animation.deathTick = 0;
            this.animation.animatePacman(mc, gui, this.direction, this.currentX, this.currentY);
        } else {
            this.animation.pacmanDeathAnimation(mc, gui, this.currentX, this.currentY);
        }
    }
}
