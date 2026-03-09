public class ImageRunner {
    public static void main(String[] args) throws Exception {
        String originalImage = "images/astronaut.jpg";

        Image myImage = new Image(originalImage);
        myImage.display();

        ImageFiltered myBlueImage = new ImageFiltered(originalImage);
        myBlueImage.makelow();
        myBlueImage.display();

    }
}
