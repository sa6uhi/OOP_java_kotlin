package carsharing;

import java.util.List;
import java.util.Scanner;

public class UIManager {

    private static final Scanner scanner = new Scanner(System.in);
    private CompanyManager companyManager = new CompanyManager();
    private CarManager carManager = new CarManager();
    private CustomerManager customerManager = new CustomerManager();

    public void start() {
        boolean isFirstTime = false;
        while (true) {
            if (isFirstTime) {   //  don't print empty line when program starts
                System.out.println();
            }
            isFirstTime = true;
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                managerMenu();
            } else if (choice == 2) {
                customerLogin();
            } else if (choice == 3) {
                createCustomer();
            }
        }
    }

    private void managerMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                showCompanyList();
            } else if (choice == 2) {
                createCompany();
            }
        }
    }

    private void customerLogin() {
        List<Customer> customers = customerManager.getAllCustomers();
        System.out.println();
        Customer selectedCustomer = chooseEntity(customers, "customer");

        if (selectedCustomer != null) {
            customerMenu(selectedCustomer);
        }
    }

    private void customerMenu(Customer customer) {
        while (true) {
            // System.out.println("'" + customer.getName() + "' customer:");
            System.out.println();
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                rentCar(customer);
            } else if (choice == 2) {
                returnRentedCar(customer);
            } else if (choice == 3) {
                myRentedCar(customer);
            }
        }
    }

    private void rentCar(Customer customer) {
        Customer updatedCustomer = customerManager.getCustomerById(customer.getId());
        if (updatedCustomer.getRentedCarId() != null) {
            System.out.println("You've already rented a car!");
            return;
        }

        List<Company> companies = companyManager.getAllCompanies();
        Company selectedCompany = chooseEntity(companies, "company");

        if (selectedCompany != null) {
            rentCarFromCompany(selectedCompany, updatedCustomer);
        }
    }

    private void rentCarFromCompany(Company company, Customer customer) {
        Customer updatedCustomer = customerManager.getCustomerById(customer.getId());
        Company updatedCompany = companyManager.getCompanyById(company.getId());  //// bu satir
        List<Car> availableCars = carManager.getCarsByCompanyId(updatedCompany.getId());

        if (availableCars.isEmpty()) {
            System.out.println("No available cars in the '" + updatedCompany.getName() + "' company.");
            return;
        }

        Car selectedCar = chooseEntity(availableCars, "car");

        if (selectedCar != null) {
            customerManager.rentCarToCustomer(updatedCustomer.getId(), selectedCar.getId());
            System.out.println("You rented '" + selectedCar.getName() + "'");
        }
    }

    private void returnRentedCar(Customer customer) {
        Customer updatedCustomer = customerManager.getCustomerById(customer.getId());
        System.out.println();
        if (updatedCustomer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
            return;
        }

        customerManager.returnRentedCar(updatedCustomer.getId());
        System.out.println("You've returned a rented car!");
    }

    private void myRentedCar(Customer customer) {
        Customer updatedCustomer = customerManager.getCustomerById(customer.getId());
        System.out.println();
        if (updatedCustomer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            Car rentedCar = carManager.getCarById(updatedCustomer.getRentedCarId());
            System.out.println("Your rented car: " + rentedCar.getName());
            Company company = companyManager.getCompanyByCarId(rentedCar.getId());
            System.out.println("Company: " + company.getName());
        }
    }

    private void chooseCompany() {
        int companyId = Integer.parseInt(scanner.nextLine());
        if (companyId == 0) {
            return;
        }

        Company selectedCompany = companyManager.getAllCompanies().stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);

        if (selectedCompany != null) {
            companyMenu(selectedCompany);
        } else {
            System.out.println("Invalid company ID. Please try again.");
        }
    }

    private void showCompanyList() {
        List<Company> companies = companyManager.getAllCompanies();

        System.out.println();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Choose the company:");
            for (Company company : companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
            chooseCompany();
        }
    }

    private void companyMenu(Company company) {
        boolean isFirstTime = true;

        while (true) {
            System.out.println();
            if (isFirstTime) {
                System.out.println("'" + company.getName() + "' company:");
                isFirstTime = false;
            }
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                showCarList(company);
            } else if (choice == 2) {
                createCar(company);
            }
        }
    }

    private void showCarList(Company company) {
        List<Car> cars = carManager.getCarsByCompanyId(company.getId());
        if (cars.isEmpty()) {
            System.out.println();
            System.out.println("The car list is empty!");
        } else {
            // System.out.println("'" + company.getName() + "' cars:");
            System.out.println();
            System.out.println("Car list:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println((i + 1) + ". " + cars.get(i).getName());
            }
        }
    }

    private void createCustomer() {
        System.out.println();
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        customerManager.createCustomer(name);
        System.out.println("The customer was added!");
    }

    private void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String companyName = scanner.nextLine();
        companyManager.createCompany(companyName);
        System.out.println("The company was created!");
    }

    private void createCar(Company company) {
        System.out.println();
        System.out.println("Enter the car name:");
        String carName = scanner.nextLine();
        carManager.createCarForCompany(carName, company.getId());
        System.out.println("The car was added!");
    }

    private <T> T chooseEntity(List<T> entityList, String entityType) {
        if (entityList.isEmpty()) {
            System.out.println("The " + entityType + " list is empty!");
            System.out.println();
            return null;
        }

        System.out.println("Choose a " + entityType + ":");
        for (int i = 0; i < entityList.size(); i++) {
            System.out.println((i + 1) + ". " + entityList.get(i).toString());
        }
        System.out.println("0. Back");

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) {
            return null;
        }

        return entityList.get(choice - 1);
    }

}