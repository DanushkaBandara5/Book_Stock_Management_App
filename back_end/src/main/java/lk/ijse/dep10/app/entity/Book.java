package lk.ijse.dep10.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements SuperEntity {
    private String isbn;
    private String Title;
    private int qty;


}
