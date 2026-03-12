package farn.threaded_screenshot.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.threaded_screenshot.ScreenShotManager;
import net.minecraft.client.Screenshot;
import org.spongepowered.asm.mixin.Mixin;

import java.io.File;

@Mixin(Screenshot.class)
public class ScreenshotMixin {

    @WrapMethod(method="take")
    private static String redirectTake(File gameDir, int width, int height, Operation<String> original) {
        ScreenShotManager.take(width, height);
        return "";
    }
}
