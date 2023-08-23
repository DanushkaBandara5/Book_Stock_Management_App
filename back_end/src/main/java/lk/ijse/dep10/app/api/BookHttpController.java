package lk.ijse.dep10.app.api;

import lk.ijse.dep10.app.business.BookBO;
import lk.ijse.dep10.app.business.impl.BookBOImpl;
import lk.ijse.dep10.app.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/books")
@RestController

public class BookHttpController {
    private BookBO bookBO;
    public BookHttpController(BookBO bookBO) {
        this.bookBO=bookBO;
    }
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
//    public List<BookDTO> getAll() throws Exception {
//        return bookBO.getAllBooks();
//
//    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable String isbn) throws Exception {
        return bookBO.findBookByIsbn(isbn);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{isbn}")
    public void deleteByIsbn(@PathVariable String isbn) throws Exception {
        bookBO.deleteBook(isbn);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody @Valid BookDTO bookDTO) throws Exception {
        bookBO.saveBook(bookDTO);
    }
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookDTO bookDTO) throws Exception {
        return new ResponseEntity<>(bookBO.updateBook(bookDTO),HttpStatus.CREATED) ;
    }
    @GetMapping
    public List<BookDTO> searchBook(@RequestParam(name = "q",required = false) String query) throws Exception {
        if(query==null) query="";
            query="%"+query+"%";
        return bookBO.search(query);
    }


}
