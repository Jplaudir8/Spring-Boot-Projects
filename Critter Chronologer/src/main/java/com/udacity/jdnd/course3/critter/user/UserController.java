package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.domain.Customer;
import com.udacity.jdnd.course3.critter.domain.Employee;
import com.udacity.jdnd.course3.critter.domain.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerServiceImpl;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.EmployeeServiceImpl;
import com.udacity.jdnd.course3.critter.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerServiceImpl customerService;
    PetServiceImpl petService;
    EmployeeServiceImpl employeeService;

    public UserController(CustomerServiceImpl customerService, PetServiceImpl petService, EmployeeServiceImpl employeeService) {
        this.customerService = customerService;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        // Saving customer data and returning it
        Customer createdCustomer = customerService.save(convertCustomerDTOToCustomer(customerDTO));

        // Setting the id to customer DTO so that it is also presented to the front end side.
        customerDTO.setId(createdCustomer.getId());
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream().map(this::convertCustomerToCustomerDTO).collect(Collectors.toList());
        return customerDTOs;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        Customer customer = pet.getCustomer();
        CustomerDTO customerDTO = convertCustomerToCustomerDTO(customer);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.save(convertEmployeeDTOToEmployee(employeeDTO));
        employeeDTO.setId(createdEmployee.getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        Employee retrievedEmployee = employeeService.getEmployeeById(employeeId);
        EmployeeDTO employeeDTO = convertEmployeeToEmployeeDTO(retrievedEmployee);
        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private List<Long> getCustomerPetIds(Customer customer) {
        List<Long> petIds = new ArrayList<>();

        if (!customer.getPets().isEmpty()) {
            for (Pet pet : customer.getPets()) {
                petIds.add(pet.getId());
            }
            return petIds;
        }
        return null;
    }

    /**
     * Helper Method
     * @param customer  customer object to be converted
     * @return DTO form of Customer
     */
    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNote(customer.getNote());

        List<Long> petIds = new ArrayList<>();

        if (!customer.getPets().isEmpty()) {
            petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    /**
     * Helper Method
     * @param customerDTO   customerDTO object to be converted
     * @return  entity form of Customer
     */
    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNote(customerDTO.getNote());

        if (!customerDTO.getPetIds().isEmpty()) {
            List<Long> petIds = customerDTO.getPetIds();
            List<Pet> pets = petService.getAllPetsByIds(petIds);
            customer.setPets(pets);
        }
        return customer;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        return employee;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());
        return employeeDTO;
    }

}
