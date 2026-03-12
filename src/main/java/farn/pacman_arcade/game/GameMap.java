package farn.pacman_arcade.game;

import farn.pacman_arcade.game.gui.PacmanArcadeGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.opengl.GL11;

public class GameMap {
    public String[] board;
    public boolean[][] dots = new boolean[21][21];
    public boolean[][] energizers = new boolean[21][21];
    public PacmanArcadeGUI gui;
    private DrawContextUnlocked context = new DrawContextUnlocked();

    public GameMap(String[] board, PacmanArcadeGUI gui) {
        this.board = board;
        this.gui = gui;
        this.resetDots();
    }

    public void resetDots() {
        int x;
        int y;
        this.gui.dotsLeft = 0;
        for (y = 0; y < this.dots.length; ++y) {
            for (x = 0; x < this.dots[0].length; ++x) {
                if (this.getTileTypeAt(x, y) != 0) continue;
                this.dots[y][x] = true;
                ++this.gui.dotsLeft;
            }
        }
        for (y = 0; y < this.energizers.length; ++y) {
            for (x = 0; x < this.energizers[0].length; ++x) {
                if (this.getTileTypeAt(x, y) != 14) continue;
                this.energizers[y][x] = true;
                ++this.gui.dotsLeft;
            }
        }
    }

    public void setDotTaken(int x, int y) {
        this.dots[y / 8 + 10][x / 8 + 10] = false;
    }

    public boolean getDotTaken(int x, int y) {
        return this.dots[y / 8 + 10][x / 8 + 10];
    }

    public boolean getEnergizerTaken(int x, int y) {
        return this.energizers[y / 8 + 10][x / 8 + 10];
    }

    public void setEnergizerTaken(int x, int y) {
        this.energizers[y / 8 + 10][x / 8 + 10] = false;
    }

    public void render(Minecraft mc, Screen gui, int xOffset, int yOffset) {
        for (int y = 0; y < this.board.length; ++y) {
            for (int x = 0; x < this.board[0].length(); ++x) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBindTexture((int)3553, (int)mc.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/map.png"));
                gui.drawTexture(gui.width / 2 - 84 + x * 8 + xOffset, gui.height / 2 - 84 + y * 8 + yOffset, this.getTileTypeAt(x, y) * 8, 0, 8, 8);
                GL11.glDisable((int)3042);
                if (this.dots[y][x]) {
                    context.fill(gui.width / 2 - 84 + x * 8 + 3 + xOffset, gui.height / 2 - 84 + y * 8 + yOffset + 3, gui.width / 2 - 84 + x * 8 + xOffset + 5, gui.height / 2 - 84 + y * 8 + yOffset + 5, -1);
                }
                if (!this.energizers[y][x]) continue;
                context.fill(gui.width / 2 - 84 + x * 8 + 2 + xOffset, gui.height / 2 - 84 + y * 8 + yOffset + 2, gui.width / 2 - 84 + x * 8 + xOffset + 6, gui.height / 2 - 84 + y * 8 + yOffset + 6, -1);
            }
        }
    }

    public int getTileTypeAt(int x, int y) {
        if (this.board[y].charAt(x) == 't') {
            return 10;
        }
        if (this.board[y].charAt(x) == 'p') {
            return 12;
        }
        if (this.board[y].charAt(x) == 'c') {
            return 11;
        }
        if (this.board[y].charAt(x) == 'g') {
            return 13;
        }
        if (this.board[y].charAt(x) == 'e') {
            return 14;
        }
        if (this.board[y].charAt(x) == 'y') {
            return 15;
        }
        if (this.board[y].charAt(x) == 's') {
            return 16;
        }
        return Character.getNumericValue(this.board[y].charAt(x));
    }

    class DrawContextUnlocked extends DrawContext {
        public void fill(int x1, int y1, int x2, int y2, int color) {
            super.fill(x1, y1, x2, y2, color);
        }

        public void fillGradient(int startX, int startY, int endX, int endY, int colorStart, int colorEnd) {
            super.fillGradient(startX, startY, endX, endY, colorStart, colorEnd);
        }
    }
}
