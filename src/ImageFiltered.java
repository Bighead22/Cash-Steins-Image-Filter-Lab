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

        FileReader.keepFirstLines("src/app.py", 9);
            
        Pixel[][] tempPixels = getPixels();

        int height = tempPixels.length;
        int width = tempPixels[0].length;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                
                // Mirroring original X/Col, then rotating 90 CCW:
                int rotatedRow = col; 
                int rotatedCol = row;
                
                int i = 7;
                int j = 10;
                int SC = (int) (Math.random() * 3);

                if (SC == 0) {
                    FileReader.appendToFile(
                        "Circle(" + (rotatedRow * 10) + ", " + (rotatedCol * 10) + ", "+ i +", fill=rgb(" 
                        + tempPixels[row][col].getRed() + ", " 
                        + tempPixels[row][col].getGreen() + ", " 
                        + tempPixels[row][col].getBlue() + "))", 
                        "src/app.py"
                    );
                } else {
                    FileReader.appendToFile(
                        "Star(" + (rotatedRow * 10) + ", " + (rotatedCol * 10) + ", "+ j +", 8, fill=rgb(" 
                        + tempPixels[row][col].getRed() + ", " 
                        + tempPixels[row][col].getGreen() + ", " 
                        + tempPixels[row][col].getBlue() + "), roundness = 88)", 
                        "src/app.py"
                    );
                }   
            }
        }

        FileReader.appendToFile("", "src/app.py");
        FileReader.appendToFile("cmu_graphics.run()", "src/app.py");
            
    }
    
}
