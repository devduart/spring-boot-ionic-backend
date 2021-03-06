package com.devduart.cursomc.dto;

import com.devduart.cursomc.domain.Cliente;
import com.devduart.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private  Integer id;

    @NotEmpty(message = "Preencimento Obrigatorio")
    @Length(min = 5, max = 120, message = "O Tamanho deve ser entre 5 e 120 caracteres")
    private  String nome;

    @NotEmpty(message = "Preencimento Obrigatorio")
    @Email(message = "Email Invalido")
    private  String email;

    public ClienteDTO(){

    }

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
