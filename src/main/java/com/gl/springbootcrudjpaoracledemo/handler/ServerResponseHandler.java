package com.gl.springbootcrudjpaoracledemo.handler;

import com.gl.springbootcrudjpaoracledemo.response.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ServerResponseHandler {

	public static ResponseEntity<Object> successResponse(String successCode, String message, Object successData) {
		ServerResponse serverResponse = new ServerResponse(successCode, message, successData, "success");
		return new ResponseEntity<>(serverResponse, HttpStatus.OK);
	}

	public static ResponseEntity<Object> failureResponse(String errorCode, String message, Object failureData,
                                                         HttpStatus code) {
		ServerResponse serverResponse = new ServerResponse(errorCode, message, failureData, "fail");
		return new ResponseEntity<>(serverResponse, code);
	}

	public static ResponseEntity<Object> errorResponse(String errorCode, String message, HttpStatus code) {
		ServerResponse serverResponse = new ServerResponse(errorCode, message, null, "error");
		return new ResponseEntity<>(serverResponse, code);
	}
}
