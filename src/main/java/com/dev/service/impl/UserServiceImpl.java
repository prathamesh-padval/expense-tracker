package com.dev.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dao.ExpenseDao;
import com.dev.entity.ExpenseUser;
import com.dev.service.UserService;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ExpenseDao dao;

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public String registerUser(ExpenseUser user) {
		LoggingParams logParams = new LoggingParams(user.getUserName(), "", "");

		logParams.setMsg("Checking if user is already present with the provided username");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		boolean userExist = dao.ifUserExist(user.getUserName());

		ExpenseUser expUser;
		if (!userExist) {
			logParams.setMsg("New user.");
			logger.info(LoggerMsgSequence.getMsg(logParams));

			expUser = dao.addUser(user);

			logParams.setMsg("New user has been added");
			logger.info(LoggerMsgSequence.getMsg(logParams));

		} else {
			logParams.setMsg("User with same username already exists");
			logger.error(LoggerMsgSequence.getMsg(logParams));

			expUser = null;
		}

		return expUser != null ? "Added" : "Failed";
	}
}
