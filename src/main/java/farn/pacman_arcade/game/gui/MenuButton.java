package farn.pacman_arcade.game.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.lwjgl.opengl.GL11;

public class MenuButton
extends ButtonWidget {
    public MenuButton(int id, int x, int y, int width, int height, String text) {
        super(id, x, y, width, height, text);
    }

    @Override
    public void render(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.visible) {
            TextRenderer textRenderer = minecraft.textRenderer;
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/menu.png"));
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            boolean isOver = this.isMouseOver(minecraft, mouseX, mouseY);
            int selectTYpe = this.getYImage(isOver);
            switch (this.id) {
                case 0: {
                    this.drawTexture(this.x, this.y, 0, 16 + selectTYpe * 16, 56, 16);
                    break;
                }
                case 1: {
                    this.drawTexture(this.x, this.y, 56, 16 + selectTYpe * 16, 88, 16);
                    break;
                }
                case 2: {
                    this.drawTexture(this.x, this.y, 112, selectTYpe * 16 - 16, 72, 16);
                }
            }
            this.renderBackground(minecraft, mouseX, mouseY);
            int textColor = 0xE0E0E0;
            if (!this.active) {
                textColor = -6250336;
            } else if (isOver) {
                textColor = 0xFFFFA0;
            }
            this.drawTextWithShadow(textRenderer, this.text, this.x + this.width / 2, this.y + (this.height - 8) / 2, textColor);
        }
    }
}
