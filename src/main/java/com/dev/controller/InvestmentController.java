package com.dev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Investment;
import com.dev.model.InputModel;

@RestController
@RequestMapping("/api")
public class InvestmentController {

	@PostMapping("fetch-investments")
	public ResponseEntity<?> fetchInvvestments(@RequestBody InputModel input) {

		try {

			List<Investment> list = null;

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("log-investment")
	public ResponseEntity<?> addInvestments(@RequestBody InputModel input) {
		try {

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
