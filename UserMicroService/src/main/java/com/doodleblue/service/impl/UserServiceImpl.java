package com.doodleblue.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doodleblue.constant.Constants;
import com.doodleblue.dto.UserDto;
import com.doodleblue.exception.UserNotFoundException;
import com.doodleblue.model.User;
import com.doodleblue.repository.UserRepository;
import com.doodleblue.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(UserDto userDto) throws Exception {

		User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getGender(),
				userDto.getAge());

		// condition to check if there exist a user with same email.
		if (userDto.getEmail() != null && !"".equals(userDto.getEmail())) {
			User userObj = this.userRepository.findByEmail(userDto.getEmail());
			if (userObj != null) {
				throw new Exception("User with this " + userDto.getEmail() + " already exists!!");
			}
		}

		// condition to check if the password is Strong or not.
		if (!passwordStrongness(userDto.getPassword())) {
			throw new Exception(Constants.PASSSWORD_WROND_EXCEPTION);
		}

		return this.userRepository.save(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndPassword(email, password);

		if (user == null) {
			throw new UserNotFoundException(Constants.EMAIL_PASSWORD_INVALID);
		}

		return user;

	}

//	To check if password is Strong or not
	public static boolean passwordStrongness(String input) {

		int n = input.length();
		boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
		Set<Character> set = new HashSet<Character>(
				Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
		for (char i : input.toCharArray()) {
			if (Character.isLowerCase(i))
				hasLower = true;
			if (Character.isUpperCase(i))
				hasUpper = true;
			if (Character.isDigit(i))
				hasDigit = true;
			if (set.contains(i))
				specialChar = true;
		}

		if (hasDigit && hasLower && hasUpper && specialChar && (n >= 8))
			return true;

		return false;
	}

}
