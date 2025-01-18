package carsharing;

import java.util.List;

public interface CompanyDAOInterface {
    List<Company> getAllCompanies();
    void createCompany(String name);
    public Company getCompanyByCarId(int carId);
    Company getCompanyByCompanyId(int companyId);
}
