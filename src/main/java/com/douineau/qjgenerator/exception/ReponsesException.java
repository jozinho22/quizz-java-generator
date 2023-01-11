package com.douineau.qjgenerator.exception;

import com.douineau.qjgenerator.model.Question;

public class ReponsesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -610208580334466014L;

	public ReponsesException(Question question) {
		super("Le compte de bonnes et de mauvaises réponses est faux pour la question : \n" + question.getText());
	}

	public ReponsesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReponsesException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReponsesException(String message) {
		super(message);
	}

	public ReponsesException(Throwable cause) {
		super(cause);
	}
	
	

}
