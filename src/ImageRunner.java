import java.awt.GraphicsEnvironment;

public class ImageRunner {

    // The shape grid is RESOLUTION x RESOLUTION, so this is the only number you
    // need to touch to change how many shapes get drawn:
    //     40  ->   1,600 shapes  (the original, ~85 fps)
    //     80  ->   6,400 shapes  (~22 fps)
    //    100  ->  10,000 shapes  (~14 fps)
    //    140  ->  19,600 shapes  (~7 fps)
    //    200  -> 40,000 shapes  (~3.5 fps, generates instantly - the sweet spot)
    //    400  -> 160,000 shapes (the hard ceiling: spacing hits 1, so any finer
    //                            and the shapes are sub-pixel and invisible.
    //                            Costs a 9 MB App.py and ~50s to start up.)
    // Detail is capped by the source image, so going finer than the image's own
    // pixel size just makes bigger blocks, not a sharper picture.
    private static final int RESOLUTION = 400;

    // Use an image at least RESOLUTION x RESOLUTION or the extra shapes are wasted.
    // The 40x40 images (Rad.png, Obama.png, BlackHole.png, ...) top out at RESOLUTION = 40.
    private static final String IMAGE = "images/astronaut.jpg";

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
