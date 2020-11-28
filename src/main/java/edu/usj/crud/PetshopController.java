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
public class PetshopController {

    @Autowired
    PetshopRepository petshopRepository;

    @GetMapping(value="/indexPetshop")
    public ModelAndView getListaTodosPetshop() {
        
       List<Petshop> lista = petshopRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("indexPetshop");    
        modelAndView.addObject("lista", lista);  
        return modelAndView;
    }
    
    @GetMapping(value="/cadastroPetshop")
    public ModelAndView getCadastro() {
        Petshop petshop = new Petshop();
        
        ModelAndView modelAndView = new ModelAndView("cadastroPetshop");   
        modelAndView.addObject("petshop", petshop);
        return modelAndView;
    }

    @GetMapping(value="detalhesPetshop/{id}")
    public ModelAndView getMostrarPorId(@PathVariable Long id) {

        //ler do banco
        Petshop petshop = petshopRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("detalhesPetshop");

        //acrescentar na modelAndView
        modelAndView.addObject("petshop", petshop);
        return modelAndView;
    }

    @PostMapping(value="/adicionarPetshop")
    public String postAdicionaPetshop(
        Petshop petshop
    ) {
        petshopRepository.save(petshop);
        return "redirect:/indexPetshop";
    }
    
    @GetMapping(value="deletarPetshop/{id}")
    public String getDeletar(@PathVariable Long id) {
        petshopRepository.deleteById(id);
        return "redirect:/indexPetshop";
    }
    
    @GetMapping(value="editarPetshop/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {

        Petshop petshop = petshopRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastroPetshop");
        modelAndView.addObject("petshop", petshop);
        return modelAndView;
    }
    
    
    @GetMapping(value="pesquisarPetshop")
    public String getPesquisar() {
        return "pesquisarPetshop";
    }

    @PostMapping(value="/pesquisarPetshop")
    public ModelAndView postMethodName(@RequestParam String nome) {
        List<Petshop> lista = petshopRepository.findByNomeContainingIgnoreCase(nome);
        
        ModelAndView modelAndView = new ModelAndView("pesquisarPetshop");
        modelAndView.addObject("nome", nome);
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }

}
