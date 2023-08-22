package lk.ijse.dep10.app.dao.mapper;

import lk.ijse.dep10.app.entity.Book;
import org.springframework.jdbc.core.RowMapper;

public class Mapper {
    public static RowMapper<Book> BOOK_ROW_MAPPER=(rs,no)->{
        String isbn = rs.getObject("isbn", String.class);
        String title = rs.getObject("title", String.class);
        return new Book(isbn,title);

    };
}
