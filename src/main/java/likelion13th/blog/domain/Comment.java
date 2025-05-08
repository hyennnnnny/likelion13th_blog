package likelion13th.blog.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Article article;
    private String content;
    private String author;
    private String password;
    private LocalDateTime createdAt;

    public Comment(Long id, String author, String password, Article article, String content){
        this.id = id;
        this.author = author;
        this.password =password;
        this.article = article;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
