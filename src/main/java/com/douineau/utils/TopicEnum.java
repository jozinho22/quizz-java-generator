package com.douineau.utils;

public enum TopicEnum {

	JAVA("Java"), SPRING("Spring"), JPA("JPA"), GIT("Git"), MAVEN("Maven"), DESIGN_PATTERNS("Design Patterns"), FRAMEWORKS("Frameworks de Java JEE"), SQL("SQL"), DIVERS("Divers"), ALGO("Algorithmie");
	
	private String topic;
	
	TopicEnum(String topic) {
		this.topic = topic;
	}
		
}
