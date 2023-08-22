package lk.ijse.dep10.app.business;

import lk.ijse.dep10.app.dao.custom.BookDAO;
import lk.ijse.dep10.app.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookBO {
    List<BookDTO> getAllBooks() throws Exception;
    void saveBook(BookDTO bookDTO) throws Exception;
    BookDTO updateBook(BookDTO bookDTO)throws Exception;
    void deleteBook(String isbn) throws Exception;
    BookDTO findBookByIsbn(String isbn) throws Exception;

}
