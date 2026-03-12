package farn.threaded_screenshot;

import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.tick.TickScheduler;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ScreenShotManager {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    public static final File screenShotDirectory = new File(Minecraft.getRunDirectory(), "screenshots");

    public static void take(int width, int height) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 3);
        GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
        byte[] pixelData = new byte[width * height * 3];
        buffer.get(pixelData);
        (new ScreenShotThread(width, height, pixelData)).start();
    }

    public static void takeMinecraft() {
        if(!Minecraft.INSTANCE.screenshotKeyDown) {
            Minecraft.INSTANCE.screenshotKeyDown = true;
            take(Minecraft.INSTANCE.displayWidth, Minecraft.INSTANCE.displayHeight);
        }
    }

    public static void sendMessage(String msg) {
        TickScheduler.CLIENT_RENDER_END.immediate(() ->
                Minecraft.INSTANCE.inGameHud.addChatMessage(msg)
        );
    }

    static {
        screenShotDirectory.mkdir();
    }

}
