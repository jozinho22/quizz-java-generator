package com.douineau.qjgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

public class Answer extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876188057166632647L;

	private String text;
	private Boolean goodAnswer;

	public Answer() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getGoodAnswer() {
		return goodAnswer;
	}

	public void setGoodAnswer(Boolean goodAnswer) {
		this.goodAnswer = goodAnswer;
	}
}
