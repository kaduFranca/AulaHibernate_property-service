package DTOs;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
public class PropertyDto {

    @NotBlank
    private String nome;
    @CPF
    private Integer cpf;
    @Email
    private String email;
    @NotBlank
    private LocalDate dataNascimento;

    public PropertyDto() {
    }
}
