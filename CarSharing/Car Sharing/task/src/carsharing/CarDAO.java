package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements CarDAOInterface {

    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    // private static final String SELECT_CARS_BY_COMPANY = "SELECT * FROM CAR WHERE COMPANY_ID = ? AND RENTED ORDER BY ID";
    private static final String SELECT_CARS_BY_COMPANY =
            "SELECT CAR.ID, CAR.NAME, CAR.COMPANY_ID " +
                    "FROM CAR " +
                    "LEFT JOIN CUSTOMER ON CAR.ID = CUSTOMER.RENTED_CAR_ID " +
                    "WHERE CAR.COMPANY_ID = ? AND CUSTOMER.RENTED_CAR_ID IS NULL " +
                    "ORDER BY CAR.ID";
    private static final String INSERT_CAR = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES (?, ?)";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM CAR WHERE ID = ?";

    private Connection connection;

    public CarDAO() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CAR (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL UNIQUE, " +
                    "COMPANY_ID INT NOT NULL, " +
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getCarsByCompany(int companyId) {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_CARS_BY_COMPANY)) {
            stmt.setInt(1, companyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    int companyIdFromDb = rs.getInt("COMPANY_ID");
                    cars.add(new Car(id, name, companyIdFromDb));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public void createCar(String name, int companyId) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CAR)) {
            stmt.setString(1, name);
            stmt.setInt(2, companyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCarById(int carId) {
        Car car = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_CAR_BY_ID)) {
            ps.setInt(1, carId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    int companyId = rs.getInt("COMPANY_ID");
                    car = new Car(id, name, companyId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

}
