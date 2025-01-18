package carsharing;

import java.util.List;

public interface CustomerDAOInterface {
    void addCustomer(String name);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void rentCar(int customerId, int carId);
    void returnCar(int customerId);
}