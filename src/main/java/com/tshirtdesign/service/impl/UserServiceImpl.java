/**
 * 
 */
package com.tshirtdesign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tshirtdesign.dao.UserDao;
import com.tshirtdesign.model.User;
import com.tshirtdesign.service.UserService;

/**
 * @author saroj-gautam
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#findById(long)
	 */
	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String userName) {
		return userDao.findByUsername(userName);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#saveUser(com.mrsfdemo.model.User)
	 */
	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#updateUser(com.mrsfdemo.model.User)
	 */
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#deleteUserById(long)
	 */
	@Override
	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#findAllUser()
	 */
	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.service.UserService#isUserExist(com.mrsfdemo.model.User)
	 */
	@Override
	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}
}