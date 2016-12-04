package com.rx.sample.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void testSave() {
		userService.save("sample", "sample");
	}

	@Test
	public void testUpdate() {
		userService.update(1L, "sample1", "sample1");
	}

	@Test
	public void testGet() {
		logger.info(userService.get(1L).toString());
	}

	@Test
	public void testFind() {
		userService.find().forEach(user -> logger.info(user.toString()));
	}

}
