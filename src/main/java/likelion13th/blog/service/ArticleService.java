package likelion13th.blog.service;
import likelion13th.blog.domain.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final List<Article> articleDB = new ArrayList<>();
    private Long nextId = 1L;
    // 전체 메서드가 사용해야하니 디비는 외부에 있음
    
    public Article addArticle(Article article) {

        if(article.getAuthor() == null
                || article.getContent() == null
                || article.getTitle() == null
                || article.getPassword() == null) {
            throw new IllegalArgumentException("제목, 내용, 작성자, 비밀번호는 필수 입력 항목입니다.");
        }

        //Article 객체 생성
        Article newArticle = new Article(

                article.getContent(),
                nextId++,
                article.getTitle(),
                article.getAuthor(),
                article.getPassword()
        );

        //DB에 객체 저장
        articleDB.add(newArticle);
        
        //저장한 Article 객체 반환
        return newArticle;
    }

    public List<Article> findAll() {
        return articleDB;
    }
}
