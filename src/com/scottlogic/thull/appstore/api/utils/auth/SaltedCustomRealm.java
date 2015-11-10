package com.scottlogic.thull.appstore.api.utils.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scottlogic.thull.appstore.api.dao.UserDao;
import com.scottlogic.thull.appstore.api.dao.hibernate.HibernateUserDao;
import com.scottlogic.thull.appstore.api.domains.User;

public class SaltedCustomRealm extends JdbcRealm {
	static final Logger LOG = LoggerFactory.getLogger(SaltedCustomRealm.class);
	private UserDao userDao = new HibernateUserDao();
	
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();
		
		if(username == null) {
			LOG.debug("null username supplied");
			throw new  IncorrectCredentialsException();
		}
		
		final User user = userDao.getUser(username);
		
		SaltedAuthenticationInfo info = new MySaltedAuthentificationInfo(username, user.getPassword(), user.getSalt());
		
		return info;
	}

}
