package carsharing;

import java.util.List;

public class CompanyManager {
    private CompanyDAOInterface companyDAO  = new CompanyDAO();

    public void createCompany(String name) {
        companyDAO.createCompany(name);
    }

    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    public Company getCompanyByCarId(int carId) {
        return companyDAO.getCompanyByCarId(carId);
    }

    public Company getCompanyById(int companyId) {
        return companyDAO.getCompanyByCompanyId(companyId); // DAO'yu kullanarak güncel veriyi al
    }
}