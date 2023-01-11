package com.douineau.qjgenerator.model;

public enum TopicEnum {

	JAVA("Java"),
	SPRING("Spring"),
	JPA("JPA"),
	GIT("Git"),
	MAVEN("Maven"),
	DESIGN_PATTERNS("Design Patterns"),
	FRAMEWORKS("Frameworks de Java JEE"),

	JAVASCRIPT("JavaScript"),
	SQL("SQL"),
	DIVERS("Divers"),
	ALGO("Algorithmie");
	
	private String name;
	
	TopicEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	
		
}
