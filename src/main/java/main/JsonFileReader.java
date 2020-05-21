package main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.douineau.entity.Question;
import com.douineau.utils.FileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileReader {

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader();
		File jsonFile = reader.getFile("questions/datas.json");
//		String json = jsonFile.
		System.out.println(jsonFile);

		ObjectMapper mapper = new ObjectMapper();
//		List<Question> questions = (List<Question>) mapper.readValue(json, Question.class);
		
		List<Question> questions = mapper.reader()
			      .forType(new TypeReference<List<Question>>() {})
			      .readValue(jsonFile);
		
		System.out.println(questions);

	}

}
