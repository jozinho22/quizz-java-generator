package com.douineau.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.douineau.entity.Question;
import com.douineau.entity.Reponse;
import com.douineau.exception.ReponsesException;
import com.douineau.utils.FileReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionsVerificator {

	public static void main(String[] args) throws IOException, ReponsesException {


		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
		
		FileReader reader2 = new FileReader();
		File jsonFile2 = reader2.getFile("questions/quotes.json");
		
		List<String> inputCode = mapper.reader()
			      .forType(new TypeReference<List<String>>() {})
			      .readValue(jsonFile2);

		FileReader reader = new FileReader();
		File jsonFile = reader.getFile("questions/datas.json");
		
		List<Question> questions = mapper.reader()
			      .forType(new TypeReference<List<Question>>() {})
			      .readValue(jsonFile);
		
		for(Question q : questions) {
			
			for(String s : inputCode) {
				if(q.getTexte().contains(s)) {
					String after = "<code>" + s + "</code>";
					String replace = q.getTexte().replace(s, after);
					q.setTexte(replace);
				}
			}
			
			List<Boolean> booleans = new ArrayList<Boolean>();
			for(Reponse r : q.getReponses()) {
				for(String s : inputCode) {
					if(r.getTexte().contains(s)) {
						String after = "<code>" + s + "</code>";
						String replace = r.getTexte().replace(s, after);
						r.setTexte(replace);
					}
				}
				booleans.add(r.getIsTrue());
			}
			
			boolean ok = false;
			for(int k = 0 ; k < booleans.size() - 1 ; k++) {
				if(booleans.get(k)) {
					if(!booleans.get(k + 1) && !booleans.get(k + 2)) {
						ok = true;
						break;
					} else {
						ok = false;
						break;
					}
				} else if(!booleans.get(k)) {
					if(!booleans.get(k + 1) && booleans.get(k + 2)) {
						ok = true;
						break;
					} else if(booleans.get(k + 1) && !booleans.get(k + 2)) {
						ok = true;
						break;
					} else {
						ok = false;
						break;
					}
				}
			}
			if(!ok) {
				throw new ReponsesException(q);
			}
			
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

}
