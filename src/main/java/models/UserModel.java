package models;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tb_suario")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 130)
    private String nome;
    @Column(nullable = false, unique = true, length = 11)
    private Integer cpf;
    @Column(nullable = false, unique = true, length = 70)
    private String email;
    @Column(nullable = false)
    private LocalDate dataNascimento;
}
