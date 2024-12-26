package md.Management_Client.controller;

import md.Management_Client.entity.Customer;
import md.Management_Client.exceptions.AlreadyExistsException;
import md.Management_Client.exceptions.NotFoundException;
import md.Management_Client.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        if (!customerRepository.exists(id))
            throw new NotFoundException();
        return customerRepository.getCustomerById(id);
    }

    @GetMapping("/customers/email")
    public Customer getCustomerByEmail(@RequestParam("email") String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @PostMapping("/customers")
    public void createCustomer(@RequestBody Customer customer) {
        if(customerRepository.exists(customer.getId()))
            throw new AlreadyExistsException();
        customerRepository.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerRepository.updateCustomer(id, customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteCustomer(id);
    }
}
