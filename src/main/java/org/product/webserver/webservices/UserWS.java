package org.product.webserver.webservices;

import org.product.webserver.session.Operation;
import org.product.webserver.session.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 */

@RestController
@RequestMapping("/api/user")
public class UserWS {

	private final UserInfo userInfo;

	@Autowired
	public UserWS(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@GetMapping("/retrieve/operations")
	public List<Operation> getOperations(){
		return userInfo.getOperations();
	}

	@RequestMapping(value = "/submit/operation/{data}", method = POST)
	public ResponseEntity<HttpStatus> addOperation(@PathVariable("data") String data) {
		userInfo.getOperations().add(new Operation(UUID.randomUUID().toString() ,data));
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}


}