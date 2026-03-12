package farn.threaded_screenshot.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import farn.threaded_screenshot.ScreenShotManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @WrapOperation(method="handleScreenshotKey", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;screenshotKeyDown:Z", ordinal = 0))
    public boolean redirectTakeScreenshot(Minecraft instance, Operation<Boolean> original) {
        ScreenShotManager.takeMinecraft();
        return true;
    }
}
