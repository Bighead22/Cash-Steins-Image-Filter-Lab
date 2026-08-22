import java.awt.GraphicsEnvironment;

public class ImageRunner {

    // The shape grid is RESOLUTION x RESOLUTION, so this is the only number you
    // need to touch to change how many shapes get drawn. Each cell is drawn as a
    // square exactly one cell wide, so the grid tiles the canvas exactly:
    //     100 ->  10,000 rects (cells are 4 units wide)
    //     200 ->  40,000 rects (cells are 2 units wide)
    //     400 -> 160,000 rects (cells are 1 unit wide - an exact reproduction of
    //                           the source image, and the hard ceiling. Spacing
    //                           cannot go below 1, so anything finer would be
    //                           sub-pixel and invisible.)
    // Detail is also capped by the source image, so a 40x40 picture has nothing
    // more to give past RESOLUTION = 40 no matter how fine the grid gets.
    private static final int RESOLUTION = 400;

    // Use an image at least RESOLUTION x RESOLUTION or the extra shapes are wasted.
    // The 40x40 images (Rad.png, Obama.png, BlackHole.png, ...) top out at RESOLUTION = 40.
    private static final String IMAGE = "images/Radv2.jpeg";

    public static void main(String[] args) throws Exception {

        String originalImage = IMAGE;

        ImageFiltered myBlueImage = new ImageFiltered(originalImage);
        myBlueImage.makePython(RESOLUTION);

        // Only pop the Swing preview windows when there is actually a screen to
        // draw them on, so the generator still runs from a plain terminal.
        if (!GraphicsEnvironment.isHeadless()) {
            Image myImage = new Image(originalImage);
            myImage.display();
            myBlueImage.display();
        }

    }
}
