package com.devduart.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devduart.cursomc.domain.Pedido;
import com.devduart.cursomc.repositories.PedidoRepository;
import com.devduart.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objet nao encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName()));
	}
}
