package com.doodleblue.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.doodleblue.constant.Constants;
import com.doodleblue.constant.JwtConstant;
import com.doodleblue.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGeneratorImpl implements JwtGenerator {
	
//	Method to generate the Token
	@Override
	public Map<String, Object> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SECRET).compact();
		Map<String, Object> jwtTokenGen = new HashMap<>();
		jwtTokenGen.put(Constants.TOKEN, jwtToken);
		jwtTokenGen.put(Constants.MESSAGE, user);
		return jwtTokenGen;
	}

}
