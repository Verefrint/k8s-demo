package com.example.k8sdemo.strore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkEntity {

    @Id
    @SequenceGenerator(name = "bookmark_id_sequence", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_id_sequence")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    private Instant createdAt = Instant.now();
}
