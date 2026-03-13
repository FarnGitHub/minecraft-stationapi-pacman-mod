package farn.pacman_arcade.client.entity;

import farn.pacman_arcade.client.model.PacmanArcadeModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.lwjgl.opengl.GL11;

//Replace by json Model
@Deprecated
public class PacManBlockEntityRenderer extends BlockEntityRenderer {
	private final PacmanArcadeModel og_model = new PacmanArcadeModel();

	public void render(BlockEntity blockEntity, double x, double y, double z, float tick) {
		int meta = blockEntity.getPushedBlockData();
		this.bindTexture("/assets/pacman_arcade/pacman_textures/machine.png");
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5f, y + 1.5f, z + 0.5f);
		GL11.glRotatef(meta == 0 ? 180.0f : (meta == 1 ? 90.0f : (meta == 2 ? 0.0f : 270.0f)), 0.0f, 1.0f, 0.0f);
		GL11.glScalef(1.0f, -1.0f, -1.0f);
		this.og_model.render(0.0625f);
		GL11.glPopMatrix();
	}
}
