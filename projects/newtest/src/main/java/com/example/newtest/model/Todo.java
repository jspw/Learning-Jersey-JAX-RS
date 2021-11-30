package com.example.newtest.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
public class Todo {
    private long id;
    private String summary;
    private String description;
    private Date reg_date;

    public Todo() {

    }

    public Todo(long id, String summary, String description, Date reg_date) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.reg_date = reg_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
