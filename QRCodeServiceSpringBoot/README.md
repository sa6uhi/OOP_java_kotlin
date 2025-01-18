# QR Code Service

## Overview

The QR Code Service is a Spring Boot application that generates QR codes based on user input. The service provides an HTTP API that allows users to specify the contents of the QR code, the desired image type, size, and error correction level. This project leverages the ZXing library to create QR codes and demonstrates the use of Spring Boot for building RESTful services.

## Features

- **Generate QR Codes**: Users can generate QR codes in various formats (PNG, JPEG, GIF) by sending a request to the API.
- **Error Handling**: The application includes comprehensive error handling to ensure that invalid input is managed gracefully.
- **Health Check Endpoint**: A health check endpoint is available to verify the service status.

## Getting Started

To get started with the QR Code Service, follow these steps:

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/sa6uhi/QRCodeServiceSpringBoot
    cd QRCodeServiceSpringBoot
    ```

2. **Run the Application**:
   - You can run the application using your preferred Java IDE or from the command line by executing the `Application` class.
   - Ensure you have the necessary dependencies, including Spring Boot and ZXing.

3. **API Endpoints**:
   - **Health Check**: 
     - **GET** `/api/health`
     - Returns HTTP status 200 OK.
   - **Generate QR Code**:
     - **GET** `/api/qrcode`
     - **Parameters**:
       - `contents`: The content to encode in the QR code (required).
       - `type`: The image format (default: `png`, supported: `png`, `jpeg`, `gif`).
       - `size`: The size of the QR code in pixels (default: `250`, must be between `150` and `350`).
       - `correction`: The error correction level (default: `L`, supported: `L`, `M`, `Q`, `H`).
     - **Response**: Returns the generated QR code image.

## Error Handling

The service provides meaningful error messages for the following scenarios:
- Invalid image size (must be between 150 and 350 pixels).
- Unsupported image type (only PNG, JPEG, and GIF formats are allowed).
- Empty content (contents cannot be null or blank).
- Invalid error correction level (permitted levels are L, M, Q, H).

## Technologies Used

- **Spring Boot**: For building the RESTful API.
- **ZXing**: For generating QR codes.
- **Java**: For implementing the application logic.

## Future Development

This project is open for future enhancements, including:
- Support for additional image formats.
- Improved error handling and logging mechanisms.
- A user interface for easier interaction with the QR code generation process.

## Get Help

If you have any questions or need assistance with the project, feel free to open an issue on the [GitHub repository](https://github.com/sa6uhi/QRCodeServiceSpringBoot/issues). Your feedback is always welcome!

---

Thank you for checking out the QR Code Service! Enjoy generating your QR codes, and I look forward to your contributions!
