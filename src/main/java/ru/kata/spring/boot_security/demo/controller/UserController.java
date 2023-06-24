package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import javax.validation.Valid;


@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String startPage() {
		return "index";
	}
//	@GetMapping(value = "/login")
//	public String login() {
//		return "users";
//	}
	@GetMapping(value = "/admin")
	public String showAllUsers(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
	@GetMapping("/user/{id}")
	public String showOneUser(@PathVariable("id") long id1, ModelMap model) {
		model.addAttribute("user", userService.getUser(id1));
		return "oneUser";
	}

	@GetMapping("/admin/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping("/admin")
	public String create(@Valid @ModelAttribute("user")  User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "/new";

			userService.addUser(user);
			return "redirect:/admin";
	}

	@GetMapping("/admin/{id}/edit")
	public String edit(ModelMap model, @PathVariable("id") long id) {
		model.addAttribute("user", userService.getUser(id));
		return "edit";
	}

	@PatchMapping("/admin/{id}")
	public String update(@Valid @ModelAttribute("user")  User user, BindingResult bindingResult, @PathVariable("id") long id) {
		if (bindingResult.hasErrors())
			return "/edit";

		userService.updateUser(id, user);
		return "redirect:/admin";
	}

	@DeleteMapping("/admin/{id}")
	public String delete(@PathVariable("id") long id) {
		userService.removeUserById(id);
		return "redirect:/admin";
	}
}