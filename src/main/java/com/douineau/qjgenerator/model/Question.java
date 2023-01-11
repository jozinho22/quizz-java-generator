package com.douineau.qjgenerator.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Question extends AbstractEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5641309062449375141L;

	private String text;
	private String topicKey;

	private List<Answer> answers;

	public Question() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTopicKey() {
		return topicKey;
	}

	public void setTopicKey(String topicKey) {
		this.topicKey = topicKey;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
