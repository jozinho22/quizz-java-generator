package com.douineau.qjgenerator.dao;

import com.douineau.qjgenerator.model.Question;
import com.douineau.qjgenerator.model.Reponse;
import org.springframework.data.repository.CrudRepository;

public interface ReponseDao extends CrudRepository<Reponse, Integer> {
}
