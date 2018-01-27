package gov.MSI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/api/test")
	public String index() {
		return "Hello, world!";
	}
}
