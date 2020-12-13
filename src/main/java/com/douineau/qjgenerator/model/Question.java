package com.douineau.qjgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Question extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641309062449375141L;

	@Column(length = 1200)
	private String texte;
	private String topic;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private List<Reponse> reponses;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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
				", topic='" + topic + '\'' +
				", reponses=" + reponses +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reponses == null) ? 0 : reponses.hashCode());
		result = prime * result + ((texte == null) ? 0 : texte.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reponses == null) {
			if (other.reponses != null)
				return false;
		} else if (!reponses.equals(other.reponses))
			return false;
		if (texte == null) {
			if (other.texte != null)
				return false;
		} else if (!texte.equals(other.texte))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		
		return true;
	}

}
