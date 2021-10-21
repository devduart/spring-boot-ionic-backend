package com.devduart.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devduart.cursomc.domain.Categoria;
import com.devduart.cursomc.domain.Produto;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}