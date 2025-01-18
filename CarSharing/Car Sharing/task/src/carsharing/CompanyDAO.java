package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements CompanyDAOInterface {

    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY ORDER BY ID";
    private static final String INSERT_DATA = "INSERT INTO COMPANY (NAME) VALUES (?)";
    private static final String GET_COMPANY_BY_CAR_ID = "SELECT COMPANY.ID, COMPANY.NAME " +
            "FROM COMPANY " +
            "INNER JOIN CAR ON COMPANY.ID = CAR.COMPANY_ID " +
            "WHERE CAR.ID = ?";
    private static final String FIND_BY_COMPANY_ID = "SELECT * FROM COMPANY WHERE ID = ?";
    private Connection connection;

    public CompanyDAO() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL UNIQUE" +
                    ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                companies.add(new Company(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    @Override
    public void createCompany(String name) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_DATA)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company getCompanyByCarId(int carId) {
        return getCompanyById(carId, GET_COMPANY_BY_CAR_ID);
    }

    @Override
    public Company getCompanyByCompanyId(int companyId) {
        return getCompanyById(companyId, FIND_BY_COMPANY_ID);
    }

    private Company getCompanyById(int givenId, String query) {
        Company company = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, givenId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    company = new Company(id, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

}
