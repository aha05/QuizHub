package com.quiz.QuizHub.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("""
    SELECT new com.quiz.QuizHub.quiz.CategoryResponse(
        c.id,
        c.name,
        c.description,
        c.icon,
        c.color,
        COUNT(q.id)
    )
    FROM Category c
    LEFT JOIN Quiz q
        ON q.category.id = c.id
    GROUP BY c.id, c.name, c.description, c.icon, c.color
""")
    List<CategoryResponse> findAllWithQuizCount();
}
