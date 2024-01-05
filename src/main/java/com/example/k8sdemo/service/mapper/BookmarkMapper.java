package com.example.k8sdemo.service.mapper;


import com.example.k8sdemo.api.dto.BookmarkDTO;
import com.example.k8sdemo.strore.entities.BookmarkEntity;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {


    public BookmarkDTO map(BookmarkEntity entity) {
        BookmarkDTO dto = new BookmarkDTO();
        return new BookmarkDTO(entity.getId(), entity.getTitle(), entity.getUrl(), entity.getCreatedAt());
    }

    public BookmarkEntity map(BookmarkDTO dto) {
        return new BookmarkEntity(dto.getId(), dto.getTitle(), dto.getUrl(), dto.getCreatedAt());
    }
}
