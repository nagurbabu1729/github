package com.Oauth.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

	@GetMapping("/public")
	public String access() {
		return "public api accessed without authorization";
	}
	
	@GetMapping("/private")
	public String get() {
		return "private api accessed with authorization";
	}
	
}