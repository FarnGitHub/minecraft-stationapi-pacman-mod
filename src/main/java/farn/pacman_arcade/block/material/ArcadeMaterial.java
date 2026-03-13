package farn.pacman_arcade.block.material;

import net.minecraft.block.MapColor;
import net.minecraft.block.material.Material;

public class ArcadeMaterial extends Material {
    public static final ArcadeMaterial ARCADE = new ArcadeMaterial();

    public ArcadeMaterial() {
        super(MapColor.LIGHT_GRAY);
        setUnpushablePistonBehavior();
    }
}
