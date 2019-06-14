package mandelbrot;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ImageWrapper {
    BufferedImage image;

    int width;
    int height;

    double minX;
    double maxX;
    double minY;
    double maxY;

    double plotWidth;
    double plotHeight;

    int drawWidth;
    int drawHeight;
    int drawOffsetX;
    int drawOffsetY;

    ImageWrapper(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.width = width;
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void init() {
        Graphics2D g2 = image.createGraphics();
        g2.setBackground((new Color(0, 0, 0)));
        g2.clearRect(0, 0, image.getWidth(), image.getHeight());
    }

    /**
     * Plot to buffered image, scaling according to the image dimensions minX, minY, maxX, maxY.
     */
    void plot(Point2D coordinates, int color) {
        image.setRGB((int)coordinates.getX(), (int)coordinates.getY(), color);
    }

    /**
     * Set drawing bounds.
     */
    void setBounds(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;

        plotWidth = maxX - minX;
        plotHeight = maxY - minY;

        double plotAspect = plotWidth / plotHeight;
        double drawAspect = (double)width / (double)height;

        drawWidth = width;
        drawHeight = height;
        drawOffsetX = 0;
        drawOffsetY = 0;
        if (plotAspect < drawAspect) {
            drawWidth = (int)(height * plotAspect);
            drawOffsetX = (width - drawWidth) / 2;
        } else {
            drawHeight = (int)(width / plotAspect);
            drawOffsetY = (height - drawHeight) / 2;
        }
    }

    /**
     * Version of setBounds with a center and magnification
     */
    void setBounds(double x, double y, double scale) {
        double drawAspect = (double)width / (double)height;

        double halfPlotWidth = scale / 2.0;
        double halfPlotHeight = scale / 2.0;
        if (drawAspect > 1.0) {
            halfPlotWidth *= drawAspect;
        } else {
            halfPlotHeight /= drawAspect;
        }

        setBounds(x - halfPlotWidth, y - halfPlotHeight, x + halfPlotWidth, y + halfPlotHeight);
    }

    // extra draw utilities
    // get current plot based on counter
    Point2D getDrawCoordinates(int counter) {
        int x = counter % drawWidth;
        int y = (counter / drawWidth) % drawHeight;

        x += drawOffsetX;
        y += drawOffsetY;

        return new Point2D.Double(x, y);
    }

    // get current graph/mandelbrot coordinate from current plot coordinates
    Point2D getPlotCoordinates(Point2D drawCoordinates) {
        double x = minX + ((drawCoordinates.getX() - drawOffsetX) / drawWidth) * plotWidth;
        double y = minY + ((drawCoordinates.getY() - drawOffsetY) / drawHeight) * plotHeight;

        return new Point2D.Double(x, y);
    }
}
