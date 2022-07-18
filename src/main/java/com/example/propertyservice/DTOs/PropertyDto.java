package com.example.propertyservice.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class PropertyDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @Email
    private String email;
    @NotNull
    private LocalDate dataNascimento;

    public PropertyDto() {
    }
}
