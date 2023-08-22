package lk.ijse.dep10.app.business.impl;

import lk.ijse.dep10.app.business.BookBO;
import lk.ijse.dep10.app.business.exceptions.DuplicateException;
import lk.ijse.dep10.app.business.exceptions.RecordNotFoundException;
import lk.ijse.dep10.app.business.util.Transformer;
import lk.ijse.dep10.app.dao.custom.BookDAO;
import lk.ijse.dep10.app.dto.BookDTO;
import lk.ijse.dep10.app.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BookBOImpl implements BookBO {
    private final BookDAO bookDAO;
    private final Transformer transformer;
    public BookBOImpl(BookDAO bookDAO, Transformer transformer){
        this.bookDAO=bookDAO;
        this.transformer=transformer;
    }
    @Override
    public List<BookDTO> getAllBooks() throws Exception {
        List<BookDTO> list =new ArrayList<>();
        for (Book book : bookDAO.findAll()) {
            list.add(transformer.fromEntity(book));
        }
        return list;
    }

    @Override
    public void saveBook(BookDTO bookDTO) throws Exception {
        if(bookDAO.existsById(bookDTO.getIsbn())){
           throw new DuplicateException("ISBN "+bookDTO.getIsbn()+" Already Exists");
        }
        bookDAO.save(transformer.toEntity(bookDTO));


    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) throws Exception {
        if(!bookDAO.existsById(bookDTO.getIsbn())){
            throw new DuplicateException("ISBN "+bookDTO.getIsbn()+" doesn't Exists");
        }
        bookDAO.update(transformer.toEntity(bookDTO));
        return bookDTO;
    }

    @Override
    public void deleteBook(String isbn) throws Exception {
        if(!bookDAO.existsById(isbn)){
            throw new RecordNotFoundException("ISBN "+isbn+" not Exists");
        }
        bookDAO.deleteById(isbn);


    }

    @Override
    public BookDTO findBookByIsbn(String isbn) throws Exception {
        return bookDAO.findById(isbn).map(transformer::fromEntity)
                .orElseThrow(()-> new RecordNotFoundException("ISBN "+isbn+" not Exists"));
    }
}
