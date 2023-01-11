package com.douineau.qjgenerator.main;

import com.douineau.qjgenerator.model.Question;
import com.douineau.qjgenerator.model.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileGenerator {

	public static void main(String[] args) {

		List<Question> questions = new ArrayList<Question>();

		for (int i = 0; i < 2; i++) {

			Question question = new Question();

			List<Answer> answers = new ArrayList<Answer>();
			for (int k = 0; k < 3; k++) {
				Answer answer = new Answer();
				answers.add(answer);
			}
			question.setAnswers(answers);
			questions.add(question);
		}

		ObjectMapper mapper = new ObjectMapper();

		try {

			// get Oraganisation object as a json string
			String jsonStr = mapper.writeValueAsString(questions);

			// Displaying JSON String
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
