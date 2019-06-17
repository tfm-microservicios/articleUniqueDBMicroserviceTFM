package es.upm.miw.rest_controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import es.upm.miw.repositories.ArticleRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import es.upm.miw.dtos.ArticleDto;

@ApiTestConfig
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArticleResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private ArticleRepository articleRepository;

    @AfterAll
    void cleanupEnd(){
        this.articleRepository.deleteAll();
    }

    @BeforeAll
    void cleanupStart() {
        this.articleRepository.deleteAll();
    }

    @Test
    void testCreateArticle() {
        ArticleDto bodyArtDto = new ArticleDto("84000008899", "description test", "", BigDecimal.TEN, 10);
        bodyArtDto.setProviderId("5d07c19c5f09121875d7e5ee");
        ArticleDto articleDto = this.restService.loginAdmin().restBuilder(new RestBuilder<ArticleDto>())
                .clazz(ArticleDto.class).path(ArticleResource.ARTICLES).body(bodyArtDto).post().build();
        assertNotNull(articleDto);
    }

    @Test
    void testCreateArticleProviderNonExisting() {
        ArticleDto bodyArtDto = new ArticleDto("84000009977", "description test", "", BigDecimal.TEN, 10);
        bodyArtDto.setProviderId("abcdefhghijklmn");
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> this.restService.loginAdmin().restBuilder(new RestBuilder<ArticleDto>())
                        .clazz(ArticleDto.class).path(ArticleResource.ARTICLES).body(bodyArtDto).post().build());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testCreateArticleNegativePrice() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> this.restService.loginAdmin().restBuilder().path(ArticleResource.ARTICLES)
                        .body(new ArticleDto("4800000000011", "new", "", new BigDecimal("-1"), 10)).post().build());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
