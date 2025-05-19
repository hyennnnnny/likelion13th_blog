package likelion13th.blog.controller;

import likelion13th.blog.domain.Article;
import likelion13th.blog.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    
    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody Article article){

        Article newArticle = articleService.addArticle(article);
        
        //저장한 객체 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newArticle);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {

        List<Article> articles = articleService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articles);
    }

    // 게시글 1개 조회
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(article);
    }

}
