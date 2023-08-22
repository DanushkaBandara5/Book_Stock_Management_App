package lk.ijse.dep10.app.business.util;

import lk.ijse.dep10.app.dao.custom.BookDAO;
import lk.ijse.dep10.app.dto.BookDTO;
import lk.ijse.dep10.app.entity.Book;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {
    private ModelMapper mapper;
public Transformer(ModelMapper map){
    this.mapper=map;

}
public BookDTO fromEntity(Book book){
    return mapper.map(book,BookDTO.class);

}
public Book toEntity(BookDTO bookDTO){
    return mapper.map(bookDTO,Book.class);
}
}
