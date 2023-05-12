package com.example.blps_lab1.model.extended;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "culinary_news")
@Data
@NoArgsConstructor
public class CulinaryNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name ="user_login")
    private String user;

    private String description;

    @Column(name = "publication_date")
    private Timestamp publicationDate;

    public CulinaryNews(String name, String user, String description, Timestamp publicationDate) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.publicationDate = publicationDate;
    }


}
