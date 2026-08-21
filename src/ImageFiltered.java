import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFiltered extends Image{

    // cmu_graphics draws into a 400x400 canvas by default, so the shape grid
    // always has to spread itself across exactly this many units.
    public static final int CANVAS = 400;

    public ImageFiltered(String filename) throws Exception {
        super(filename);
    }

    public void makeBlue() {
        Pixel[][] tempPixels = getPixels();

        for (int row = 0; row < tempPixels.length; row++) {
            for (int col = 0; col < tempPixels[0].length; col++) {
                tempPixels[row][col].setRed(0);
                tempPixels[row][col].setGreen(0);
            }
        }
    }

    public void makeRed() {
        Pixel[][] tempPixels = getPixels();

        for (int row = 0; row < tempPixels.length; row++) {
            for (int col = 0; col < tempPixels[0].length; col++) {
                tempPixels[row][col].setGreen(0);
                tempPixels[row][col].setBlue(0);
            }
        }
    }

    public void makePython() throws IOException {
        makePython(40);
    }

    // resolution is the width/height of the shape grid, so the drawing ends up
    // with resolution * resolution shapes. The source image is resampled to fit,
    // which means the grid no longer has to match the image's pixel dimensions.
    public void makePython(int resolution) throws IOException {

        Pixel[][] tempPixels = getPixels();

        int height = tempPixels.length;
        int width = tempPixels[0].length;

        double spacing = (double) CANVAS / resolution;

        int shapeCount = resolution * resolution;

        List<String> out = new ArrayList<>(shapeCount + 16);
        out.add("from cmu_graphics import *");
        out.add("");
        out.add("print(\"Hello, World!\")");
        out.add("");
        out.add("#run from terminal: python src/App.py");
        out.add("#put pip install cmu-graphics in terminal if you don't have it yet");
        out.add("");
        out.add("#cmu_graphics stops drawing after 2000 shapes unless we raise the cap");
        out.add("app.setMaxShapeCount(" + (shapeCount + 100) + ")");
        out.add("");
        out.add("#delete all under");
        out.add("");

        for (int row = 0; row < resolution; row++) {
            for (int col = 0; col < resolution; col++) {

                Pixel p = sampleCell(tempPixels, row, col, resolution, height, width);

                // Mirroring original X/Col, then rotating 90 CCW:
                double x = col * spacing;
                double y = row * spacing;

                String fill = "rgb(" + p.getRed() + ", " + p.getGreen() + ", " + p.getBlue() + ")";

                // A square exactly one cell wide tiles the canvas with no gaps and no
                // overlap, so each cell keeps its own colour instead of being smeared
                // by its neighbours the way the old oversized stars were.
                out.add("Rect(" + fmt(x) + ", " + fmt(y) + ", " + fmt(spacing) + ", "
                    + fmt(spacing) + ", fill=" + fill + ")");
            }
        }

        out.add("");
        out.add("cmu_graphics.run()");

        FileReader.writeLines(out, "src/App.py");
        System.out.println("Drew " + shapeCount + " rects at " + resolution + "x" + resolution
            + " (spacing " + fmt(spacing) + ")");
    }

    // Averages the block of source pixels that falls under one grid cell. When the
    // grid is finer than the image this just repeats the nearest pixel instead.
    private Pixel sampleCell(Pixel[][] px, int row, int col, int resolution, int height, int width) {
        int rowStart = (int) ((long) row * height / resolution);
        int rowEnd   = (int) ((long) (row + 1) * height / resolution);
        int colStart = (int) ((long) col * width / resolution);
        int colEnd   = (int) ((long) (col + 1) * width / resolution);

        if (rowEnd <= rowStart) rowEnd = rowStart + 1;
        if (colEnd <= colStart) colEnd = colStart + 1;
        if (rowEnd > height) rowEnd = height;
        if (colEnd > width) colEnd = width;

        long red = 0, green = 0, blue = 0, count = 0;

        for (int r = rowStart; r < rowEnd; r++) {
            for (int c = colStart; c < colEnd; c++) {
                red   += px[r][c].getRed();
                green += px[r][c].getGreen();
                blue  += px[r][c].getBlue();
                count++;
            }
        }

        return new Pixel((int) (red / count), (int) (green / count), (int) (blue / count));
    }

    // Keeps whole numbers looking like whole numbers in the generated Python.
    private static String fmt(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((int) Math.rint(value));
        }
        return String.format("%.2f", value);
    }
    
}
