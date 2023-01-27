package com.doodleblue.service;

import org.springframework.stereotype.Service;

import com.doodleblue.dto.UserDto;
import com.doodleblue.exception.UserNotFoundException;
import com.doodleblue.model.User;

@Service
public interface UserService {

	User saveUser(UserDto userDto) throws Exception;

	User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

}
