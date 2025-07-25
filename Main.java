import java.util.Scanner;

public class SteganographyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Steganography Tool");
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        System.out.print("Choose an option (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        try {
            if (choice == 1) {
                System.out.print("Enter input image path: ");
                String inputImagePath = scanner.nextLine();

                System.out.print("Enter message to hide: ");
                String message = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                System.out.print("Enter output image path (e.g., output.png): ");
                String outputImagePath = scanner.nextLine();

                Steganography.encode(inputImagePath, message, password, outputImagePath);
            } else if (choice == 2) {
                System.out.print("Enter image path to decode from: ");
                String imagePath = scanner.nextLine();

                System.out.print("Enter password to decode: ");
                String password = scanner.nextLine();

                Steganography.decode(imagePath, password);
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
