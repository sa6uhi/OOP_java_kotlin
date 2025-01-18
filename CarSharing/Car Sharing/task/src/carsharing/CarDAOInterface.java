package carsharing;

import java.util.List;

public interface CarDAOInterface {
    List<Car> getCarsByCompany(int companyId);
    void createCar(String name, int companyId);
    Car getCarById(int carId);
}
