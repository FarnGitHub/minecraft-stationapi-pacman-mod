package farn.pacman_arcade;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;

@SuppressWarnings("unused")
public class PacManTextureListener {

    public static int[] textures = new int[16];
    public static int bottomTexture;
    public static int backTexture;
    public static int frontTexture;
    public static int leftTexture;
    public static int rightTexture;
    public static int arcadeIcon;


    @EventListener
    public void registerTexture(TextureRegisterEvent event) {
        ExpandableAtlas terrainAtlas = Atlases.getTerrain();
        bottomTexture = getTexture(terrainAtlas, "block/pacman/arcade_bottom");
        backTexture = getTexture(terrainAtlas, "block/pacman/arcade_back");
        frontTexture = getTexture(terrainAtlas, "block/pacman/arcade_front");
        leftTexture = getTexture(terrainAtlas, "block/pacman/arcade_left");
        rightTexture = getTexture(terrainAtlas, "block/pacman/arcade_right");
        PacmanMain.pacmanItem.setTexture(PacmanMain.NAMESPACE.id("item/pacman_arcade_icon"));
        PacmanMain.pacmanCoins.setTexture(PacmanMain.NAMESPACE.id("item/pacman_coins"));
        int[] sides = new int[]{3, 2, 5, 4, 5, 4, 2, 3, 2, 3, 4, 5, 4, 5, 3, 2};
        for(int i = 0; i < sides.length; i++) {
            textures[i] = setTexture(sides[i]);
        }
    }

    private static int getTexture(ExpandableAtlas terrainAtlas, String iden) {
        return terrainAtlas.addTexture(PacmanMain.NAMESPACE.id(iden)).index;
    }

    private static int setTexture(int index) {
        return switch (index) {
            case 2 -> backTexture;
            case 3 -> frontTexture;
            case 4 -> leftTexture;
            case 5 -> rightTexture;
            default -> bottomTexture;
        };
    }

}
