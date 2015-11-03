package com.scottlogic.thull.appstore.api.utils.auth;

import java.io.IOException;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.jboss.resteasy.util.Base64;

public class MySaltedAuthentificationInfo implements SaltedAuthenticationInfo {

	private static final long serialVersionUID = 6257265894642102924L;
	
	private final String username;
	private final String password;
	private final String salt;
	
	public MySaltedAuthentificationInfo(String username, String password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}
	
	@Override
	public Object getCredentials() {
		return password;
	}

	@Override
	public PrincipalCollection getPrincipals() {
		PrincipalCollection coll = new SimplePrincipalCollection(username, username);
		return coll;
	}

	@Override
	public ByteSource getCredentialsSalt() {
		try {
			return new SimpleByteSource(Base64.decode(salt));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
