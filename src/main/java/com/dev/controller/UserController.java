package com.dev.controller;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dev.entity.ExpenseUser;
import com.dev.service.UserService;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ExpenseUser input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "", "");

		logParams.setMsg("Performing validations");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		
		//Need to work on this
		
		return new ResponseEntity<>("Login Success", HttpStatus.OK);
	}

	@PostMapping("/register-user")
	public ResponseEntity<?> registerUser(@RequestBody ExpenseUser input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "", "");

		logParams.setMsg("Registering User");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		
		String res = userService.registerUser(input);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
