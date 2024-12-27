package md.Management_Client.repository;

import md.Management_Client.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, (row, index) -> {
            Long id = row.getLong("id");
            String name = row.getString("product_name");
            double price = row.getDouble("price");
            return new Product(id, name, price);
        });
    }

    public Product getProductById(Long id) {
        String sql = "select * from products where id = ?";
        return jdbcTemplate.queryForObject(sql, (row, index) -> {
            String name = row.getString("product_name");
            double price = row.getDouble("price");
            return new Product(id, name, price);
        }, id);
    }

    public List<Product> getProductByPrice(double price) {
        String sql = "select * from products where price > ?";
        return jdbcTemplate.query(sql, (row, index) -> {
            Long id = row.getLong("id");
            String name = row.getString("product_name");
            double priceIntern = row.getDouble("price");
            return new Product(id, name, priceIntern);
        }, price);
    }

    public void createProduct(Product product) {
        String sql = "insert into products (product_name, price) values (?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    public void updateProduct(Long id, Product product) {
        String sql = "update products set product_name = ?, price = ? where id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), id);
    }

    public void deleteProduct(Long id) {
        String sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean exists(Long id) {
        String sql = "select count(*) from products where id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
