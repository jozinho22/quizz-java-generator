package com.douineau.qjgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reponse extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876188057166632647L;

	private String texte;
	private Boolean isTrue;
//	@ManyToOne
//	private Question question;

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

	@Override
	public String toString() {
		return "Reponse [id=" + id + ", texte=" + texte + ", isTrue=" + isTrue
				+ ", createdAt=" + createdAt + "]";
	}

}
