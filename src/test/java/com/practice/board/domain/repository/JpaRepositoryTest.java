package com.practice.board.domain.repository;

import com.practice.board.config.JpaConfig;
import com.practice.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
@Rollback(value = false)
class JpaRepositoryTest {

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Test
    @DisplayName("select 테스트")
    void given_whenSelecting_thenWorksFine() {

        //Given

        //When
        List<Article> articleList = articleRepository.findAll();

        //Then
        assertThat(articleList)
                .isNotNull()
                .hasSize(1000);
    }


    @Test
    @DisplayName("insert 테스트")
    void givenTestData_whenInserting_thenWorksFine() {

        //Given
        long count = articleRepository.count();
        Article article = Article.of("title","hashTag","content");
        //When
        articleRepository.save(article);
        //Then
        assertThat(articleRepository.count()).isEqualTo(1);
    }



    @Test
    @DisplayName("update 테스트")
    void givenTestData_whenUpdate_thenWorksFine() {

        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashTag = "#springBOOT";
        article.setHashTag(updateHashTag);

        //When
        Article savedArticle = articleRepository.save(article);

        //Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashTag",updateHashTag);
    }




    @Test
    @DisplayName("delete 테스트")
    void givenTestData_whenDelete_thenWorksFine() {

        //Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long  articleCount = articleRepository.count();
        long  commentCount = articleCommentRepository.count();
        int deletedCommentCount = article.getArticleComments().size();

        //When
        articleRepository.delete(article);

        //Then
        assertThat(articleRepository.count()).isEqualTo(articleCount -1);
        assertThat(commentCount).isEqualTo(commentCount -deletedCommentCount);

    }

}