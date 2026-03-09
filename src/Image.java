import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image {
    private Pixel[][] pixels;
    private int width;
    private int height;
    private BufferedImage bufferedImage;

    private static int nextDisplayX, nextDisplayY;

    public Image(String filename) throws Exception{
        bufferedImage = ImageIO.read(new File(filename));

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        pixels = imageToArray(bufferedImage);
    }

    private Pixel[][] imageToArray(BufferedImage img) {
        Pixel[][] tempPixels = new Pixel[height][width]; //height is num rows; width is num columns
        for (int row = 0; row < tempPixels.length; row++) {
            for (int col = 0; col < tempPixels[0].length; col++) {
                int rgb = img.getRGB(col,row); //CAREFUL: x coordinate is the col; y coord is the row
                
                //Fansy bit shifting to get 8 bit rgb values from 32 bit int value
                int r = (rgb >> 16) & 0xFF; // R
                int g = (rgb >> 8) & 0xFF;  // G
                int b = rgb & 0xFF;         // B

                tempPixels[row][col] = new Pixel(r, g, b);
            }
        }

        return tempPixels;
    }
    

    private BufferedImage arrayToImage(Pixel[][] myPixels) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < myPixels.length; row++) {
            for (int col = 0; col < myPixels[0].length; col++) {
                int r = clamp(myPixels[row][col].getRed());
                int g = clamp(myPixels[row][col].getGreen());
                int b = clamp(myPixels[row][col].getBlue());
                int rgb = (r << 16) | (g << 8) | b;
                out.setRGB(col, row, rgb); //CAREFUL: x coordinate is the col; y coord is the row
            }
        }
        return out;
    }

    private int clamp(int v) {
        //Make sure rgb values are in range 0-255
        if (v < 0) return 0;
        if (v > 255) return 255;
        return v;
    }

    public void display() {
            // 1. Update BufferedImage from pixels 
            bufferedImage = arrayToImage(pixels);

            // 2. Create an ImageIcon from the BufferedImage
            ImageIcon icon = new ImageIcon(bufferedImage);

            // 3. Create a JLabel with the ImageIcon
            JLabel label = new JLabel(icon);

            // 4. Create a JFrame to hold the label
            JFrame frame = new JFrame("Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // 5. Add the label to the frame's content pane
            frame.getContentPane().add(label);
            
            // 6. Pack the frame to the size of its contents and don't allow resizing
            frame.pack(); // Adjusts frame size to fit the image
            frame.setResizable(false);

            // 7. Set location of frame
            // frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setLocation(nextDisplayX, nextDisplayY); // Let's be fancy...

            // Get the screen width and height
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();

            // Wrap images if they go off the screen
            nextDisplayX += width;
            if(nextDisplayX + width > screenWidth) {
                nextDisplayX = 0;
                nextDisplayY += height;
                if(nextDisplayY + height > screenHeight) {
                    nextDisplayY = 0;
                }
            }

            // 8. Make frame visible
            frame.setVisible(true);
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
