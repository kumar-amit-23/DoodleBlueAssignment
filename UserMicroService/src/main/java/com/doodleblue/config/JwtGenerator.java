package com.doodleblue.config;

import java.util.Map;

import com.doodleblue.model.User;

public interface JwtGenerator {
//	Method to generate the Token
	Map<String, Object> generateToken(User user);
}
