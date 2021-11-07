package com.devduart.cursomc.services.validation;


import com.devduart.cursomc.domain.enums.TipoCliente;
import com.devduart.cursomc.dto.ClienteNewDTO;
import com.devduart.cursomc.resources.exceptions.FieldMessage;
import com.devduart.cursomc.services.validation.util.ValidacaoCpfCnpj;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator  implements  ConstraintValidator<ClienteInsert, ClienteNewDTO>{

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

        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
