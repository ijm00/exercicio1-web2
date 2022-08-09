package com.example.exercicio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 5, message = "Nome muito curto")
    @Size(max = 100, message = "Nome muito longo")
    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    @NotNull(message = "Idade não pode estar em branco")
    @Min(value = 18, message = "Idade deve ser maior ou igual a 18")
    private Integer age;

    @NotBlank
    @Pattern(regexp = "^([A-Z]{2})([1-9]{9})$", message = "Não é um VAT Number válido. Deve seguir o padrão XX999999999.")
    private String vatNumber;

    @NotBlank(message = "E-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    private String email;
}
