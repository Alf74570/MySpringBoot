package com.campusnum.springboot.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.campusnum.springboot.dao.UserDAO;
import com.campusnum.springboot.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

	@Autowired
	private UserDAO userDAO;
	
	//Créer Utilisateur
	@GetMapping("/creer")
	public String userForm(Model model) {
		model.addAttribute("newuser", new User());
		return "newuser";
	}
	
	@PostMapping("/creer")
	public String userSubmit(@Valid @ModelAttribute("newuser") User user, BindingResult bindingResult ) {
		
		if (bindingResult.hasErrors()) {
			return "newuser";
		}
		
		userDAO.save(user);
		userDAO.findAll();
		return "result";
	}
	
	//Afficher Liste des Utilisateurs
	@GetMapping(value= "user")
	public String userList(Model model) {
		model.addAttribute("users", userDAO.findAll());
		
		return "list";
	}
	
	//Afficher Profil
	@GetMapping(value = "user/{id}")
    public String userProfil(@PathVariable Long id, Model model) {

        Optional<User> maybeUser = userDAO.findById(id);   
        		
		if(maybeUser.isPresent()) {
		model.addAttribute("user", maybeUser.get());
		
		return "profil";
		}
		return "redirect:/user";   
	}

	//Mettre à Jour Profil
	@GetMapping(value = "update/{id}")
    public String userUpdate(@PathVariable Long id, Model model) {

        Optional<User> maybeUser = userDAO.findById(id);   
        		
		if(maybeUser.isPresent()) {
		model.addAttribute("user", maybeUser.get());
		
		return "update";
		}
		return "redirect:/user";   
	}
	
	@PutMapping(value = "/update")
	public String updateProfil(@ModelAttribute User userForm) {


        Optional<User> maybeUser = userDAO.findById(userForm.getId());


        if (maybeUser.isPresent()) {

            User n = maybeUser.get();
            n.setId(userForm.getId());
            n.setFirstName(userForm.getFirstName());
            n.setLastName(userForm.getLastName());
            n.setEmail(userForm.getEmail());
            userDAO.save(n);
        }


        return "redirect:/user/" + userForm.getId();
	}
	
	
	
	//Supprimer Utilisateur
	@DeleteMapping(value= "user/{id}")
	public String userDelete(@PathVariable Long id) {
		userDAO.deleteById(id);
		
		return "redirect:/user";
		
	}
	
}
