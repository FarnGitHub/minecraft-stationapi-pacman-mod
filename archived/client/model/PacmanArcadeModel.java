package farn.pacman_arcade.client.model;

import net.minecraft.client.render.entity.model.EntityModel;

//Replace by json Model
@Deprecated
public class PacmanArcadeModel
extends EntityModel {
    ModelPartTextured LeftSide;
    ModelPartTextured TopSide;
    ModelPartTextured BackSide;
    ModelPartTextured RightSide;
    ModelPartTextured BottomSide;
    ModelPartTextured Joystick;
    ModelPartTextured Screen;
    ModelPartTextured LeftButton;
    ModelPartTextured RightButton;

    public PacmanArcadeModel() {
        this.LeftSide = new ModelPartTextured(32, 17);
        this.LeftSide.addCuboid(7.0f, -6.0f, -8.0f, 1, 14, 15,128,64);
        this.LeftSide.setPivot(0.0f, 15.0f, 0.0f);
        this.LeftSide.mirror = true;
        this.setRotation(this.LeftSide, 0.0f, 0.0f, 0.0f);
        this.TopSide = new ModelPartTextured(0, 0);
        this.TopSide.addCuboid(-8.0f, -7.0f, -8.0f, 16, 1, 16,128,64);
        this.TopSide.setPivot(0.0f, 15.0f, 0.0f);
        this.TopSide.mirror = true;
        this.setRotation(this.TopSide, 0.0f, 0.0f, 0.0f);
        this.BackSide = new ModelPartTextured(64, 0);
        this.BackSide.addCuboid(-8.0f, -6.0f, 7.0f, 16, 14, 1,128,64);
        this.BackSide.setPivot(0.0f, 15.0f, 0.0f);
        this.BackSide.mirror = true;
        this.setRotation(this.BackSide, 0.0f, 0.0f, 0.0f);
        this.RightSide = new ModelPartTextured(0, 17);
        this.RightSide.addCuboid(-8.0f, -6.0f, -8.0f, 1, 14, 15,128,64);
        this.RightSide.setPivot(0.0f, 15.0f, 0.0f);
        this.RightSide.mirror = true;
        this.setRotation(this.RightSide, 0.0f, 0.0f, 0.0f);
        this.BottomSide = new ModelPartTextured(64, 15);
        this.BottomSide.addCuboid(-8.0f, 8.0f, -8.0f, 16, 1, 16,128,64);
        this.BottomSide.setPivot(0.0f, 15.0f, 0.0f);
        this.BottomSide.mirror = true;
        this.setRotation(this.BottomSide, 0.0f, 0.0f, 0.0f);
        this.Joystick = new ModelPartTextured(98, 0);
        this.Joystick.addCuboid(-5.0f, 5.0f, -7.0f, 1, 3, 1,128,64);
        this.Joystick.setPivot(0.0f, 15.0f, 0.0f);
        this.Joystick.mirror = true;
        this.setRotation(this.Joystick, 0.0f, 0.0f, 0.0f);
        this.Screen = new ModelPartTextured(0, 46);
        this.Screen.addCuboid(-6.0f, 7.0f, -4.0f, 12, 1, 10,128,64);
        this.Screen.setPivot(0.0f, 15.0f, 0.0f);
        this.Screen.mirror = true;
        this.setRotation(this.Screen, 0.0f, 0.0f, 0.0f);
        this.LeftButton = new ModelPartTextured(106, 0);
        this.LeftButton.addCuboid(3.8f, 7.0f, -7.0f, 1, 1, 1,128,64);
        this.LeftButton.setPivot(0.0f, 15.0f, 0.0f);
        this.LeftButton.mirror = true;
        this.setRotation(this.LeftButton, 0.0f, 0.0f, 0.0f);
        this.RightButton = new ModelPartTextured(102, 0);
        this.RightButton.addCuboid(1.0f, 7.0f, -7.0f, 1, 1, 1,128,64);
        this.RightButton.setPivot(0.0f, 15.0f, 0.0f);
        this.RightButton.mirror = true;
        this.setRotation(this.RightButton, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(f, f1, f2, f3, f4, f5);
        this.setAngles(f, f1, f2, f3, f4, f5);
        this.LeftSide.render(f5);
        this.TopSide.render(f5);
        this.BackSide.render(f5);
        this.RightSide.render(f5);
        this.BottomSide.render(f5);
        this.Joystick.render(f5);
        this.Screen.render(f5);
        this.LeftButton.render(f5);
        this.RightButton.render(f5);
    }

    public void render(float f5) {
        this.LeftSide.render(f5);
        this.TopSide.render(f5);
        this.BackSide.render(f5);
        this.RightSide.render(f5);
        this.BottomSide.render(f5);
        this.Joystick.render(f5);
        this.Screen.render(f5);
        this.LeftButton.render(f5);
        this.RightButton.render(f5);
    }

    private void setRotation(ModelPartTextured model, float x, float y, float z) {
        model.pitch = x;
        model.yaw = y;
        model.roll = z;
    }
}
