package farn.pacman_arcade.game.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.lwjgl.opengl.GL11;

public class MenuButton
extends ButtonWidget {
    public MenuButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, par6Str);
    }

    @Override
    public void render(Minecraft par1Minecraft, int par2, int par3) {
        if (this.visible) {
            TextRenderer var4 = par1Minecraft.textRenderer;
            GL11.glBindTexture((int)3553, (int)par1Minecraft.textureManager.getTextureId("/assets/pacman_arcade/pacman_textures/menu.png"));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean field_82253_i = this.isMouseOver(par1Minecraft, par2, par3);
            int var5 = this.getYImage(field_82253_i);
            switch (this.id) {
                case 0: {
                    this.drawTexture(this.x, this.y, 0, 16 + var5 * 16, 56, 16);
                    break;
                }
                case 1: {
                    this.drawTexture(this.x, this.y, 56, 16 + var5 * 16, 88, 16);
                    break;
                }
                case 2: {
                    this.drawTexture(this.x, this.y, 112, var5 * 16 - 16, 72, 16);
                }
            }
            this.renderBackground(par1Minecraft, par2, par3);
            int var6 = 0xE0E0E0;
            if (!this.active) {
                var6 = -6250336;
            } else if (field_82253_i) {
                var6 = 0xFFFFA0;
            }
            this.drawTextWithShadow(var4, this.text, this.x + this.width / 2, this.y + (this.height - 8) / 2, var6);
        }
    }
}
