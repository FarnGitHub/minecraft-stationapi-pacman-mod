package farn.pacman_arcade.game.entity;

import farn.pacman_arcade.game.Animation;
import farn.pacman_arcade.game.GameMap;
import farn.pacman_arcade.game.gui.PacmanArcadeGUI;
import net.minecraft.client.Minecraft;

public class Ghost
extends Entity {
    public int mode = 0;
    public int type;
    public Animation animation;

    public Ghost(String[] board, int type, int startX, int startY) {
        super(board, startX, startY, 8, 8);
        this.type = type;
        this.animation = new Animation();
        this.speed = 3;
    }

    @Override
    public boolean canMove(int direction) {
        if (this.currentTile != 16 && this.currentTile != 13) {
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
        } else {
            if (this.currentTile == 13 && direction == 3) {
                return true;
            }
            if (this.currentTile == 16) {
                if (this.getTile(this.currentX - 8, this.currentY) == 16 && this.getTile(this.currentX + 8, this.currentY) != 16 && direction == 2) {
                    return true;
                }
                if (this.getTile(this.currentX - 8, this.currentY) != 16 && this.getTile(this.currentX + 8, this.currentY) == 16 && direction == 0) {
                    return true;
                }
                if (this.getTile(this.currentX - 8, this.currentY) == 16 && this.getTile(this.currentX + 8, this.currentY) == 16 && direction == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean between(double x, double y, double z) {
        return x >= y && x <= z;
    }

    public void decision(PacmanArcadeGUI gui, int x, int y) {
        if (x == this.currentX && y > this.currentY) {
            if (this.canMove(1)) {
                this.setMoving(1);
            } else if (this.canMove(2) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 0);
            } else if (this.canMove(3)) {
                this.setMoving(3);
            }
        } else if (x == this.currentX && y < this.currentY) {
            if (this.canMove(3)) {
                this.setMoving(3);
            } else if (this.canMove(2) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 0);
            } else if (this.canMove(1)) {
                this.setMoving(1);
            }
        } else if (x < this.currentX && y == this.currentY) {
            if (this.canMove(2)) {
                this.setMoving(2);
            } else if (this.canMove(3) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 1);
            } else if (this.canMove(0)) {
                this.setMoving(0);
            }
        } else if (x > this.currentX && y == this.currentY) {
            if (this.canMove(0)) {
                this.setMoving(0);
            } else if (this.canMove(3) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 1);
            } else if (this.canMove(2)) {
                this.setMoving(2);
            }
        } else if (x > this.currentX && y > this.currentY) {
            if (this.canMove(0) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 0 : 1);
            } else if (this.canMove(3) || this.canMove(2)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 2);
            }
        } else if (x < this.currentX && y < this.currentY) {
            if (this.canMove(2) || this.canMove(3)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 3);
            } else if (this.canMove(0) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 0 : 1);
            }
        } else if (x > this.currentX && y < this.currentY) {
            if (this.canMove(0) || this.canMove(3)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 0 : 3);
            } else if (this.canMove(2) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 1);
            }
        } else if (x < this.currentX && y > this.currentY) {
            if (this.canMove(2) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 1);
            } else if (this.canMove(3) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 0);
            }
        }
    }

    public void decision1(PacmanArcadeGUI gui, int x, int y) {
        if (x == this.currentX && y > this.currentY) {
            if (this.canMove(3)) {
                this.setMoving(3);
            } else if (this.canMove(2) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 0);
            } else if (this.canMove(1)) {
                this.setMoving(1);
            }
        } else if (x == this.currentX && y < this.currentY) {
            if (this.canMove(1)) {
                this.setMoving(1);
            } else if (this.canMove(2) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 0);
            } else if (this.canMove(3)) {
                this.setMoving(3);
            }
        } else if (x < this.currentX && y == this.currentY) {
            if (this.canMove(0)) {
                this.setMoving(0);
            } else if (this.canMove(3) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 1);
            } else if (this.canMove(2)) {
                this.setMoving(2);
            }
        } else if (x > this.currentX && y == this.currentY) {
            if (this.canMove(2)) {
                this.setMoving(2);
            } else if (this.canMove(3) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 1);
            } else if (this.canMove(0)) {
                this.setMoving(0);
            }
        } else if (x > this.currentX && y > this.currentY) {
            if (this.canMove(3) || this.canMove(2)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 2);
            } else if (this.canMove(1) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 1 : 0);
            }
        } else if (x < this.currentX && y < this.currentY) {
            if (this.canMove(0) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 0 : 1);
            } else if (this.canMove(2) || this.canMove(3)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 3);
            }
        } else if (x > this.currentX && y < this.currentY) {
            if (this.canMove(2) || this.canMove(1)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 1);
            } else if (this.canMove(3) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 0);
            }
        } else if (x < this.currentX && y > this.currentY) {
            if (this.canMove(3) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 3 : 0);
            } else if (this.canMove(3) || this.canMove(0)) {
                int i = this.rand.nextInt(2);
                this.setMoving(i == 1 ? 2 : 1);
            }
        }
    }

    @Override
    public void update(Minecraft mc, PacmanArcadeGUI gui, GameMap theMap) {
        if (this.currentTile == 13) {
            this.setMoving(3);
        }
        if (this.currentTile == 16) {
            if (this.getTile(this.currentX - 8, this.currentY) == 16 && this.getTile(this.currentX + 8, this.currentY) != 16) {
                this.setMoving(2);
            }
            if (this.getTile(this.currentX - 8, this.currentY) != 16 && this.getTile(this.currentX + 8, this.currentY) == 16) {
                this.setMoving(0);
            }
            if (this.getTile(this.currentX - 8, this.currentY) == 16 && this.getTile(this.currentX + 8, this.currentY) == 16) {
                this.setMoving(3);
            }
        }
        if (this.getMode() == 0) {
            this.decision(gui, gui.thePacman.currentX, gui.thePacman.currentY);
        } else if (this.getMode() == 1) {
            this.decision1(gui, gui.thePacman.currentX, gui.thePacman.currentY);
        } else if (this.getMode() == 2) {
            this.decision(gui, 0, -8);
        }
        super.update(mc, gui, theMap);
        this.animation.updateGhostAnimation();
    }

    @Override
    public void render(Minecraft mc, PacmanArcadeGUI gui) {
        if (this.getMode() == 0) {
            this.animation.animateGhost(mc, gui, this.type, this.mode, this.currentX, this.currentY);
        } else if (this.getMode() == 1) {
            if (gui.energizerTick < 200L) {
                this.animation.animateEnergizedGhost(mc, gui, this.mode, this.currentX, this.currentY);
            } else {
                this.animation.animateFlashingGhost(mc, gui, this.mode, this.currentX, this.currentY);
            }
        } else if (this.getMode() == 2) {
            this.animation.animateDeadGhost(mc, gui, this.mode, this.currentX, this.currentY);
        }
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
