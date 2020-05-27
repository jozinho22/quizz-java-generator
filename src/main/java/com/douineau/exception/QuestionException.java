package com.douineau.exception;

import com.douineau.entity.Question;

public class QuestionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -610208580334466014L;

	public QuestionException(Question question) {
		super("Cette question n'a pas de topic : \n" + question.getTexte());
	}

	public QuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public QuestionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public QuestionException(String message) {
		super(message);
	}

	public QuestionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
