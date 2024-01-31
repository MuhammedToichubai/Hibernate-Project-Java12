package mukhammed;

import mukhammed.entities.Address;
import mukhammed.entities.Company;
import mukhammed.entities.Project;
import mukhammed.services.AddressService;
import mukhammed.services.CompanyService;
import mukhammed.services.ProjectService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Scanner scannerS = new Scanner(System.in);
        Scanner scannerN = new Scanner(System.in);
        // Services
        AddressService addressService = new AddressService();
        CompanyService companyService = new CompanyService();
        ProjectService projectService = new ProjectService();

        while (true){
            System.out.println("""
                              "Address"
                    1.  Save 
                    2.  Delete 
                    3.  Update 
                    4.  Find by id
                    5.  Find all
                               "Company"
                    6.  Save 
                    7.  Delete 
                    8.  Update 
                    9.  Find by id
                    10. Find all
                    11. Assign Company to Address
                    12. Find Company By Address Id
                              "Project"
                    13.  Save Project
                    14.  Save Project to Company
                    15.  Assign Project to Company
                    16.  Delete 
                    17.  Update 
                    18.  Find by id
                    19.  Find all
               
                    """);

            switch (scannerN.nextInt()){
                // Address CRUD
                case 1 ->{
                    System.out.println(addressService.save(new Address("Kyrgyzstan")));
                }
                case 2 ->{
                    System.out.print("Write address id for delete: ");
                    String message = addressService.deleteById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 3 ->{
                    System.out.print("Write address id for update: ");
                    System.out.println(addressService.update(scannerN.nextLong(), new Address("USA")));
                }
                case 4 ->{
                    System.out.print("Write address id for find: ");
                    Address address = addressService.findById(scannerN.nextLong());
                    System.out.println(address);
                }
                case 5 ->{
                    addressService.findAll().forEach(System.out::println);
                }
                // Company CRUD
                case 6 ->{
                    System.out.println(companyService.save(new Company("Peaksoft")));
                }
                case 7 ->{
                    System.out.print("Write company id for delete: ");
                    String message = companyService.deleteById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 8 ->{
                    System.out.print("Write company id for update: ");
                    System.out.println(companyService.update(scannerN.nextLong(), new Company("Google")));
                }
                case 9 ->{
                    System.out.print("Write company id for find: ");
                    Company company = companyService.findById(scannerN.nextLong());
                    System.out.println(company);
                }
                case 10 ->{
                    companyService.findAll().forEach(System.out::println);
                }
                case 11 ->{
                    System.out.print("Write address id: ");
                    Long addressId = scannerN.nextLong();
                    System.out.print("Write company id: ");
                    Long companyId = scannerN.nextLong();
                    String message = companyService.assignCompanyToAddress(addressId, companyId);
                    System.out.println(message);
                }
                case 12 ->{
                    System.out.println("Write address id: ");
                    Long addressId = scannerN.nextLong();
                    Company company = companyService.findCompanyByAddressId(addressId);
                    System.out.println(company);
                }
                // Project CRUD
                case 13 ->{
                    System.out.println(projectService.save(Project.builder().title("LMS").build()));
                }
                case 14 ->{
                    System.out.print("Write company id, for assign new project: ");
                    System.out.println(projectService.save(scannerN.nextLong(), new Project("TRACK")));
                }
            }
        }

    }
}
