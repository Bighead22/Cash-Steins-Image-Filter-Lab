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
    public void makelow() {
       
       
        Pixel[][] tempPixels = getPixels();

        for (int row = 0; row < tempPixels.length; row++) {
            for (int col = 0; col < tempPixels[0].length; col++) {
            
                    int c = 50;
                
                   
                   tempPixels[row][col].setBlue(255*(int)((tempPixels[row][col].getBlue()/255.0)+0.5));
                   tempPixels[row][col].setRed(255*(int)((tempPixels[row][col].getGreen()/255.0)+0.5));
                   tempPixels[row][col].setGreen(255*(int)((tempPixels[row][col].getRed()/255.0)+0.5));

                   if(tempPixels[row][col].getBlue()==255){
                    int r = (int)(Math.random()*Math.random()*50)+50+c;
                    int b = (int)(Math.random()*Math.random()*50)+50+c;
                    int g = (int)(Math.random()*Math.random()*50)+50+c;
                    tempPixels[row][col].setBlue(b);
                    tempPixels[row][col].setRed(r);
                    tempPixels[row][col].setGreen(g);
                   } else
                   if(tempPixels[row][col].getGreen()==255){
                    int r = (int)(Math.random()*Math.random()*50)+100+c;
                    int b = (int)(Math.random()*Math.random()*50)+100+c;
                    int g = (int)(Math.random()*Math.random()*50)+100+c;
                    tempPixels[row][col].setBlue(b);
                    tempPixels[row][col].setRed(r);
                    tempPixels[row][col].setGreen(g);
                   } else
                   if(tempPixels[row][col].getRed()==255){
                    int r = (int)(Math.random()*Math.random()*50)+150+c;
                    int b = (int)(Math.random()*Math.random()*50)+150+c;
                    int g = (int)(Math.random()*Math.random()*50)+150+c;
                    tempPixels[row][col].setBlue(b);
                    tempPixels[row][col].setRed(r);
                    tempPixels[row][col].setGreen(g);
                   } else {
                    int r = (int)(Math.random()*Math.random()*50)+c;
                    int b = (int)(Math.random()*Math.random()*50)+c;
                    int g = (int)(Math.random()*Math.random()*50)+c;
                    tempPixels[row][col].setBlue(b);
                    tempPixels[row][col].setRed(r);
                    tempPixels[row][col].setGreen(g);
                   }


            
                    
            }        
        }
    }
    
}
