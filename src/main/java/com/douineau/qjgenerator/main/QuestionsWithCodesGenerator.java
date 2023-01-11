package com.douineau.qjgenerator.main;

import com.douineau.qjgenerator.exception.QuestionException;
import com.douineau.qjgenerator.exception.ReponsesException;
import com.douineau.qjgenerator.model.Question;
import com.douineau.qjgenerator.model.Reponse;
import com.douineau.qjgenerator.utils.ResourcesFileReader;
import com.douineau.qjgenerator.model.TopicEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class QuestionsWithCodesGenerator {

	public static void process() throws IOException, QuestionException, ReponsesException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

		// codes
		ResourcesFileReader reader2 = new ResourcesFileReader();
		File jsonFile2 = reader2.getFile("datas/codes.json");

		List<String> codes = mapper.reader().forType(new TypeReference<List<String>>() {
		}).readValue(jsonFile2);

		// datas
		ResourcesFileReader reader = new ResourcesFileReader();
		File jsonFile = reader.getFile("datas/questions.json");

		List<Question> questions = mapper.reader().forType(new TypeReference<List<Question>>() {
		}).readValue(jsonFile);

		System.out.println("nbQuestions : " + questions.size());

		for (Question q : questions) {

			// Questions contenant du code java avec une ou plusieurs balises (<java>, <java1>...)
			if (q.getTexte().contains("<java>")) {
				addJavaStyleQuestionText(q);
			}
			if (!q.getTexte().contains("<code>")) {
				if(!q.getTexte().contains("<none>")) {
					addItemsToQuestionText(codes, q);
				} else {
					deleteNoneTag(q);
				}

			}

			if (q.getTopicKey().equals("")) {
				throw new QuestionException(q);
			}

			List<Boolean> booleans = new ArrayList<Boolean>();
			for (Reponse r : q.getReponses()) {

				addBlanksToResponseText(r);

				if (!r.getTexte().contains("<code>")) {
					if(!r.getTexte().contains("<none>")) {
						addItemsToResponseText(codes, r);
					} else {
						deleteNoneTag(r);
					}

				}

				booleans.add(r.getIsTrue());

			}

			checkNbReponses(q, booleans);

		}

		printCountByTopic(questions);

		try {
			// get Oraganisation object as a json string
			String jsonStr = mapper.writeValueAsString(questions);

			// Displaying JSON String
			System.out.println(jsonStr);

			File file = new File("src/main/resources/datas/questionswithcodes.json");
			String absolutePath = file.getAbsolutePath();

			System.out.println(absolutePath);

			FileWriter writer=new FileWriter(absolutePath);
			writer.write(jsonStr);
			writer.close();

/*			File quizzJavaDatas = new File("../quizz-java/src/main/resources/datas/questionswithcodes.json");
			Files.deleteIfExists(quizzJavaDatas.toPath());
			Files.copy(file.toPath(), quizzJavaDatas.toPath());*/

			File quizzJavaSpringBootReactDatas = new File("../quizz-java-springboot-react/src/main/resources/questionswithcodes.json");
			Files.deleteIfExists(quizzJavaSpringBootReactDatas.toPath());
			Files.copy(file.toPath(), quizzJavaSpringBootReactDatas.toPath());

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
		int debutCode = q.getTexte().indexOf("<java>");
		String debutQuestion = q.getTexte().substring(0, debutCode);
		String codeQuestion =  q.getTexte().substring(debutCode);

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
		
		q.setTexte(newQuestion);
		
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

	private static void addItemsToResponseText(List<String> items, Reponse r) {

		for (String s : items) {
			if (r.getTexte().contains(s + " ")) {
				String after = "<code>" + s + "</code>";
				String replace = r.getTexte().replace(s, after);
				r.setTexte(replace);
			}
		}
	}

	private static void deleteNoneTag(Question q) {
		String replace = q.getTexte().replace("<none>", "");
		q.setTexte(replace);
	}
	
	private static void deleteNoneTag(Reponse r) {
		String replace = r.getTexte().replace("<none>", "");
		r.setTexte(replace);
	}
	
	private static void addBlanksToResponseText(Reponse r) {

		String s = r.getTexte();
		String sNew = s + " ";
		r.setTexte(sNew);

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

	private static void printCountByTopic(List<Question> questions) {
		
		for(TopicEnum topic : TopicEnum.values()) {
			System.out.println("count " + topic + " : " + 
					questions.stream()
					.filter(q -> q.getTopicKey().equals(topic.name()))
					.count());
		}

	}

}
