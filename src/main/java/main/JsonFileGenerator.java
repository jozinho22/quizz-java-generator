package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileGenerator {

	public static void main(String[] args) {

		List<Question> questions = new ArrayList<Question>();

		for (int i = 0; i < 2; i++) {

			Question question = new Question();

			List<Reponse> reponses = new ArrayList<Reponse>();
			for (int k = 0; k < 3; k++) {
				Reponse reponse = new Reponse();
				reponses.add(reponse);
			}
			question.setReponses(reponses);
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
