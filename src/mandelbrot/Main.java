package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Main {
    IterableImage iimage;
    Timer timer;

    /**
     * The ImageCanvas nested class is only used for drawing. No calculations should be done here.
     */
    class ImageCanvas extends Canvas {
        int windowOffset;

        public ImageCanvas(int width, int height, int windowOffset) {
            setSize(width, windowOffset + height);

            this.windowOffset = windowOffset;
        }

        /**
         * This method paints the canvas and is called by the system whenever a repaint is requested.
         */
        public void paint(Graphics g) {
            BufferedImage image = iimage.getImage();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0, imageHeight + windowOffset, imageWidth, -imageHeight, null);
        }
    }

    public Main(IterableImage iimage, float framesPerSecond) {
        this.iimage = iimage;

        // sets up graphics
        Frame frame = new Frame("Mandelbrot Set");
        frame.setVisible(true);
        Insets insets = frame.getInsets();

        Canvas canvas = new ImageCanvas(iimage.getImage().getWidth(), iimage.getImage().getHeight(), insets.top);
        frame.add(canvas);
        frame.setLayout(null);
        frame.setSize(canvas.getWidth(), canvas.getHeight());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // sets up timer so that we can animate the life grid
        int frameDelay = (int) (1000 / framesPerSecond);
        timer = new Timer(frameDelay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public static void main(String args[]) {
        int width = 800;
        int height = 600;
        int framesPerSecond = 20;

        int maxIterLower = 120; // try 2
        int maxIterUpper = maxIterLower + 1; // try 255

        MandelbrotStarter mandelbrot = new MandelbrotStarter(width, height);
        mandelbrot.init();

        Main main = new Main(mandelbrot, framesPerSecond);

        for (int maxIterations = maxIterLower; maxIterations < maxIterUpper; maxIterations++) {
            System.out.println("Plotting set with maxIterations: " + maxIterations);

            mandelbrot.reset();
            mandelbrot.setMaxIterations(maxIterations);
            for (int i = 0; i < width * height; i++) {
                mandelbrot.iterate();
            }
        }

        main.stopTimer();
        System.out.println("done");
    }
}
