package com.douineau.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641309062449375141L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String texte;
	private String topic;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	private List<Reponse> reponses;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdAt;
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", texte=" + texte + ", topic=" + topic + ", reponses=" + reponses
				+ ", createdAt=" + createdAt + "]";
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
