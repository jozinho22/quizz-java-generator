package com.douineau.qjgenerator.exception;

import com.douineau.qjgenerator.model.Question;

public class QuestionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -610208580334466014L;

	public QuestionException(Question question) {
		super("Cette question n'a pas de topic : \n" + question.getText());
	}

	public QuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuestionException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuestionException(String message) {
		super(message);
	}

	public QuestionException(Throwable cause) {
		super(cause);
	}
	
	

}
