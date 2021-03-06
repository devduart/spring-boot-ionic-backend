package com.devduart.cursomc.services.validation;


import com.devduart.cursomc.domain.Cliente;
import com.devduart.cursomc.domain.enums.TipoCliente;
import com.devduart.cursomc.dto.ClienteNewDTO;
import com.devduart.cursomc.repositories.ClienteRepository;
import com.devduart.cursomc.resources.exceptions.FieldMessage;
import com.devduart.cursomc.services.validation.util.ValidacaoCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator  implements  ConstraintValidator<ClienteInsert, ClienteNewDTO>{

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann){

    }

    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context){

        List<FieldMessage> list = new ArrayList<>();

        if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !ValidacaoCpfCnpj.isValidCPF(objDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF INVALIDO"));
        }

        if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !ValidacaoCpfCnpj.isValidCNPJ(objDTO.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ INVALIDO"));
        }

        Cliente aux = repo.findByEmail(objDTO.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email","Email já existente"));
        }

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
