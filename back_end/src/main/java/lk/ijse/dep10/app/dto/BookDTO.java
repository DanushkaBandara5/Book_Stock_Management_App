package lk.ijse.dep10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    @NotBlank(message ="ISBN cannot be Empty")
    private String isbn;
    @NotBlank(message ="Title cannot be Empty")
    private String title;
}
