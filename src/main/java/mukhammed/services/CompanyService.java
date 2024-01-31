package mukhammed.services;

import mukhammed.dao.AddressDao;
import mukhammed.dao.CompanyDao;
import mukhammed.entities.Address;
import mukhammed.entities.Company;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
public class CompanyService {
    private final CompanyDao companyDao = new CompanyDao();
    private final AddressDao addressDao = new AddressDao();

    public String save(Company newCompany) {
        return companyDao.save(newCompany) ?
                "Successfully saved address with name: " + newCompany.getName() :
                "Address not saved !";
    }

    public String deleteById(Long id) {
        return companyDao.deleteById(id) ?
                "Successfully deleted company with id: " + id :
                "Company with id: " + id + " not found!";
    }

    public String update(Long id, Company newCompany) {
        return companyDao.update(id, newCompany) ?
                "Successfully updated company with id: " + id :
                "Company with id: " + id + " not found!";
    }

    public Company findById(Long id) {
        Company company = null;
        try {
            company = companyDao.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Company with id: " + id + " not found!"));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public String assignCompanyToAddress(Long addressId, Long companyId) {
        Address address = addressDao.findById(addressId).orElse(null);
        Company company = findById(companyId);
        if (address == null) return "Address with id: "+addressId+ " not found!";
        if (company != null){
            companyDao.assignCompanyToAddress(addressId, companyId);
            return "Company: "+company.getName()+" assign to address: "+address.getCountry();
        }
        return "Not assign";
    }

    public Company findCompanyByAddressId(Long addressId) {
        Company company = null;
        try {
            addressDao.findById(addressId).orElseThrow(() ->
                    new RuntimeException("Address with id: "+addressId+ " not found!"));
            company = companyDao.findCompanyByAddressId(addressId).get();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }
}
