package entities;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column
    private String nome;
    @NotBlank
    @Column
    private Integer cpf;
    @NotBlank
    @Column
    private String email;
    @NotBlank
    @Column
    private String dataNascimento;
}
