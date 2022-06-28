package zlagoda.server.company.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zlagoda.server.company.dto.LoginDTO;
import zlagoda.server.company.dto.ResponseLoginDTO;


@RestController
public class LoginController
{

	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public ResponseEntity<ResponseLoginDTO> login(final LoginDTO loginDTO)
	{
		System.out.println(loginDTO.getLogin() + loginDTO.getPassword());
		return new ResponseEntity<>( new ResponseLoginDTO(loginDTO.getLogin(), loginDTO.getPassword()) , HttpStatus.OK);
	}
}
