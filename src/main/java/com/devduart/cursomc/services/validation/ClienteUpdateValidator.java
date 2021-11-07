package com.devduart.cursomc.services.validation;


import com.devduart.cursomc.domain.Cliente;
import com.devduart.cursomc.domain.enums.TipoCliente;
import com.devduart.cursomc.dto.ClienteDTO;
import com.devduart.cursomc.dto.ClienteNewDTO;
import com.devduart.cursomc.repositories.ClienteRepository;
import com.devduart.cursomc.resources.exceptions.FieldMessage;
import com.devduart.cursomc.services.validation.util.ValidacaoCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements  ConstraintValidator<ClienteUpdate, ClienteDTO>{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann){

    }

    @Override
    public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context){

        //Pega id da URI
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer urlID = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(objDTO.getEmail());
        if (aux != null && !aux.getId().equals(urlID)){
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
