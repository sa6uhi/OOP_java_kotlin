package carsharing;

import java.util.List;

public class CarManager {
    private CarDAOInterface carDAO = new CarDAO();

    public void createCarForCompany(String carName, int companyId) {
        carDAO.createCar(carName, companyId);
    }

    public List<Car> getCarsByCompanyId(int companyId) {
        return carDAO.getCarsByCompany(companyId);
    }

    public Car getCarById(int carId) {
        return carDAO.getCarById(carId);
    }
}
