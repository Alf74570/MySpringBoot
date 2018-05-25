package com.campusnum.springboot.controller;

import java.util.Optional;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campusnum.springboot.dao.UserDAO;
import com.campusnum.springboot.exceptions.UtilisateurIntrouvableException;
import com.campusnum.springboot.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spring")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	// Créer Utilisateurs
	@PostMapping("/creer")
	@ResponseBody
	public String creerUtilisateur(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email) {

		String userId = "";

		try {

			User user = new User(firstName, lastName, email);
			userDAO.save(user);
			userId = String.valueOf(user.getId());

		} catch (Exception ex) {
			return "*** ERROR *** L'utilisateur n'a pas pu être créé : " + ex.toString();
		}

		return "*** SUCCESS *** Utilisateur créé avec l'id = " + userId;

	}

	// Liste tous les Utilisateurs
	@GetMapping("/utilisateurs")
	public Iterable<User> listeUtilisateurs() {

		return userDAO.findAll();
	}

	// Trouver Utilisateur par son id
	@GetMapping("/utilisateurs/{id}")
	public User afficherUnUtilisateur(@PathVariable Long id) {
		Optional<User> maybeUser = userDAO.findById(id);

		if (!maybeUser.isPresent())
			throw new UtilisateurIntrouvableException("L'utilisateur avec l'id " + id + " est introuvable");

		return maybeUser.get();
	}

	// Trouver utilisateur par email
	@GetMapping("/utilisateur/mail/{email}")
	public User trouverParEmail(@PathVariable String email) {
		Optional<User> maybeUser = userDAO.findByEmail(email);

		if (!maybeUser.isPresent())
			throw new UtilisateurIntrouvableException("L'utilisateur avec l'email " + email + " est introuvable");

		return maybeUser.get();

	}

	// Supprimer Utilisateur par id
	@DeleteMapping("supprimer/{id}")
	public String supprimerUtilisateur(@PathVariable Long id) {

		try {

			userDAO.deleteById(id);

		} catch (Exception ex) {

			return "*** ERROR *** Utilisateur non supprimé :" + ex.toString();
		}

		return "*** SUCCESS *** \n Utilisateur supprimé correctement";
	}

	// Update l'email, le prénom & le nom de l'Utilisateur
	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable Long id, @RequestParam String email, @RequestParam String firstName,
			@RequestParam String lastName) {

		User user = new User();

		try {

			userDAO.findById(id);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			userDAO.save(user);

		} catch (Exception ex) {

			return "*** ERROR *** l'utilisateur n'a pas pu se mettre à jour: " + ex.toString();
		}

		return "*** SUCCESS *** L'utilisateur a bien été mis à jour";
	}

}
