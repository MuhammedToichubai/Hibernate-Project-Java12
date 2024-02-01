package mukhammed;

import mukhammed.entities.Address;
import mukhammed.entities.Company;
import mukhammed.entities.Programmer;
import mukhammed.entities.Project;
import mukhammed.services.AddressService;
import mukhammed.services.CompanyService;
import mukhammed.services.ProgrammerService;
import mukhammed.services.ProjectService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scannerS = new Scanner(System.in);
        Scanner scannerN = new Scanner(System.in);
        // Services
        AddressService addressService = new AddressService();
        CompanyService companyService = new CompanyService();
        ProjectService projectService = new ProjectService();
        ProgrammerService programmerService = new ProgrammerService();

        while (true) {
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
                                       
                         "Programmers"
                    20.  Save 
                    21.  Assign Programmer to Project
                    22.  Assign Programmers to Project
                    23.  Delete 
                    24.  Update 
                    25.  Find by id
                    26.  Find all
                    27.  Group Programmers By Company name
                                   
                    """);

            switch (scannerN.nextInt()) {
                // Address CRUD
                case 1 -> {
                    System.out.println(addressService.save(new Address("Kyrgyzstan")));
                }
                case 2 -> {
                    System.out.print("Write address id for delete: ");
                    String message = addressService.deleteById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 3 -> {
                    System.out.print("Write address id for update: ");
                    System.out.println(addressService.update(scannerN.nextLong(), new Address("USA")));
                }
                case 4 -> {
                    System.out.print("Write address id for find: ");
                    Address address = addressService.findById(scannerN.nextLong());
                    System.out.println(address);
                }
                case 5 -> {
                    addressService.findAll().forEach(System.out::println);
                }
                // Company CRUD
                case 6 -> {
                    System.out.println(companyService.save(new Company("Peaksoft")));
                }
                case 7 -> {
                    System.out.print("Write company id for delete: ");
                    String message = companyService.deleteById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 8 -> {
                    System.out.print("Write company id for update: ");
                    System.out.println(companyService.update(scannerN.nextLong(), new Company("Google")));
                }
                case 9 -> {
                    System.out.print("Write company id for find: ");
                    Company company = companyService.findById(scannerN.nextLong());
                    System.out.println(company);
                }
                case 10 -> {
                    companyService.findAll().forEach(System.out::println);
                }
                case 11 -> {
                    System.out.print("Write address id: ");
                    Long addressId = scannerN.nextLong();
                    System.out.print("Write company id: ");
                    Long companyId = scannerN.nextLong();
                    String message = companyService.assignCompanyToAddress(addressId, companyId);
                    System.out.println(message);
                }
                case 12 -> {
                    System.out.println("Write address id: ");
                    Long addressId = scannerN.nextLong();
                    Company company = companyService.findCompanyByAddressId(addressId);
                    System.out.println(company);
                }
                // Project CRUD
                case 13 -> {   //sava
                    System.out.println(projectService.save(Project.builder().title("LMS").build()));
                }
                case 14 -> {   //save 2
                    System.out.print("Write company id, for assign new project: ");
                    System.out.println(projectService.save(scannerN.nextLong(), new Project("TRACK")));
                }
                case 15 -> {  //assign
                    System.out.print("Write company id: ");
                    Long companyId = scannerN.nextLong();
                    System.out.print("Write project id: ");
                    Long projectId = scannerN.nextLong();
                    String message = projectService.assignProjectToCompany(companyId, projectId);
                    System.out.println(message);
                }
                case 16 -> {    //delete
                    System.out.println("Write project id: ");
                    System.out.println(projectService.deleteById(scannerN.nextLong()));
                }
                case 17 -> {   //update
                    System.out.print("Write project id: ");
                    String result = projectService.update(
                            scannerN.nextLong(),
                            Project.builder().title("Instagram").build()
                    );
                    System.out.println(result);
                }
                case 18 -> { //findById
                    System.out.print("Write project id: ");
                    try {
                        System.out.println(projectService.findById(scannerN.nextLong()));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 19 -> { //findAll
                    projectService.findAll().forEach(System.out::println);
                }
                //Programmer CRUD
                case 20 -> { //save
                    String message = programmerService.save(
                            Programmer.builder()
                                    .fullName("Mukhammed Asantegin")
                                    .email("mukhammed.codes@gmail.com")
                                    .build()
                    );
                    System.out.println(message);

                }
                case 21 -> { //assign programmer to project
                    System.out.print("Write programmer id: ");
                    Long programmerId = scannerN.nextLong();
                    System.out.print("Write project id: ");
                    Long projectId = scannerN.nextLong();
                    String message =
                            programmerService.assignProgrammerToProject(programmerId, projectId);

                }
                case 22 -> { //assign programmers to project
                    List<Long> programmersIds = List.of(2L, 3L, 4L);
                    System.out.println(programmerService.assignProgrammersToProject(programmersIds, 5L));


                }
                case 23 -> { //delete
                    System.out.print("Write programmer id for delete: ");
                    String message = programmerService.deleteById(scannerN.nextLong());
                    System.out.println(message);

                }
                case 24 -> { //update
                    System.out.print("Write programmer id: ");
                    String message =
                            programmerService.update(
                                    scannerN.nextLong(),
                                    Programmer.builder()
                                            .fullName("Amigos code")
                                            .email("a.code@gmail.com")
                                            .build());
                    System.out.println(message);
                }
                case 25 -> { //findById
                    System.out.print("Write programmer id: ");
                    try {
                        System.out.println(programmerService.findById(scannerN.nextLong()));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                }
                case 26 -> { //findAll
                    programmerService.findAll().forEach(System.out::println);

                }
                case 27 ->{
                    Map<String, List<Programmer>> groupProgrammers = programmerService.groupProgrammersByCompanyName();
                    System.out.println(groupProgrammers);
                }
            }
        }
    }

}
