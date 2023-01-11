package com.douineau.qjgenerator.main;

import com.douineau.qjgenerator.exception.QuestionException;
import com.douineau.qjgenerator.exception.ReponsesException;
import com.douineau.qjgenerator.model.Question;
import com.douineau.qjgenerator.model.Answer;
import com.douineau.qjgenerator.model.Topic;
import com.douineau.qjgenerator.utils.ResourcesFileReader;
import com.douineau.qjgenerator.model.TopicEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class QuestionsWithCodesGenerator {

	public static void process() throws IOException, QuestionException, ReponsesException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

		// topics
		int i = 0;
		List<Topic> topics = new ArrayList<>();
		for(TopicEnum t : TopicEnum.values()) {
			Topic topic = new Topic();
			topic.setId(i);
			topic.setName(t.getName());
			topic.setTopicKey(t.toString());
			topics.add(topic);
			i++;
		}

		// codes
		ResourcesFileReader codesReader = new ResourcesFileReader();
		File jsonFile2 = codesReader.getFile("datas/codes.json");

		List<String> codes = mapper.reader().forType(new TypeReference<List<String>>() {
		}).readValue(jsonFile2);

		// questions
		ResourcesFileReader questionsReader = new ResourcesFileReader();
		File jsonFile = questionsReader.getFile("datas/questions.json");

		List<Question> questions = mapper.reader().forType(new TypeReference<List<Question>>() {
		}).readValue(jsonFile);

		System.out.println("nbQuestions : " + questions.size());

		int idQ = 0;
		for (Question q : questions) {
			q.setId(idQ);
			// Questions contenant du code java avec une ou plusieurs balises (<java>, <java1>...)
			if (q.getText().contains("<java>")) {
				addJavaStyleQuestionText(q);
			}
			if (!q.getText().contains("<code>")) {
				if(!q.getText().contains("<none>")) {
					addItemsToQuestionText(codes, q);
				} else {
					deleteNoneTag(q);
				}

			}

			if (q.getTopicKey().equals("")) {
				throw new QuestionException(q);
			}

			List<Boolean> booleans = new ArrayList<Boolean>();
			int idR = 0;
			for (Answer r : q.getAnswers()) {
				r.setId(idR);
				addBlanksToResponseText(r);

				if (!r.getText().contains("<code>")) {
					if(!r.getText().contains("<none>")) {
						addItemsToResponseText(codes, r);
					} else {
						deleteNoneTag(r);
					}

				}

				booleans.add(r.getGoodAnswer());
				idR++;
			}

			checkNbReponses(q, booleans);
			idQ++;
		}

		printCountByTopic(questions);

		try {

			String jsonTopics = mapper.writeValueAsString(topics);

			File tFile = new File("src/main/resources/datas/topics.json");
			String tAbsPath = tFile.getAbsolutePath();
			System.out.println(tAbsPath);

			FileWriter tWriter = new FileWriter(tAbsPath);
			tWriter.write(jsonTopics);
			tWriter.close();

			String jsonQuestions = mapper.writeValueAsString(questions);

			File qFile = new File("src/main/resources/datas/questionswithcodes.json");
			String qAbsPath = qFile.getAbsolutePath();
			System.out.println(qAbsPath);

			FileWriter qWriter = new FileWriter(qAbsPath);
			qWriter.write(jsonQuestions);
			qWriter.close();

			// Displaying JSON String
			System.out.println(jsonQuestions);
			System.out.println(jsonTopics);

			// send it to the react project
			File jsonTopicsFile = new File("../test-your-skills/src/resources/topics.json");
			Files.deleteIfExists(jsonTopicsFile.toPath());
			Files.copy(tFile.toPath(), jsonTopicsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			File questionsWithCodesFile = new File("../test-your-skills/src/resources/questionswithcodes.json");
			Files.deleteIfExists(questionsWithCodesFile.toPath());
			Files.copy(qFile.toPath(), questionsWithCodesFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			/*Files.deleteIfExists(file.toPath());*/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String getEndRepere(String s) {		

		return s.replace("<", "</");
	}

	
	private static void addJavaStyleQuestionText(Question q) {
//		System.out.println(q.getTexte());
		int debutCode = q.getText().indexOf("<java>");
		String debutQuestion = q.getText().substring(0, debutCode);
		String codeQuestion =  q.getText().substring(debutCode);

		List<String> javaCodes = getJavaCodes(codeQuestion, debutCode);

		StringBuilder sb = new StringBuilder();
		sb.append("<br><div style=\"padding-left:40px;\">");
		
		for(String s : javaCodes) {
			sb.append(s);
		}
		
		sb.append("</div>");

		String newJavaCode = sb.toString();
//		System.out.println("newJavaCode - " + newJavaCode);
		String newQuestion = debutQuestion + newJavaCode;
		
		q.setText(newQuestion);
		
	}
	
	private static List<String> getJavaCodes(String codeQuestion, int debutCode) {
		boolean endOfLine = false;
		List<String> javaCodes = new LinkedList<String>();
		String javaCode = null;
		String javaLine = null;
		int repereDebut = 0;
		int repereFin = 0;
		
		String repere = "<java";

		while (!endOfLine) {

			if (codeQuestion.contains(repere)) {
				repereDebut = codeQuestion.indexOf(repere);
				repereFin = codeQuestion.indexOf(getEndRepere(repere));

//				System.out.println(codeQuestion);
				String tag = codeQuestion.substring(repereDebut, codeQuestion.indexOf(">") + 1);
				javaCode = codeQuestion.substring(repereDebut + tag.length(), repereFin);
				javaLine = fillWithContext(javaCode, tag);
				
				javaCodes.add(javaLine);
				codeQuestion = codeQuestion.substring(repereFin + getEndRepere(tag).length());
			} else {
				endOfLine = true;
			}
		}

		return javaCodes;
	}

	private static String fillWithContext(String javaCode, String tag) {
		StringBuilder sb = new StringBuilder();
		
		if(tag.equals("<java>")) {
			
			sb.append("<br><code>");
			sb.append(javaCode);
			sb.append("</code>");

		} else if(tag.equals("<java1>")) {
			
			sb.append("<br><code style=\"padding-left:40px;\">");
			sb.append(javaCode);
			sb.append("</code>");
			
		} else if(tag.equals("<java2>")) {
			
			sb.append("<br><code style=\"padding-left:80px;\">");
			sb.append(javaCode);
			sb.append("</code>");
			
		} else if(tag.equals("<java3>")) {
			
			sb.append("<br><code style=\"padding-left:120px;\">");
			sb.append(javaCode);
			sb.append("</code>");
			
		} else if(tag.equals("<java4>")) {
			
			sb.append("<br><code style=\"padding-left:160px;\">");
			sb.append(javaCode);
			sb.append("</code>");
			
		} else if(tag.equals("<javaReturn>")) {
			
			sb.append("<br><br><code>");
			sb.append(javaCode);
			sb.append("</code>");
			
		} else if(tag.equals("<javaCR>")) {
			
			sb.append("<br>");
			
		} else if(tag.equals("<none>")) {
			
			sb.append("");
			
		}
		
		return sb.toString();
	}

	private static void addItemsToResponseText(List<String> items, Answer r) {

		for (String s : items) {
			if (r.getText().contains(s + " ")) {
				String after = "<code>" + s + "</code>";
				String replace = r.getText().replace(s, after);
				r.setText(replace);
			}
		}
	}

	private static void deleteNoneTag(Question q) {
		String replace = q.getText().replace("<none>", "");
		q.setText(replace);
	}
	
	private static void deleteNoneTag(Answer r) {
		String replace = r.getText().replace("<none>", "");
		r.setText(replace);
	}
	
	private static void addBlanksToResponseText(Answer r) {

		String s = r.getText();
		String sNew = s + " ";
		r.setText(sNew);

	}
	
	private static void checkNbReponses(Question q, List<Boolean> booleans) throws ReponsesException {
		boolean ok = false;
		int nb = q.getAnswers().size();
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
			if (q.getText().contains(s + " ")) {
				String after = "<code>" + s + "</code>";
				String replace = q.getText().replace(s, after);
				q.setText(replace);
			}

		}
	}

	private static void printCountByTopic(List<Question> questions) {
		
		for(TopicEnum topic : TopicEnum.values()) {
			System.out.println("count " + topic + " : " + 
					questions.stream()
					.filter(q -> q.getTopicKey().equals(topic.name()))
					.count());
		}

	}

}
