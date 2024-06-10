package com.example.demo.Repository;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{

}

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ProductRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Product> findAll() {
//        return jdbcTemplate.query(
//                "SELECT * FROM products",
//                new BeanPropertyRowMapper<>(Product.class)
//        );
//    }
//
//    public Product findById(Long id) {
//        try {
//            return jdbcTemplate.queryForObject(
//                    "SELECT * FROM products WHERE id = ?",
//                    new Object[]{id},
//                    new BeanPropertyRowMapper<>(Product.class)
//            );
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }
//
//    public int save(Product product) {
//        return jdbcTemplate.update(
//                "INSERT INTO products (title, description, city, author, price) VALUES (?, ?, ?, ?, ?)",
//                product.getTitle(), product.getDescription(), product.getCity(), product.getAuthor(), product.getPrice()
//        );
//    }
//
//    public int update(Product product) {
//        return jdbcTemplate.update(
//                "UPDATE products SET title=?, description=?, city=?, author=?, price=? WHERE id=?",
//                product.getTitle(), product.getDescription(), product.getCity(), product.getAuthor(), product.getPrice(), product.getId()
//        );
//    }
//
//    public int deleteById(Long id) {
//        return jdbcTemplate.update(
//                "DELETE FROM products WHERE id = ?",
//                id
//        );
//    }
//}