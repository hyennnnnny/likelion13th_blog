package likelion13th.blog.service;
import jakarta.persistence.EntityNotFoundException;
import likelion13th.blog.domain.Article;
import likelion13th.blog.dto.AddArticleRequest;
import likelion13th.blog.dto.ArticleResponse;
import likelion13th.blog.dto.SimpleArticleResponse;
import likelion13th.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleResponse addArticle(AddArticleRequest request) {
        Article article=request.toEntity();

        articleRepository.save(article);
        return ArticleResponse.of(article);

    }

    public List<SimpleArticleResponse> getAllArticles(){
        List<Article> articleList = articleRepository.findAll();

        List<SimpleArticleResponse> articleResponseList = articleList.stream()
                .map(article -> SimpleArticleResponse.of(article))
                .toList();

        return articleResponseList;

    }

    public ArticleResponse getArticle(Long id){
        /* 1. JPA의 findById()를 사용하여 DB에서 id가 일치하는 게시글 찾기.
              id가 일치하는 게시글이 DB에 없으면 에러 반환*/
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다. ID: "+id));

        /*2. ArticleResponse DTO 생성하여 반환 */
        return ArticleResponse.of(article);
    }


}
