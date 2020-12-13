package com.douineau.qjgenerator.dao;

import com.douineau.qjgenerator.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends CrudRepository<Question, Integer> {
}
