package edu.usj.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PetshopRepository extends CrudRepository<Petshop, Long>{
    List<Petshop> findAll();
    List<Petshop> findByNome(String nome);
    List<Petshop> findByNomeContainingIgnoreCase(String nome);
}
