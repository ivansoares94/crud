package edu.usj.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AnimalController {
    

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping(value="/indexAnimal")
    public ModelAndView getListaTodos() {
        
       List<Animal> lista = animalRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("indexAnimal");    
        modelAndView.addObject("lista", lista);  
        return modelAndView;
    }

    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        Animal animal = new Animal();
        
        ModelAndView modelAndView = new ModelAndView("cadastro");   
        modelAndView.addObject("animal", animal);
        return modelAndView;
    }
    
    @GetMapping(value="detalhes/{id}")
    public ModelAndView getMostrarPorId(@PathVariable Long id) {

        //ler do banco
        Animal animal = animalRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("detalhes");

        //acrescentar na modelAndView
        modelAndView.addObject("animal", animal);
        return modelAndView;
    }

    @PostMapping(value="/adicionar")
    public String postAdicionaAnimal(
        Animal animal
    ) {
        animalRepository.save(animal);
        return "redirect:/indexAnimal";
    }
    
    @GetMapping(value="deletar/{id}")
    public String getDeletar(@PathVariable Long id) {
        animalRepository.deleteById(id);
        return "redirect:/indexAnimal";
    }
    
    @GetMapping(value="editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {

        Animal animal = animalRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("animal", animal);
        return modelAndView;
    }
    
    
    @GetMapping(value="pesquisar")
    public String getPesquisar() {
        return "pesquisar";
    }
    
    @PostMapping(value="/pesquisar")
    public ModelAndView postMethodName(@RequestParam String nome) {
        List<Animal> lista = animalRepository.findByNomeContainingIgnoreCase(nome);
        
        ModelAndView modelAndView = new ModelAndView("pesquisar");
        modelAndView.addObject("nome", nome);
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }
    
}
