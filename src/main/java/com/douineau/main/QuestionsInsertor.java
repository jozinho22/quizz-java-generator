package com.douineau.main;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.douineau.dao.DaoUtil;
import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.utils.FileReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionsInsertor {

	public static void main(String[] args) throws IOException {
		
		FileReader reader = new FileReader();
		File jsonFile = reader.getFile("datas/questionswithcodes.json");
		System.out.println(jsonFile);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//		List<Question> questions = (List<Question>) mapper.readValue(json, Question.class);
		
		List<Question> questions = mapper.reader()
			      .forType(new TypeReference<List<Question>>() {})
			      .readValue(jsonFile);
		
//		DaoUtil.truncateTable("reponse");
//		DaoUtil.truncateTable("question");
		
		for(Question question : questions) {
			question.setCreatedAt(new Date());
			DaoUtil.insertObject(question);
			List<Reponse> reponses = question.getReponses();
			for(Reponse reponse : reponses) {
				reponse.setQuestion(question);
				DaoUtil.insertObject(reponse);
			}
		}
		
	}

}
