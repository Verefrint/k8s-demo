package com.example.k8sdemo.service;


import com.example.k8sdemo.api.dto.BookmarkDTO;
import com.example.k8sdemo.api.dto.BookmarksDTO;
import com.example.k8sdemo.service.mapper.BookmarkMapper;
import com.example.k8sdemo.strore.repositories.BookmarkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final BookmarkMapper mapper;


    public BookmarksDTO getAllBookmarks(Integer page) {
        int pageNumber = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkEntityPage = bookmarkRepository.findBookmarks(pageable);
        return new BookmarksDTO(bookmarkEntityPage);
    }
}
