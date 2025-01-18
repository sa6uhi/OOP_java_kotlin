package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDAOInterface {
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER (NAME) VALUES (?)";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM CUSTOMER";
    private static final String UPDATE_RENTED_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = ? WHERE ID = ?";
    private static final String RESET_RENTED_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID = ?";

    private Connection connection;

    public CustomerDAO() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL UNIQUE, " +
                    "RENTED_CAR_ID INT, " +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(String name) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CUSTOMER)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL_CUSTOMERS)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                Integer rentedCarId = rs.getInt("RENTED_CAR_ID");
                if (rs.wasNull()) rentedCarId = null;
                customers.add(new Customer(id, name, rentedCarId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("NAME");
                    Integer rentedCarId = rs.getInt("RENTED_CAR_ID");
                    if (rs.wasNull()) rentedCarId = null;
                    customer = new Customer(id, name, rentedCarId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void rentCar(int customerId, int carId) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_RENTED_CAR)) {
            stmt.setInt(1, carId);
            stmt.setInt(2, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnCar(int customerId) {
        try (PreparedStatement stmt = connection.prepareStatement(RESET_RENTED_CAR)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
