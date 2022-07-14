package entities;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tb_suario")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 130)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private Integer cpf;
    @Column(nullable = false, unique = true, length = 70)
    private String email;
    @Column(nullable = false)
    private LocalDate dataNascimento;
}
