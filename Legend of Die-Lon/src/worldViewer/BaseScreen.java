package worldViewer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import resources.Art;

public class BaseScreen extends BaseBitmap {
	protected BufferedImage image;
	protected int xOffset;
	protected int yOffset;
	
	public BaseScreen(int w, int h) {
		super(w, h);
		this.image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void loadResources() {
		Art.loadAllResources(this);
	}
	
	public BufferedImage getBufferedImage() {
		return image;
	}
	
	public void clear(int color) {
		Arrays.fill(pixels, color);
	}
	
	public void blit(BaseBitmap bitmap, int x, int y) {
		this.blit(bitmap, x, y, bitmap.width, bitmap.height);
	}
	
	public void blit(BaseBitmap bitmap, int x, int y, int w, int h) {
		//TODO: May need to change parameter types into abstracts/interfaces.
		if (w == -1)
			w = bitmap.width;
		if (h == -1)
			h = bitmap.height;
		Rect blitArea = new Rect(x, y, w, h).adjust(this);
		int blitWidth = blitArea.bottomRightCorner_X - blitArea.topLeftCorner_X;
		
		for (int yy = blitArea.topLeftCorner_Y; yy < blitArea.bottomRightCorner_Y; yy++) {
			int tgt = yy * this.width + blitArea.topLeftCorner_X;
			int src = (yy - y) * bitmap.width + (blitArea.topLeftCorner_X - x);
			tgt -= src;
			for (int xx = src; xx < src + blitWidth; xx++) {
				int color = bitmap.pixels[xx];
				int alpha = (color >> 24) & 0xFF;
				if (alpha == 0xFF) {
					this.pixels[tgt + xx] = color;
				}
				else {
					this.pixels[tgt + xx] = blendPixels(this.pixels[tgt + xx], color);
				}
			}
		}
	}
	
	public void setOffset(int xOff, int yOff) {
		this.xOffset = xOff;
		this.yOffset = yOff;
	}
}
