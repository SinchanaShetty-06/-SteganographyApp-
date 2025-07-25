# Steganography Tool – Java Project

This project is a simple Java-based steganography application that allows users to hide and retrieve secret text messages within image files using a password-based encryption approach.

## Features

- Encode text messages into PNG images
- Decode hidden messages using a password
- Command-line interface (CLI) for interaction
- Lightweight and easy to use

## Technologies Used

- Java (JDK 8+)
- `javax.imageio.ImageIO` for image processing
- `BufferedImage` for pixel manipulation
- Simple XOR-based encryption for message security

## How to Use

### Encoding a Message

1. Run the Java program.
2. Select option `1` when prompted.
3. Provide the path to the input image.
4. Enter the secret message you want to hide.
5. Set a password for encryption.
6. Enter the path to save the output (encoded) image.

The program will generate a new image file with the hidden message embedded.

### Decoding a Message

1. Run the Java program.
2. Select option `2` when prompted.
3. Provide the path to the image containing the hidden message.
4. Enter the correct password.

The program will decrypt and display the hidden message.

## Project Structure

Steganography/
├── Steganography.java
├── README.md
├── input.png (sample input image)
├── output.png (image with hidden message)

## How It Works

This tool hides text inside an image using basic steganography principles. Each character of the encrypted message is stored in the least significant bits (LSBs) of image pixels. XOR encryption adds an additional layer of security using a password.

## Author

Your Name  
Your GitHub Profile (optional)  
Your Email (optional)

## License

This project is open-source and free to use for educational or personal purposes.

