package com.example.propertyservice.models;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
@Builder
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String email;
    @Column
    private LocalDate dataNascimento;
}
