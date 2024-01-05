package com.example.k8sdemo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BookmarksDTO {
    List<BookmarkDTO> data;
    @JsonProperty("total_elements")
    long totalElements;
    @JsonProperty("total_pages")
    long totalPages;
    @JsonProperty("current_page")
    long currentPage;
    @JsonProperty("is_first")
    boolean isFirst;
    @JsonProperty("is_last")
    boolean isLast;
    @JsonProperty("has_next")
    boolean hasNext;
    @JsonProperty("has_previous")
    boolean hasPrevious;

    public BookmarksDTO(Page<BookmarkDTO> bookMarksPage) {
        this.setData(bookMarksPage.getContent());
        this.setTotalElements(bookMarksPage.getTotalElements());
        this.setTotalPages(bookMarksPage.getTotalPages());
        this.setCurrentPage(bookMarksPage.getNumber() + 1);
        this.setFirst(bookMarksPage.isFirst());
        this.setLast(bookMarksPage.isLast());
        this.setHasNext(bookMarksPage.hasNext());
        this.setHasPrevious(bookMarksPage.hasPrevious());
    }
}
