package com.douineau.qjgenerator;

import com.douineau.qjgenerator.dao.insert.QuestionsInsertEvent;
import com.douineau.qjgenerator.main.QuestionsWithCodesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QJGeneratorApplication implements CommandLineRunner {

	@Autowired
	private QuestionsInsertEvent insertEvent;

	public static void main(String[] args) {
		SpringApplication.run(QJGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (String arg : args) {
			System.out.println(arg);
			if(arg.equals("insert")) {
				insertEvent.insert();
			}
		}
	}
}
