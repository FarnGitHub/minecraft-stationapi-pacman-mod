package farn.pacman_arcade.client.entity;

import farn.pacman_arcade.client.model.PacmanArcadeModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.lwjgl.opengl.GL11;

public class PacManBlockEntityRenderer extends BlockEntityRenderer {
	private PacmanArcadeModel aModel = new PacmanArcadeModel();

	public void render(BlockEntity tileentity1, double d, double d1, double d2, float f) {
		int i = tileentity1.getPushedBlockData();
		this.bindTexture("/assets/pacman_arcade/pacman_textures/machine.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float)((float)d + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
		GL11.glRotatef((float)(i == 0 ? 180.0f : (i == 1 ? 90.0f : (i == 2 ? 0.0f : 270.0f))), (float)0.0f, (float)1.0f, (float)0.0f);
		GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
		this.aModel.render(0.0625f);
		GL11.glPopMatrix();
	}
}
