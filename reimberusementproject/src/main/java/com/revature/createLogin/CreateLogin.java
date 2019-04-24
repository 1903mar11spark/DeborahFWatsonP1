package com.revature.createLogin;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

//import java.util.Random;

public class CreateLogin {

	

	public String createPassword() 
    { 
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
		String password = RandomStringUtils.random( 8, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
       
        return password;
    } 
	public String createUsername(String firstname, String lastname) {
		firstname = firstname.replace(" ", "").toLowerCase();
		lastname = lastname.replace(" ", "").toLowerCase();
		String username = firstname + "." + lastname;
		return username;
	}

	public String createEmail(String firstname, String lastname) {
		String email = firstname + "." + lastname + "@alchemy.com";
		return email;
	}
}