package farn.pacman_arcade.game.gui;

import farn.pacman_arcade.game.*;
import farn.pacman_arcade.game.entity.Ghost;
import farn.pacman_arcade.game.entity.Pacman;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class PacmanArcadeGUI extends Screen {
    public Splash splash;
    public int currentScreen;
    public int highScore;
    public int round;
    public int currentScore;
    public int lives = 3;
    public int prevFlashyScore = 100;
    public int toRender;
    public int dotsLeft;
    public int upKey = 17;
    public int leftKey = 30;
    public int downKey = 31;
    public int rightKey = 32;
    public MenuButton start;
    public boolean started;
    public boolean energized;
    public boolean render;
    public boolean editingUp;
    public boolean editingLeft;
    public boolean editingDown;
    public boolean editingRight;
    public long tick;
    public long energizerTick;
    public long renderTick;
    public PlayerEntity player;
    public String[] board = new String[]{"n3111111111111111116n", "n2000000000000000002n", "n2e110111010111011e2n", "n2000000000000000002n", "n2011020117110201102n", "n2000020002000200002n", "n51160t1102011903114n", "nnnn2020000000202nnnn", "1111402031g1602051111", "p00000002sss20000000p", "111160205111402031111", "nnnn202000y000202nnnn", "n3114020117110205116n", "n2000000002000000002n", "n2016011102011103102n", "n2e020000000000020e2n", "nt102020117110202019n", "n2000020002000200002n", "n2011181102011811102n", "n2000000000000000002n", "n5111111111111111114n"};
    public GameMap theMap;
    public Pacman thePacman;
    public Ghost pinky;
    public Ghost inky;
    public Ghost clyde;
    public Ghost blinky;
    public Animation animation = new Animation();

    public PacmanArcadeGUI(PlayerEntity player) {
        this.splash = new Splash("/assets/pacman_arcade/pacman_textures/mojang.png", "/assets/pacman_arcade/pacman_textures/namco.png");
        this.theMap = new GameMap(this.board, this);
        this.thePacman = new Pacman(this.board, 0, 8);
        this.thePacman.setDirection(4);
        this.pinky = new Ghost(this.board, 0, -8, -8);
        this.inky = new Ghost(this.board, 1, 0, -8);
        this.clyde = new Ghost(this.board, 2, 8, -8);
        this.blinky = new Ghost(this.board, 3, 0, -24);
        this.player = player;
    }

    public boolean shouldPause() {
        return false;
    }

    public void addButtons(int screen) {
        if (screen == 1) {
            this.buttons.clear();
            this.start = new MenuButton(0, this.width / 2 - 28, this.height / 2, 56, 16, "");
            this.buttons.add(this.start);
            this.buttons.add(new MenuButton(1, this.width / 2 - 44, this.height / 2 + 20, 88, 16, ""));
            this.buttons.add(new MenuButton(2, this.width / 2 - 36, this.height / 2 + 40, 72, 16, ""));
        } else if (screen == 2) {
            this.buttons.clear();
            this.buttons.add(new ButtonWidget(7, this.width / 2 - 15, this.height / 2 + 90, 30, 10, "Menu"));
        } else if (screen == 3) {
            this.buttons.clear();
            this.buttons.add(new ButtonWidget(3, this.width / 2 - 70, this.height / 2 - 50, 50, 20, !this.editingUp ? Keyboard.getKeyName((int)this.upKey) : "???"));
            this.buttons.add(new ButtonWidget(4, this.width / 2 - 70, this.height / 2 - 20, 50, 20, !this.editingLeft ? Keyboard.getKeyName((int)this.leftKey) : "???"));
            this.buttons.add(new ButtonWidget(5, this.width / 2 - 70, this.height / 2 + 10, 50, 20, !this.editingDown ? Keyboard.getKeyName((int)this.downKey) : "???"));
            this.buttons.add(new ButtonWidget(6, this.width / 2 - 70, this.height / 2 + 40, 50, 20, !this.editingRight ? Keyboard.getKeyName((int)this.rightKey) : "???"));
            this.buttons.add(new ButtonWidget(7, this.width / 2 - 80, this.height / 2 + 77, 50, 20, "Menu"));
        } else if (screen == 4) {
            this.buttons.clear();
            this.buttons.add(new ButtonWidget(7, this.width / 2 - 80, this.height / 2 + 77, 50, 20, "Menu"));
        } else if (screen == 5) {
            this.buttons.clear();
            this.buttons.add(new MenuButton(0, this.width / 2 - 28, this.height / 2 + 50, 56, 16, ""));
            this.buttons.add(new ButtonWidget(7, this.width / 2 - 80, this.height / 2 + 77, 50, 20, "Menu"));
        } else {
            this.buttons.clear();
        }
    }

    @Override
    public void init() {
        if (this.currentScreen == 1) {
            this.addButtons(1);
        }
    }

    @Override
    public void buttonClicked(ButtonWidget gb) {
        if (gb.id == 0) {
            this.currentScreen = 2;
            this.addButtons(2);
            this.pinky.setX(-8);
            this.pinky.setY(-8);
            this.pinky.setMode(0);
            this.inky.setX(0);
            this.inky.setY(-8);
            this.inky.setMode(0);
            this.clyde.setX(8);
            this.clyde.setY(-8);
            this.clyde.setMode(0);
            this.blinky.setX(0);
            this.blinky.setY(-24);
            this.blinky.setMode(0);
            this.thePacman.dead = false;
            this.thePacman.setX(0);
            this.thePacman.setY(8);
            this.thePacman.setDirection(4);
            this.currentScore = 0;
            this.lives = 3;
            this.started = false;
            this.energized = false;
            this.energizerTick = 0L;
            this.tick = 0L;
            this.thePacman.prevEnergizerScore = 100;
            this.prevFlashyScore = 100;
            this.theMap.resetDots();
            this.round = 1;
            this.minecraft.soundManager.playSound("pacman_arcade:pacman.intro", 1.0f, 1.0f);
        }
        if (gb.id == 1) {
            this.currentScreen = 3;
            this.addButtons(3);
        }
        if (gb.id == 2) {
            this.currentScreen = 4;
            this.addButtons(4);
        }
        if (gb.id == 3) {
            this.editingUp = true;
            this.addButtons(3);
        }
        if (gb.id == 4) {
            this.editingLeft = true;
            this.addButtons(3);
        }
        if (gb.id == 5) {
            this.editingDown = true;
            this.addButtons(3);
        }
        if (gb.id == 6) {
            this.editingRight = true;
            this.addButtons(3);
        }
        if (gb.id == 7) {
            this.currentScreen = 1;
            this.addButtons(1);
        }
    }


    @Override
    public void keyPressed(char c, int i) {
        super.keyPressed(c, i);
        if (this.editingUp) {
            this.upKey = i;
            this.editingUp = false;
            this.addButtons(3);
        }
        if (this.editingLeft) {
            this.leftKey = i;
            this.editingLeft = false;
            this.addButtons(3);
        }
        if (this.editingDown) {
            this.downKey = i;
            this.editingDown = false;
            this.addButtons(3);
        }
        if (this.editingRight) {
            this.rightKey = i;
            this.editingRight = false;
            this.addButtons(3);
        }
    }

    public void updateKeys() {
        if (Keyboard.isKeyDown((int)this.upKey) && this.thePacman.canMove(3) && !this.thePacman.getDead()) {
            this.thePacman.setMoving(3);
        }
        if (Keyboard.isKeyDown((int)this.leftKey) && this.thePacman.canMove(2) && !this.thePacman.getDead()) {
            this.thePacman.setMoving(2);
        }
        if (Keyboard.isKeyDown((int)this.downKey) && this.thePacman.canMove(1) && !this.thePacman.getDead()) {
            this.thePacman.setMoving(1);
        }
        if (Keyboard.isKeyDown((int)this.rightKey) && this.thePacman.canMove(0) && !this.thePacman.getDead()) {
            this.thePacman.setMoving(0);
        }
    }

    @Override
    public void tick() {
        if (this.currentScreen == 1) {
            this.animation.updateMenuAnimation();
        }
        if (this.currentScreen == 2) {
            if (this.tick % 8L == 0L && this.started && this.currentScreen == 2 && this.pinky.getMode() == 0 && this.inky.getMode() == 0 && this.clyde.getMode() == 0 && this.blinky.getMode() == 0) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.ghost", 1.0f, 1.0f);
            }
            if (this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.pinky.getMode() == 1 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.inky.getMode() == 1 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.clyde.getMode() == 1 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.blinky.getMode() == 1) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.ghostflash", 1.0f, 1.0f);
            }
            if (this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.pinky.getMode() == 2 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.inky.getMode() == 2 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.clyde.getMode() == 2 || this.tick % 6L == 0L && this.started && this.currentScreen == 2 && this.blinky.getMode() == 2) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.ghostdead", 1.0f, 1.0f);
            }
            if (this.dotsLeft == 0) {
                ++this.round;
                this.theMap.resetDots();
                this.pinky.setX(-8);
                this.pinky.setY(-8);
                this.pinky.setMode(0);
                this.inky.setX(0);
                this.inky.setY(-8);
                this.inky.setMode(0);
                this.clyde.setX(8);
                this.clyde.setY(-8);
                this.clyde.setMode(0);
                this.blinky.setX(0);
                this.blinky.setY(-24);
                this.blinky.setMode(0);
                this.thePacman.dead = false;
                this.thePacman.setX(0);
                this.thePacman.setY(8);
                this.thePacman.setDirection(4);
                this.thePacman.prevEnergizerScore = 100;
                this.prevFlashyScore = 100;
            }
            if (this.render) {
                if (this.renderTick < 30L) {
                    ++this.renderTick;
                } else {
                    this.renderTick = 0L;
                    this.render = false;
                }
            }
            ++this.tick;
            this.thePacman.update(this.minecraft, this, this.theMap);
            this.pinky.update(this.minecraft, this, this.theMap);
            this.inky.update(this.minecraft, this, this.theMap);
            this.clyde.update(this.minecraft, this, this.theMap);
            this.blinky.update(this.minecraft, this, this.theMap);
            if ((this.thePacman.boundingBox.intersects(this.pinky.boundingBox) && this.pinky.mode == 0 || this.thePacman.boundingBox.intersects(this.inky.boundingBox) && this.inky.mode == 0 || this.thePacman.boundingBox.intersects(this.clyde.boundingBox) && this.clyde.mode == 0 || this.thePacman.boundingBox.intersects(this.blinky.boundingBox) && this.blinky.mode == 0) && !this.thePacman.getDead()) {
                this.thePacman.setDead(this.minecraft);
                this.thePacman.setMoving(5);
            }
            if (this.thePacman.boundingBox.intersects(this.pinky.boundingBox) && this.pinky.mode == 1) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.eatghost", 1.0f, 1.0f);
                this.pinky.setMode(2);
                this.prevFlashyScore = this.prevFlashyScore != 1600 ? (this.prevFlashyScore *= 2) : 200;
                this.currentScore += this.prevFlashyScore;
                this.render = true;
                this.toRender = 0;
            }
            if (this.thePacman.boundingBox.intersects(this.inky.boundingBox) && this.inky.mode == 1) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.eatghost", 1.0f, 1.0f);
                this.inky.setMode(2);
                this.prevFlashyScore = this.prevFlashyScore != 1600 ? (this.prevFlashyScore *= 2) : 200;
                this.currentScore += this.prevFlashyScore;
                this.render = true;
                this.toRender = 1;
            }
            if (this.thePacman.boundingBox.intersects(this.clyde.boundingBox) && this.clyde.mode == 1) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.eatghost", 1.0f, 1.0f);
                this.clyde.setMode(2);
                this.prevFlashyScore = this.prevFlashyScore != 1600 ? (this.prevFlashyScore *= 2) : 200;
                this.currentScore += this.prevFlashyScore;
                this.render = true;
                this.toRender = 2;
            }
            if (this.thePacman.boundingBox.intersects(this.blinky.boundingBox) && this.blinky.mode == 1) {
                this.minecraft.soundManager.playSound("pacman_arcade:pacman.eatghost", 1.0f, 1.0f);
                this.blinky.setMode(2);
                this.prevFlashyScore = this.prevFlashyScore != 1600 ? (this.prevFlashyScore *= 2) : 200;
                this.currentScore += this.prevFlashyScore;
                this.render = true;
                this.toRender = 3;
            }
            if (this.thePacman.getDead()) {
                if (this.thePacman.animation.deathTick == 30 && this.lives > 0) {
                    --this.lives;
                    this.pinky.setX(-8);
                    this.pinky.setY(-8);
                    this.pinky.setMode(0);
                    this.inky.setX(0);
                    this.inky.setY(-8);
                    this.inky.setMode(0);
                    this.clyde.setX(8);
                    this.clyde.setY(-8);
                    this.clyde.setMode(0);
                    this.blinky.setX(0);
                    this.blinky.setY(-24);
                    this.blinky.setMode(0);
                    this.thePacman.dead = false;
                    this.thePacman.setX(0);
                    this.thePacman.setY(8);
                    this.thePacman.setDirection(4);
                    this.thePacman.prevEnergizerScore = 100;
                    this.prevFlashyScore = 100;
                }
                if (this.lives == 0) {
                    this.currentScreen = 5;
                    this.addButtons(5);
                }
            }
            if (this.currentScore > this.highScore) {
                this.highScore = this.currentScore;
            }
            if (this.energized) {
                ++this.energizerTick;
            }
            if (this.energizerTick == 250L) {
                this.energized = false;
                this.energizerTick = 0L;
                this.pinky.setMode(0);
                this.inky.setMode(0);
                this.clyde.setMode(0);
                this.blinky.setMode(0);
            }
        }
        this.updateKeys();
        this.splash.update();
    }

    @Override
    public void render(int i, int j, float f) {
        if (this.currentScreen == 0) {
            this.drawSplash();
        }
        if (this.currentScreen == 1) {
            this.drawMenu(i, j, f);
        }
        if (this.currentScreen == 2) {
            this.drawGame(i, j, f);
        }
        if (this.currentScreen == 3) {
            this.drawOptions(i, j, f);
        }
        if (this.currentScreen == 4) {
            this.drawAbout(i, j, f);
        }
        if (this.currentScreen == 5) {
            this.drawDeath(i, j, f, this.currentScore);
        }
    }

    public void drawSplash() {
        if (!this.splash.done) {
            this.drawBG(0, 0, false);
            this.splash.render(this.minecraft, this);
        } else {
            this.currentScreen = 1;
            this.addButtons(1);
        }
    }

    public void drawMenu(int i, int j, float f) {
        this.drawBG(0, 0, true);
        this.fill(this.width / 2 - 64, this.height / 2 - 80, this.width / 2 + 64, this.height / 2 + 80, -16777216);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/menu.png"));
        this.drawTexture(this.width / 2 - 56, this.height / 2 - 75, 0, 0, 112, 32);
        super.render(i, j, f);
        GL11.glDisable((int)3042);
        this.animation.animateMenu(this.minecraft, this, 0, -25);
    }

    public void drawGame(int i, int j, float f) {
        this.drawBG(0, 0, true);
        this.drawCenteredTextWithShadow(this.textRenderer, "High: " + this.highScore, this.width / 2 + 40, this.height / 2 - 95, 0xFFFFFF);
        this.drawCenteredTextWithShadow(this.textRenderer, "Round: " + this.round, this.width / 2 + 40, this.height / 2 + 87, 0xFFFFFF);
        this.drawCenteredTextWithShadow(this.textRenderer, "Current: " + this.currentScore, this.width / 2 - 40, this.height / 2 - 95, 0xFFFFFF);
        this.theMap.render(this.minecraft, this, 0, 0);
        this.drawLives(-50, 95);
        this.pinky.render(this.minecraft, this);
        this.inky.render(this.minecraft, this);
        this.clyde.render(this.minecraft, this);
        this.blinky.render(this.minecraft, this);
        this.thePacman.render(this.minecraft, this);
        if (!this.started) {
            this.drawCountdown();
        }
        if (this.render) {
            if (this.toRender == 0) {
                this.drawTextWithShadow(this.textRenderer, "" + this.prevFlashyScore, this.width / 2 + this.pinky.currentX, this.height / 2 + this.pinky.currentY, 0xFFFFFF);
            }
            if (this.toRender == 1) {
                this.drawTextWithShadow(this.textRenderer, "" + this.prevFlashyScore, this.width / 2 + this.inky.currentX, this.height / 2 + this.inky.currentY, 0xFFFFFF);
            }
            if (this.toRender == 2) {
                this.drawTextWithShadow(this.textRenderer, "" + this.prevFlashyScore, this.width / 2 + this.clyde.currentX, this.height / 2 + this.clyde.currentY, 0xFFFFFF);
            }
            if (this.toRender == 3) {
                this.drawTextWithShadow(this.textRenderer, "" + this.prevFlashyScore, this.width / 2 + this.blinky.currentX, this.height / 2 + this.blinky.currentY, 0xFFFFFF);
            }
        }
        super.render(i, j, f);
    }

    public void drawOptions(int i, int j, float f) {
        this.drawBG(0, 0, true);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/menu.png"));
        this.drawTexture(this.width / 2 - 44, this.height / 2 - 85, 56, 32, 88, 16);
        this.drawTextWithShadow(this.textRenderer, "Move Pacman Up", this.width / 2 - 15, this.height / 2 - 45, 0xFFFFFF);
        this.drawTextWithShadow(this.textRenderer, "Move Pacman Left", this.width / 2 - 15, this.height / 2 - 15, 0xFFFFFF);
        this.drawTextWithShadow(this.textRenderer, "Move Pacman Down", this.width / 2 - 15, this.height / 2 + 15, 0xFFFFFF);
        this.drawTextWithShadow(this.textRenderer, "Move Pacman Right", this.width / 2 - 15, this.height / 2 + 45, 0xFFFFFF);
        GL11.glDisable((int)3042);
        super.render(i, j, f);
    }

    public void drawAbout(int i, int j, float f) {
        this.drawBG(0, 0, true);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/menu.png"));
        this.drawTexture(this.width / 2 - 36, this.height / 2 - 85, 112, 0, 72, 16);
        GL11.glDisable((int)3042);
        this.drawCenteredTextWithShadow(this.textRenderer, "Pacman Arcade v0.32", this.width / 2, this.height / 2 - 50, 0xFF0000);
        this.drawCenteredTextWithShadow(this.textRenderer, "by Formulayeti", this.width / 2, this.height / 2 - 40, 65280);
        this.drawCenteredTextWithShadow(this.textRenderer, "Uses 1833+ lines of code", this.width / 2, this.height / 2 - 20, 0xFFFF00);
        this.drawCenteredTextWithShadow(this.textRenderer, "Took over 10 man-hours", this.width / 2, this.height / 2 - 10, 0xFF0000);
        this.drawCenteredTextWithShadow(this.textRenderer, "Uses 19 classes", this.width / 2, this.height / 2, 0xFFFF00);
        this.drawCenteredTextWithShadow(this.textRenderer, "Uses 11 image files", this.width / 2, this.height / 2 + 10, 0xFF0000);
        this.drawCenteredTextWithShadow(this.textRenderer, "Uses 7 sound files", this.width / 2, this.height / 2 + 20, 0xFFFF00);
        super.render(i, j, f);
    }

    public void drawDeath(int i, int j, float f, int finalScore) {
        this.drawBG(0, 0, true);
        this.drawCenteredTextWithShadow(this.textRenderer, "GameOver", this.width / 2, this.height / 2 - 20, 0xFF0000);
        this.drawCenteredTextWithShadow(this.textRenderer, "Your score was: " + finalScore, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
        this.drawCenteredTextWithShadow(this.textRenderer, "again", this.width / 2, this.height / 2 + 65, 0xFFFFFF);
        GL11.glEnable((int)3042);
        super.render(i, j, f);
        GL11.glDisable((int)3042);
    }

    public void drawBG(int x, int y, boolean black) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)this.minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/bg.png"));
        this.drawTexture(this.width / 2 - 100 + x, this.height / 2 - 116 + y, 0, 0, 200, 232);
        GL11.glDisable((int)3042);
        if (black) {
            this.fill(this.width / 2 - 84 + x, this.height / 2 - 100 + y, this.width / 2 + 84 + x, this.height / 2 + 100 + y, -16777216);
        }
    }

    public void drawLives(int x, int y) {
        for (int i = 0; i < this.lives; ++i) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBindTexture((int)3553, (int)this.minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/sprites.png"));
            this.drawTexture(this.width / 2 - 8 + x + i * 10, this.height / 2 - 8 + y, 32, 0, 8, 8);
            GL11.glDisable((int)3042);
        }
    }

    public void drawCountdown() {
        if (this.tick < 90L) {
            this.drawCenteredTextWithShadow(this.textRenderer, "Get Ready!", this.width / 2, this.height / 2, 0xFFFFFF);
        } else {
            this.started = true;
        }
    }
}
