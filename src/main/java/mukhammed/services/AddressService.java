package mukhammed.services;

import mukhammed.dao.AddressDao;
import mukhammed.entities.Address;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
public class AddressService {
    private final AddressDao addressDao = new AddressDao();

    public String save(Address newAddress) {
        return addressDao.save(newAddress) ?
                "Successfully saved address with country: "+newAddress.getCountry():
                "Address not saved !";
    }

    public String deleteById(Long id) {
        Address address = findById(id);
        if (address != null ) {
            addressDao.deleteById(id);
        }
        else return "Address not deleted !";
        return "Successfully deleted address with id: "+id ;
    }

    public String update(Long id, Address newAddress) {
        return addressDao.update(id, newAddress) ?
                "Successfully updated address with id: "+id :
                "Address with id: "+id+" not found!";
    }

    public Address findById(Long id) {
        Address address = null;
        try {
           address = addressDao.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Address with id: " + id + " not found!"));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        return address;
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }
}
