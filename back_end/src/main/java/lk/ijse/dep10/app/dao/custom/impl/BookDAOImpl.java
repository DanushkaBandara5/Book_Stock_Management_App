package lk.ijse.dep10.app.dao.custom.impl;

import lk.ijse.dep10.app.dao.custom.BookDAO;
import lk.ijse.dep10.app.entity.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static lk.ijse.dep10.app.dao.mapper.Mapper.BOOK_ROW_MAPPER;
@Repository
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;

    }
    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("Select count(*) from book", Long.class);
    }

    @Override
    public Book save(Book entity) throws Exception {
        jdbcTemplate.update("insert into book (isbn,description) values (?,?)",entity.getIsbn(),entity.getTitle());
        return entity;
    }

    @Override
    public void update(Book entity) throws Exception {
        jdbcTemplate.update("update book set description=? where isbn=?",entity.getIsbn(),entity.getTitle());


    }

    @Override
    public void deleteById(String pk) throws Exception {
        jdbcTemplate.update("delete from book where id=?",pk);

    }

    @Override
    public Optional<Book> findById(String pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM book WHERE isbn=?", BOOK_ROW_MAPPER, pk));
        }catch (DataAccessException exp){
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() throws Exception {
        return jdbcTemplate.query("select * from book",BOOK_ROW_MAPPER);
    }

    @Override
    public boolean existsById(String pk) throws Exception {
        return findById(pk).isPresent();
    }
}
