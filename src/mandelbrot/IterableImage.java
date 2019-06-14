package mandelbrot;

import java.awt.image.BufferedImage;

public interface IterableImage {
    /**
     * Initializes image.
     */
    public void init();

    /**
     * Return a buffered image that is ready to render.
     */
    public BufferedImage getImage();

    /**
     * Calculates next image iteration.
     */
    public void iterate();
}
