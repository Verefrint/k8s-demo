package com.example.k8sdemo;

import com.example.k8sdemo.strore.entities.BookmarkEntity;
import com.example.k8sdemo.strore.repositories.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.CoreMatchers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class K8sDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
        List<BookmarkEntity> bookmarks = new ArrayList<>();

        bookmarks.add(new BookmarkEntity(null, "SivaLabs", "https://sivalabs.in", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "SpringBlog", "https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Quarkus", "https://quarkus.io/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Micronaut", "https://micronaut.io/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "JOOQ", "https://www.jooq.org/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Thoughts On Java", "https://thorben-janssen.com/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "DZone", "https://dzone.com/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "DevOpsBookmarks", "http://www.devopsbookmarks.com/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Expressjs", "https://expressjs.com/", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "Marcobehler", "https://www.marcobehler.com", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "baeldung", "https://www.baeldung.com", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "devglan", "https://www.devglan.com", Instant.now()));
        bookmarks.add(new BookmarkEntity(null, "linuxize", "https://linuxize.com", Instant.now()));

        repository.saveAllAndFlush(bookmarks);

    }

    @ParameterizedTest
    @CsvSource({
        "1,15,2,1,true,false,true,false",
        "2,15,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
                            boolean isFirst, boolean isLast,
                            boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc.perform(get("/api/bookmarks?page="+pageNo))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.total_elements", CoreMatchers.equalTo(totalElements)))
            .andExpect(jsonPath("$.total_pages", CoreMatchers.equalTo(totalPages)))
            .andExpect(jsonPath("$.current_page", CoreMatchers.equalTo(currentPage)))
            .andExpect(jsonPath("$.is_first", CoreMatchers.equalTo(isFirst)))
            .andExpect(jsonPath("$.is_last", CoreMatchers.equalTo(isLast)))
            .andExpect(jsonPath("$.has_next", CoreMatchers.equalTo(hasNext)))
            .andExpect(jsonPath("$.has_previous", CoreMatchers.equalTo(hasPrevious)))
        ;
    }

}
