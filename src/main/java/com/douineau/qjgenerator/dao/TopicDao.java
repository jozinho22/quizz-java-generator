package com.douineau.qjgenerator.dao;

import com.douineau.qjgenerator.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao extends CrudRepository<Topic, Integer> {
}
