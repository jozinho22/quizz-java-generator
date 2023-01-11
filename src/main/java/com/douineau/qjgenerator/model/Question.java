package com.douineau.qjgenerator.model;

import java.io.Serializable;
import java.util.List;

public class Question extends AbstractEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5641309062449375141L;

	private String texte;
	private String topicKey;

	private List<Reponse> reponses;

	public Question() {
		super();
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getTopicKey() {
		return topicKey;
	}

	public void setTopicKey(String topicKey) {
		this.topicKey = topicKey;
	}

	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", texte='" + texte + '\'' +
				", topicKey='" + topicKey + '\'' +
				", reponses=" + reponses +
				'}';
	}

}
