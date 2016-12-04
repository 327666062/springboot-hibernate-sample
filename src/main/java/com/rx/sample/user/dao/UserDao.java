package com.rx.sample.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.rx.sample.user.model.User;

@Repository
public class UserDao extends HibernateDaoSupport {

	@Autowired
	private void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	public User get(Long id) {
		return getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> find() {
		return (List<User>) getHibernateTemplate().find("from User");
	}

}
