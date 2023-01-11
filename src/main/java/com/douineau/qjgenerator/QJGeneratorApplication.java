package com.douineau.qjgenerator;

import com.douineau.qjgenerator.exception.QuestionException;
import com.douineau.qjgenerator.exception.ReponsesException;
import com.douineau.qjgenerator.main.QuestionsWithCodesGenerator;

import java.io.IOException;

public class QJGeneratorApplication {

	public static void main(String[] args) throws QuestionException, ReponsesException, IOException {

		QuestionsWithCodesGenerator.process();
	}

}
