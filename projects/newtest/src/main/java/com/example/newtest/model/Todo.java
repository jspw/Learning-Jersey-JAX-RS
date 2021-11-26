package com.example.newtest.model;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Todo {
    private int id;
    private String summary;
    private String description;

    public Todo(int id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public  Todo (){}

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
