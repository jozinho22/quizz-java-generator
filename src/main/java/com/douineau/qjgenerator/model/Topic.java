package com.douineau.qjgenerator.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Topic extends AbstractEntity implements Serializable {

    private String topicKey;
    private String name;
    private boolean chosen;

    public Topic() {
        super();
    }

    public Topic(Integer id, String topicKey, String name, boolean chosen) {
        super.id = id;
        this.topicKey = topicKey;
        this.name = name;
        this.chosen = chosen;
    }

    public String getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(String topicKey) {
        this.topicKey = topicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicKey='" + topicKey + '\'' +
                ", name='" + name + '\'' +
                ", chosen=" + chosen +
                '}';
    }
}
