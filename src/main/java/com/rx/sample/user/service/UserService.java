package com.rx.sample.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rx.sample.user.dao.UserDao;
import com.rx.sample.user.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public void save(String username, String password) {
		User user = new User();

		user.setUsername(username);
		user.setPassword(password);

		userDao.save(user);
	}

	public void update(Long id, String username, String password) {
		User user = get(id);

		user.setUsername(username);
		user.setPassword(password);

		userDao.update(user);
	}

	public User get(Long id) {
		return userDao.get(id);
	}

	public List<User> find() {
		return userDao.find();
	}

}
