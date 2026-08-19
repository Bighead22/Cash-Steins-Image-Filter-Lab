import java.io.IOException;

public class ImageFiltered extends Image{

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

    public void makePython() throws IOException{
        
        Pixel[][] tempPixels = getPixels();

        int height = tempPixels.length;
        int width = tempPixels[0].length;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                
                // Calculate 90-degree counter-clockwise rotated coordinates
                int rotatedRow = (width - 1) - col;
                int rotatedCol = row;

                FileReader.appendToFile(
                    "Circle(" + (rotatedRow * 10) + ", " + (rotatedCol * 10) + ", 10, fill=rgb(" 
                    + tempPixels[row][col].getRed() + ", " 
                    + tempPixels[row][col].getGreen() + ", " 
                    + tempPixels[row][col].getBlue() + "))", 
                    "src/app.py"
                );
                
            }
        }

        FileReader.appendToFile("", "src/app.py");
        FileReader.appendToFile("cmu_graphics.run()", "src/app.py");
        
    }
    
}
