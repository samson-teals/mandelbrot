package mandelbrot;

import java.awt.*;
import java.awt.geom.Point2D;

public class MandelbrotStarter extends ImageWrapper implements IterableImage {
    int counter = 0;
    int maxIterations = 0;
    int[] palette;

    MandelbrotStarter(int width, int height) {
        super(width, height);

        // This bounds the entire Mandelbrot set
        setBounds(-2.25, -1.5, 0.75, 1.5);

        // Find other bounds here: http://www.cuug.ab.ca/dewara/mandelbrot/Mandelbrowser.html
        // - try with maxIterations > 80
        //setBounds(-0.7453, 0.1127, 6.5e-4);

        reset();
    }

    // Reset counter (so that we can restart the mandelbrot calculation)
    public void reset() {
        counter = 0;
    }

    // 5 is a good starting point; 255 is a good practical maximum
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        initPalette();
    }

    public void iterate() {
        Point2D drawCoordinates = getDrawCoordinates(counter);
        Point2D plotCoordinates = getPlotCoordinates(drawCoordinates);

        Complex c = new Complex(plotCoordinates.getX(), plotCoordinates.getY());

        // implement the mandelbrot function here to get the number of iterations
        // - see https://simple.wikipedia.org/wiki/Mandelbrot_set
        // - z[n+1] = z[n]^2 + c
        // - for how many iterations (n) of this sequence does abs(z[n]) remain < 2?
        int iterations = counter % maxIterations + 1; // this line is wrong!

        plot(drawCoordinates, palette[iterations]);

        counter++;
    }

    // Calculate palette based on maxIterations
    // - Points in the Mandelbrot set are traditionally colored black.
    private void initPalette() {
        palette = new int[maxIterations + 1];

        for (int i = 0; i < maxIterations; i++) {
            int v = 255;
            palette[i] = new Color(v, v, v).getRGB();
        }

        palette[maxIterations] = 0;
    }
}
