package farn.pacman_arcade.game.entity;

import java.awt.Rectangle;
import java.util.Random;

import farn.pacman_arcade.game.GameMap;
import farn.pacman_arcade.game.gui.PacmanArcadeGUI;
import net.minecraft.client.Minecraft;

public class Entity {
    public int startX;
    public int startY;
    public int currentX;
    public int currentY;
    public int width;
    public int height;
    public int currentTile;
    public int direction;
    public int speed;
    public Rectangle boundingBox;
    public String[] board;
    public Random rand = new Random();
    public boolean movingUp;
    public boolean movingLeft;
    public boolean movingDown;
    public boolean movingRight;
    public boolean isMoving;

    public Entity(String[] board, int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(startX, startY, width, height);
        this.currentX = startX;
        this.currentY = startY;
        this.board = board;
    }

    public void setMoving(int direction) {
        if (direction == 3) {
            this.movingUp = true;
            this.movingLeft = false;
            this.movingDown = false;
            this.movingRight = false;
        }
        if (direction == 2) {
            this.movingUp = false;
            this.movingLeft = true;
            this.movingDown = false;
            this.movingRight = false;
        }
        if (direction == 1) {
            this.movingUp = false;
            this.movingLeft = false;
            this.movingDown = true;
            this.movingRight = false;
        }
        if (direction == 0) {
            this.movingUp = false;
            this.movingLeft = false;
            this.movingDown = false;
            this.movingRight = true;
        }
        if (direction == 5) {
            this.movingUp = false;
            this.movingLeft = false;
            this.movingDown = false;
            this.movingRight = false;
        }
    }

    public boolean canMove(int direction) {
        switch (direction) {
            case 0: {
                return this.getTile(this.currentX + 8, this.currentY) == 0 || this.getTile(this.currentX + 8, this.currentY) == 12 || this.getTile(this.currentX + 8, this.currentY) == 14 || this.getTile(this.currentX + 8, this.currentY) == 15;
            }
            case 1: {
                return this.getTile(this.currentX, this.currentY + 8) == 0 || this.getTile(this.currentX, this.currentY + 8) == 12 || this.getTile(this.currentX, this.currentY + 8) == 14 || this.getTile(this.currentX, this.currentY + 8) == 15;
            }
            case 2: {
                return this.getTile(this.currentX - 8, this.currentY) == 0 || this.getTile(this.currentX - 8, this.currentY) == 12 || this.getTile(this.currentX - 8, this.currentY) == 14 || this.getTile(this.currentX - 8, this.currentY) == 15;
            }
            case 3: {
                return this.getTile(this.currentX, this.currentY - 8) == 0 || this.getTile(this.currentX, this.currentY - 8) == 12 || this.getTile(this.currentX, this.currentY - 8) == 14 || this.getTile(this.currentX, this.currentY - 8) == 15;
            }
        }
        return false;
    }

    public void move(PacmanArcadeGUI gui) {
        if (gui.tick % (long)this.speed == 0L && gui.started) {
            if (this.movingUp && this.canMove(3)) {
                this.isMoving = true;
                this.setDirection(3);
                this.updateY(-8);
            } else if (this.movingLeft && this.canMove(2)) {
                this.isMoving = true;
                this.setDirection(2);
                this.updateX(-8);
            } else if (this.movingDown && this.canMove(1)) {
                this.isMoving = true;
                this.setDirection(1);
                this.updateY(8);
            } else if (this.movingRight && this.canMove(0)) {
                this.isMoving = true;
                this.setDirection(0);
                this.updateX(8);
            } else {
                this.isMoving = false;
            }
        }
    }

    public void render(Minecraft mc, PacmanArcadeGUI gui) {
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void update(Minecraft mc, PacmanArcadeGUI gui, GameMap theMap) {
        this.move(gui);
        if (this.currentTile == 12) {
            this.teleport(this.direction == 2 ? 152 : -152, 0);
        }
        this.boundingBox.setLocation(this.currentX, this.currentY);
        this.currentTile = this.getTile(this.currentX, this.currentY);
    }

    public void teleport(int targetX, int targetY) {
        this.updateX(targetX);
        this.updateY(targetY);
    }

    public int getTile(int x, int y) {
        if (x > -84 && x < 84 && y > -84 && y < 84) {
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 't') {
                return 10;
            }
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 'c') {
                return 11;
            }
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 'p') {
                return 12;
            }
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 'y') {
                return 15;
            }
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 's') {
                return 16;
            }
            if (this.board[y / 8 + 10].charAt(x / 8 + 10) == 'g') {
                return 13;
            }
            return Character.getNumericValue(this.board[y / 8 + 10].charAt(x / 8 + 10));
        }
        return 2;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public int getY() {
        return this.currentY;
    }

    public void setY(int y) {
        this.currentY = y;
    }

    public int getX() {
        return this.currentX;
    }

    public void setX(int x) {
        this.currentX = x;
    }

    public void updateX(int x) {
        this.currentX += x;
    }

    public void updateY(int y) {
        this.currentY += y;
    }
}
