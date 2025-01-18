# Car Rental Management System

## Overview

The Car Rental Management System is a web application built with Spring Boot that allows users to manage car rentals efficiently. This project includes a well-structured database with three main tables: `CAR`, `CUSTOMER`, and `COMPANY`. Each table plays a crucial role in managing car rental information.

- **CAR Table**: Stores details about the cars available for rent, including their specifications and availability status.
- **CUSTOMER Table**: Holds information about customers, including their personal details and rented car ID. When a customer rents a car, the `RENTED_CAR_ID` field is updated accordingly.
- **COMPANY Table**: Contains information about the rental companies, allowing users to manage multiple companies and their respective fleets.

## Getting Started

To get started with the project, follow these steps:

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/sa6uhi/CarSharingSpringBoot
    cd CarSharingSpringBoot
    ```

2. **Set Up H2 Database**: 
   - This project uses H2 as the database. You can easily set it up by configuring the application properties.
   - The H2 database allows for easy data management and is lightweight, making it ideal for development and testing.

3. **Run the Application**: 
   - You can run the application using your IDE or by executing the following command in the terminal:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the Application**:
   - Once the application is running, you can access it at `http://localhost:8080`.

## Future Development

This project is open for future development and improvements. Whether it's adding new features, enhancing existing functionality, or optimizing performance, contributions are welcome! The project aims to evolve based on user feedback and needs.

## Technologies Used

- **Spring Boot**: For building the web application.
- **H2 Database**: For lightweight database management.
- **Thymeleaf**: For rendering dynamic web pages.
- **Maven**: For managing project dependencies.

## Get Help

If you have any questions or need assistance with the project, feel free to open an issue on the [GitHub repository](https://github.com/sa6uhi/CarSharingSpringBoot/issues). I am here to help!

---

Thank you for checking out the Car Rental Management System. We hope you find it useful and look forward to your contributions!
