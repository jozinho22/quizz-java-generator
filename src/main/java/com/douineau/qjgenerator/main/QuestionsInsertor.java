package com.douineau.qjgenerator.main;

import com.douineau.qjgenerator.model.Question;
import com.douineau.qjgenerator.utils.ResourcesFileReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestionsInsertor {

	public static void main(String[] args) throws IOException {

		ResourcesFileReader reader = new ResourcesFileReader();
		File jsonFile = reader.getFile("datas/questionswithcodes.json");
		System.out.println(jsonFile);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//		List<Question> questions = (List<Question>) mapper.readValue(json, Question.class);
		
		List<Question> questions = mapper.reader()
			      .forType(new TypeReference<List<Question>>() {})
			      .readValue(jsonFile);
		

		
	}

}
