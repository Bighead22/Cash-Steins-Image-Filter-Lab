public class ImageRunner {
    public static void main(String[] args) throws Exception {

        //use a 40 pixle ny 40 pixle image

        String originalImage = "images/Rad.png";

        Image myImage = new Image(originalImage);
        myImage.display();

        ImageFiltered myBlueImage = new ImageFiltered(originalImage);
        myBlueImage.makePython();
        myBlueImage.display();

    }
}
