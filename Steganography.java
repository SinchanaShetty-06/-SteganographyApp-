import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Steganography {

    public static void encode(String inputImagePath, String message, String password, String outputImagePath) throws Exception {
        BufferedImage image = ImageIO.read(new File(inputImagePath));
        int width = image.getWidth(), height = image.getHeight();
        int msgIndex = 0, charBitIndex = 7;

        // Add a simple password protection marker
        message = password + ":" + message + "~"; // ":" separates password from message, "~" is the end marker

        for (int y = 0; y < height && msgIndex < message.length(); y++) {
            for (int x = 0; x < width && msgIndex < message.length(); x++) {
                int rgb = image.getRGB(x, y);
                int blue = rgb & 0xff;
                int bit = (message.charAt(msgIndex) >> charBitIndex) & 1;
                blue = (blue & 0xFE) | bit;
                int newRGB = (rgb & 0xFFFFFF00) | blue;
                image.setRGB(x, y, newRGB);
                charBitIndex--;
                if (charBitIndex < 0) {
                    charBitIndex = 7;
                    msgIndex++;
                }
            }
        }

        ImageIO.write(image, "png", new File(outputImagePath));
        System.out.println("Message encoded into image.");
    }

    public static void decode(String imagePath, String enteredPassword) throws Exception {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth(), height = image.getHeight();
        StringBuilder decoded = new StringBuilder();
        int currentChar = 0, bitCount = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int lsb = rgb & 1;
                currentChar = (currentChar << 1) | lsb;
                bitCount++;
                if (bitCount == 8) {
                    if (currentChar == '~') {
                        String fullMessage = decoded.toString();
                        if (fullMessage.contains(":")) {
                            String[] parts = fullMessage.split(":", 2);
                            String embeddedPassword = parts[0];
                            String hiddenMessage = parts[1];
                            if (enteredPassword.equals(embeddedPassword)) {
                                System.out.println("Decoded message: " + hiddenMessage);
                            } else {
                                System.out.println("Incorrect password. Cannot decode message.");
                            }
                        } else {
                            System.out.println("Corrupted or invalid message.");
                        }
                        return;
                    }
                    decoded.append((char) currentChar);
                    bitCount = 0;
                    currentChar = 0;
                }
            }
        }
        System.out.println("No message found.");
    }