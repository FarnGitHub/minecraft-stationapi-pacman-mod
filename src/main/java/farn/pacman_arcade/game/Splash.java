package farn.pacman_arcade.game;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.opengl.GL11;

public class Splash {
    public String[] location;
    public int tick = 0;
    public boolean done;

    public Splash(String ... location) {
        this.location = location;
    }

    public void reset() {
        this.tick = 0;
        this.done = false;
    }

    public void update() {
        if (this.tick < 50) {
            ++this.tick;
        }
    }

    public void render(Minecraft mc, Screen gui) {
        if (this.tick != 50) {
            GL11.glEnable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glBindTexture((int)3553, (int)mc.textureManager.getTextureId(this.location[this.tick / (50 / this.location.length)]));
            gui.drawTexture(gui.width / 2 - 84, gui.height / 2 - 100, 0, 0, 168, 200);
            GL11.glDisable((int)3042);
        } else {
            this.done = true;
        }
    }
}
