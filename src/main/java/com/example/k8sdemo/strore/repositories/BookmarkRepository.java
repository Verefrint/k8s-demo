package com.example.k8sdemo.strore.repositories;


import com.example.k8sdemo.api.dto.BookmarkDTO;
import com.example.k8sdemo.strore.entities.BookmarkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    @Query("select new com.example.k8sdemo.api.dto.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from BookmarkEntity b")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);
}
