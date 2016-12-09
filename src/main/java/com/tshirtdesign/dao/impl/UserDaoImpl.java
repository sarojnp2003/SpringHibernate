/**
 * 
 */
package com.tshirtdesign.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.tshirtdesign.dao.AbstractDao;
import com.tshirtdesign.dao.UserDao;
import com.tshirtdesign.model.User;

/**
 * @author saroj-gautam
 *
 */

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#findById(long)
	 */
	@Override
	public User findById(long id) {
		SQLQuery query = getSession().createSQLQuery("SELECT a.id FROM user_details a WHERE a.id = ?");
		query.setParameter(1, id);
		return (User) query.list().get(0);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#findByUsername(java.lang.String)
	 */
	@Override
	public User findByUsername(String userName) {
		SQLQuery query = getSession().createSQLQuery("SELECT * FROM user_details a WHERE a.email = :email");
		query.setParameter("email", userName);
		query.addEntity(User.class);
		@SuppressWarnings("unchecked")
		List<User> user = query.list();
		if(user.size() > 0) {
			return user.get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#saveUser(com.mrsfdemo.model.User)
	 */
	@Override
	public void saveUser(User user) {
		persist(user);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#updateUser(com.mrsfdemo.model.User)
	 */
	@Override
	public void updateUser(User user) {
		getSession().update(user);
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#deleteUserById(long)
	 */
	@Override
	public void deleteUserById(long id) {
		SQLQuery query = getSession().createSQLQuery("delete from user_details where id = ?");
		query.setParameter(1, id);
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#findAllUser()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		SQLQuery query = getSession().createSQLQuery("Select * from user_details");
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.mrsfdemo.dao.UserDao#isUserExist(com.mrsfdemo.model.User)
	 */
	@Override
	public boolean isUserExist(User user) {
		SQLQuery query = getSession().createSQLQuery(" SELECT CASE WHEN COUNT(id) > 0 THEN true "
							+ "ELSE false END"
							+ "	 FROM user_details a WHERE a.id = ? ");
		query.setParameter(1, user.getId());
		int val =  query.executeUpdate();
		if(val == 1) {
			return true;
		} else {
			return false;
		}
	}
}