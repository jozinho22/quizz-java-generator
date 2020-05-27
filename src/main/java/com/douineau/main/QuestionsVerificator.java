package com.douineau.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.exception.QuestionException;
import com.douineau.exception.ReponsesException;
import com.douineau.utils.FileReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionsVerificator {

	public static void main(String[] args) throws IOException, ReponsesException, QuestionException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

		// codes
		FileReader reader2 = new FileReader();
		File jsonFile2 = reader2.getFile("datas/codes.json");

		List<String> codes = mapper.reader().forType(new TypeReference<List<String>>() {
		}).readValue(jsonFile2);

		// quotes
		FileReader reader3 = new FileReader();
		File jsonFile3 = reader3.getFile("datas/quotes.json");

		List<String> quotes = mapper.reader().forType(new TypeReference<List<String>>() {
		}).readValue(jsonFile3);

		// datas
		FileReader reader = new FileReader();
		File jsonFile = reader.getFile("datas/questions.json");

		List<Question> questions = mapper.reader().forType(new TypeReference<List<Question>>() {
		}).readValue(jsonFile);

		System.out.println("nbQuestions : " + questions.size());

		for (Question q : questions) {

			if (!q.getTexte().contains("<code>")) {
				addItemsToQuestionText(codes, q);
			}

			if (q.getTopic().equals("")) {
				throw new QuestionException(q);
			}
//			addItemsToQuestionText(quotes, q);

			List<Boolean> booleans = new ArrayList<Boolean>();
			for (Reponse r : q.getReponses()) {

				addBlanksToResponseText(r);

				if (!r.getTexte().contains("<code>")) {
					addItemsToResponseText(codes, r);
				}

				booleans.add(r.getIsTrue());

//				addItemsToResponseText(quotes, r);

			}

			checkNbReponses(q, booleans);

		}

		try {
			// get Oraganisation object as a json string
			String jsonStr = mapper.writeValueAsString(questions);

			// Displaying JSON String
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void checkNbReponses(Question q, List<Boolean> booleans) throws ReponsesException {
		boolean ok = false;
		int nb = q.getReponses().size();
		int k = 0;

		if (nb == 2) {
			if (booleans.get(k) && !booleans.get(k + 1) || !booleans.get(k) && booleans.get(k + 1)) {
				ok = true;
			} else {
				ok = false;
			}
		} else if (nb == 3) {
			if (booleans.get(k)) {
				if (!booleans.get(k + 1) && !booleans.get(k + 2)) {
					ok = true;
				} else {
					ok = false;
				}
			} else if (!booleans.get(k)) {
				if (!booleans.get(k + 1) && booleans.get(k + 2)) {
					ok = true;
				} else if (booleans.get(k + 1) && !booleans.get(k + 2)) {
					ok = true;
				} else {
					ok = false;
				}
			}
		}

		if (!ok) {
			throw new ReponsesException(q);
		}
	}

	private static void addItemsToQuestionText(List<String> items, Question q) {

		for (String s : items) {
			if (q.getTexte().contains(s + " ")) {
				String after = "<code>" + s + "</code>";
				String replace = q.getTexte().replace(s, after);
				q.setTexte(replace);
			}

		}
	}

	private static void addItemsToResponseText(List<String> items, Reponse r) {

		for (String s : items) {
			if (r.getTexte().contains(s + " ")) {
				String after = "<code>" + s + "</code>";
				String replace = r.getTexte().replace(s, after);
				r.setTexte(replace);
			}
		}
	}

	private static void addBlanksToResponseText(Reponse r) {

		String s = r.getTexte();
		String sNew = s + " ";
		r.setTexte(sNew);

	}

}
