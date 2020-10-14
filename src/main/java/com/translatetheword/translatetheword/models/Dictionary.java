package com.translatetheword.translatetheword.models;

import javax.persistence.*;

@Entity
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String engword, rusword;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    public Dictionary(String engword, String rusword) {
        this.engword = engword;
        this.rusword = rusword;
    }

    public Dictionary(String engword, String rusword, User user) {
        this.author = user;
        this.engword = engword;
        this.rusword = rusword;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "engword='" + engword + '\'' +
                ", rusword='" + rusword + '\'' +
                '}';
    }

    public Dictionary() {
    }

    public String getEngword() {
        return engword;
    }

    public void setEngword(String engword) {
        this.engword = engword;
    }

    public String getRusword() {
        return rusword;
    }

    public void setRusword(String rusword) {
        this.rusword = rusword;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
