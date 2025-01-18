package carsharing;

import java.util.List;

public class CustomerManager {

    private CustomerDAOInterface customerDAO = new CustomerDAO();

    public void createCustomer(String name) {
        customerDAO.addCustomer(name);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    public void rentCarToCustomer(int customerId, int carId) {
        customerDAO.rentCar(customerId, carId);
    }

    public void returnRentedCar(int customerId) {
        customerDAO.returnCar(customerId);
    }
}
