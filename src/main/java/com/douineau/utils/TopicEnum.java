package com.douineau.utils;

public enum TopicEnum {

	JAVA("Java"), GIT("Git"), DESIGN_PATTERNS("Design Patterns"), FRAMEWORKS("Frameworks de Java JEE"), DIVERS("Divers"), ALGO("Algorithmie");
	
	private String topic;
	
	public String getTopic() {
		return topic;
	}
	
	TopicEnum(String topic) {
		this.topic = topic;
	}
}
