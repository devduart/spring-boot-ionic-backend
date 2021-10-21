package com.devduart.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devduart.cursomc.domain.Categoria;
import com.devduart.cursomc.repositories.CategoriaRepository;
import com.devduart.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objet nao encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));
	}
}
