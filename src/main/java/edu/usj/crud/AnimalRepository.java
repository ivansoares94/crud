package edu.usj.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long>{
    List<Animal> findAll();
    List<Animal> findByNome(String nome);
    List<Animal> findByNomeContainingIgnoreCase(String nome);
    List<Animal> findByTipoAndNomeContainingIgnoreCase(String tipo, String nome);
}
