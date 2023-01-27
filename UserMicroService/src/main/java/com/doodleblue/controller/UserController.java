package com.doodleblue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doodleblue.config.JwtGenerator;
import com.doodleblue.constant.Constants;
import com.doodleblue.dto.UserDto;
import com.doodleblue.exception.UserNotFoundException;
import com.doodleblue.model.User;
import com.doodleblue.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtGenerator jwtGenerator;

//	Method to Register/Save the User
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		try {
			UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getPassword(), user.getGender(),
					user.getAge());

			userService.saveUser(userDto);
			return new ResponseEntity<>(userDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {

		try {
			if (user.getEmail() == null || user.getPassword() == null) {
				throw new UserNotFoundException(Constants.EMAIL_PASSWORD_EMPTY);
			}

			User userData = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());

			if (userData == null) {
				throw new UserNotFoundException(Constants.EMAIL_PASSWORD_INVALID);
			}

			return new ResponseEntity<>(this.jwtGenerator.generateToken(user), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
