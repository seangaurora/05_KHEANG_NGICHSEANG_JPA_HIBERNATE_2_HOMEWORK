package com.example._05_kheang_kheangngichseang_jpa_hibernate_2_homework;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Date publicationYear;
}
