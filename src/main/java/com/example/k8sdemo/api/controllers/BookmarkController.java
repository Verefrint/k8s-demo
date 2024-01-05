package com.example.k8sdemo.api.controllers;


import com.example.k8sdemo.api.dto.BookmarksDTO;
import com.example.k8sdemo.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService service;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return service.getAllBookmarks(page);
    }
}
