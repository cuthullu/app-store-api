package com.scottlogic.thull.appstore.api.utils.auth;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.domains.User;
import com.scottlogic.thull.appstore.api.utils.HibernateUtil;

public class AuthUtils {

	static final Logger LOG = LoggerFactory.getLogger(AuthUtils.class);

	public void registrate(String username, String plainTextPassword) {
		User user = new User();
		user.setUsername(username);

		generatePassword(user, plainTextPassword);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();

		System.out.println("\n\n");
		System.out.println("///////////////////////////////////////");
		System.out.println("Made a user with username " + username + " & password " + plainTextPassword + " & hash "
				+ user.getPassword() + " salt " + user.getSalt());
		System.out.println("///////////////////////////////////////");
		System.out.println("\n\n");
	}

	private void generatePassword(User user, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less spaaaaaace
		// than Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		user.setPassword(hashedPasswordBase64);
		user.setSalt(salt.toString());
	}
}
