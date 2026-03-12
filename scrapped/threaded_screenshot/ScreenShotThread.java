package farn.threaded_screenshot;

import net.minecraft.client.resource.language.I18n;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;

public class ScreenShotThread extends Thread {
	public int width, height;
	public byte[] pixelData;

	public ScreenShotThread(int width, int height, byte[] pixelData) {
		this.width = width;
		this.height = height;
		this.pixelData = pixelData;
	}

	@Override
	public void run() {
		try {
			int[] imageData = new int[width * height];

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int src = x + (height - y - 1) * width;
					if (src < 0 || src >= width * height) continue;
					int r = pixelData[src * 3] & 255;
					int g = pixelData[src * 3 + 1] & 255;
					int b = pixelData[src * 3 + 2] & 255;
					imageData[x + y * width] = 0xFF000000 | r << 16 | g << 8 | b;
				}
			}

			BufferedImage img =
					new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			img.setRGB(0, 0, width, height, imageData, 0, width);
			File file;
			int duplicate = 1;
			while((file = createScreenshot(duplicate)).exists()) ++duplicate;
			ImageIO.write(img, "png", file);
			sendMessage("screenshot_take_success", file.getName());
		} catch (Exception e) {
			sendMessage("screenshot_take_failed", e);
		}
	}

	private void sendMessage(String format, Object... args) {
		ScreenShotManager.sendMessage(I18n.getTranslation(format, args));
	}

	private File createScreenshot(int duplicate) {
		return new File(ScreenShotManager.screenShotDirectory,
				ScreenShotManager.dateFormat.format(new Date()) +
						(duplicate == 1 ? "" : "_" + duplicate) + ".png");
	}

}
