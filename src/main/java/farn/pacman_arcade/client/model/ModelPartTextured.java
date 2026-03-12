package farn.pacman_arcade.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Quad;
import net.minecraft.client.model.Vertex;
import net.minecraft.client.render.entity.model.EntityModel;

public class ModelPartTextured extends ModelPart {
    public ModelPartTextured(EntityModel part, int u, int v) {
        super(u, v);
    }

    public void addCuboid(float x, float y, float z, int sizeX, int sizeY, int sizeZ, int width, int height) {
        this.addCuboid(x, y, z, sizeX, sizeY, sizeZ, 0.0F, width, height);
    }

    public void addCuboid(float x, float y, float z, int sizeX, int sizeY, int sizeZ, float dilation, int width, int height) {
        this.corners = new Vertex[8];
        this.faces = new Quad[6];
        float var8 = x + (float)sizeX;
        float var9 = y + (float)sizeY;
        float var10 = z + (float)sizeZ;
        x -= dilation;
        y -= dilation;
        z -= dilation;
        var8 += dilation;
        var9 += dilation;
        var10 += dilation;
        if (this.mirror) {
            float var11 = var8;
            var8 = x;
            x = var11;
        }

        Vertex var26 = new Vertex(x, y, z, 0.0F, 0.0F);
        Vertex var12 = new Vertex(var8, y, z, 0.0F, 8.0F);
        Vertex var13 = new Vertex(var8, var9, z, 8.0F, 8.0F);
        Vertex var14 = new Vertex(x, var9, z, 8.0F, 0.0F);
        Vertex var15 = new Vertex(x, y, var10, 0.0F, 0.0F);
        Vertex var16 = new Vertex(var8, y, var10, 0.0F, 8.0F);
        Vertex var17 = new Vertex(var8, var9, var10, 8.0F, 8.0F);
        Vertex var18 = new Vertex(x, var9, var10, 8.0F, 0.0F);
        this.corners[0] = var26;
        this.corners[1] = var12;
        this.corners[2] = var13;
        this.corners[3] = var14;
        this.corners[4] = var15;
        this.corners[5] = var16;
        this.corners[6] = var17;
        this.corners[7] = var18;
        this.faces[0] = new AdvQuad(new Vertex[]{var16, var12, var13, var17}, this.u + sizeZ + sizeX, this.v + sizeZ, this.u + sizeZ + sizeX + sizeZ, this.v + sizeZ + sizeY, width, height);
        this.faces[1] = new AdvQuad(new Vertex[]{var26, var15, var18, var14}, this.u, this.v + sizeZ, this.u + sizeZ, this.v + sizeZ + sizeY, width, height);
        this.faces[2] = new AdvQuad(new Vertex[]{var16, var15, var26, var12}, this.u + sizeZ, this.v, this.u + sizeZ + sizeX, this.v + sizeZ,  width, height);
        this.faces[3] = new AdvQuad(new Vertex[]{var13, var14, var18, var17}, this.u + sizeZ + sizeX, this.v, this.u + sizeZ + sizeX + sizeX, this.v + sizeZ,  width, height);
        this.faces[4] = new AdvQuad(new Vertex[]{var12, var26, var14, var13}, this.u + sizeZ, this.v + sizeZ, this.u + sizeZ + sizeX, this.v + sizeZ + sizeY,  width, height);
        this.faces[5] = new AdvQuad(new Vertex[]{var15, var16, var17, var18}, this.u + sizeZ + sizeX + sizeZ, this.v + sizeZ, this.u + sizeZ + sizeX + sizeZ + sizeX, this.v + sizeZ + sizeY,  width, height);
        if (this.mirror) {
            for(Quad quad : this.faces) {
                quad.flip();
            }
        }

    }

    private static class AdvQuad extends Quad {

        public AdvQuad(Vertex[] vertices, int u1, int v1, int u2, int v2, int width, int height) {
            super(vertices, u1, v1, u2, v2);
            vertices[0] = vertices[0].remap((float)u2 / width, (float)v1 / height);
            vertices[1] = vertices[1].remap((float)u1 / width, (float)v1 / height);
            vertices[2] = vertices[2].remap((float)u1 / width, (float)v2 / height);
            vertices[3] = vertices[3].remap((float)u2 / width, (float)v2 / height);
        }
    }
}
