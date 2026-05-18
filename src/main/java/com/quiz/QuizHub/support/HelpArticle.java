package com.quiz.QuizHub.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "help_article")
public class HelpArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="slug")
    private String slug;

    @Column(name = "published")
    private boolean published = true;

    @Column(name = "views")
    private Integer views = 0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private HelpCategory category;
}
