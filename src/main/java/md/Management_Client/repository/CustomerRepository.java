package md.Management_Client.repository;

import md.Management_Client.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("select * from customers", (row, index) -> {
            Long id = row.getLong("id");
            String name = row.getString("name");
            String email = row.getString("email");
            return new Customer (id, name, email);
        });
    }

    public Customer getCustomerById(Long id) {
        String sql = "select * from customers where id = ?";
        return jdbcTemplate.queryForObject(sql, (row, index) -> {
            String name = row.getString("name");
            String email = row.getString("email");
            return new Customer(id, name, email);
        }, id);
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "select * from customers where email = ?";
        return jdbcTemplate.queryForObject(sql, (row, index) -> {
            Long id = row.getLong("id");
            String name = row.getString("name");
            return new Customer(id, name, email);
        }, email);
    }

    public void createCustomer(Customer customer) {
        String sql = "insert into customers(name, email) values(?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getEmail());
    }

    public void updateCustomer(Long id, Customer customer) {
        String sql = "update customers set name = ?, email = ? where id = ?";
        jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), id);
    }

    public void deleteCustomer(Long id) {
        String sql = "delete from customers where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean exists(Long id) {
        String sql = "select count(*) from customers where id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
