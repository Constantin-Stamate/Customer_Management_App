package md.Management_Client.repository;

import md.Management_Client.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private DefaultLifecycleProcessor lifecycleProcessor;

    public List<Order> getAllOrders() {
        String sql = "select * from orders";
        return jdbcTemplate.query(sql, (row, index) -> {
            Long id = row.getLong("id");
            String date = row.getString("order_date");
            double amount = row.getDouble("total_amount");
            Long idCustomer = row.getLong("id_customer");
            return new Order(id, date, amount, idCustomer);
        });
    }

    public Order getOrderById(Long id) {
        String sql = "select * from orders where id = ?";
        return jdbcTemplate.queryForObject(sql, (row, index) -> {
            String date = row.getString("order_date");
            double amount = row.getDouble("total_amount");
            Long idCustomer = row.getLong("id_customer");
            return new Order(id, date, amount, idCustomer);
        }, id);
    }

    public List<Order> getOrderByAmount(double amount) {
        String sql = "select * from orders where total_amount > ?";
        return jdbcTemplate.query(sql, (row, index) -> {
            Long id = row.getLong("id");
            String date = row.getString("order_date");
            double amountIntern = row.getDouble("total_amount");
            Long idCustomer = row.getLong("id_customer");
            return new Order(id, date, amountIntern, idCustomer);
        }, amount);
    }

    public void createOrder(Order order) {
        String sql = "insert into orders (date, amount, id_customer) values (?, ?, ?)";
        jdbcTemplate.update(sql, order.getDate(), order.getAmount(), order.getIdCustomer());
    }

    public void updateOrder(Long id, Order order) {
        String sql = "update orders set date = ?, amount = ?, id_customer = ? where id = ?";
        jdbcTemplate.update(sql, order.getDate(), order.getAmount(), order.getIdCustomer(), id);
    }

    public void deleteOrder(Long id) {
        String sql = "delete from orders where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean exists(Long id) {
        String sql = "select count(*) from orders where id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
