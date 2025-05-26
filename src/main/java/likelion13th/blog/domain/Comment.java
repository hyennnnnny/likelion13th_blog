package likelion13th.blog.domain;
//
//import java.time.LocalDateTime;
//
//public class Comment {
//    private Long id;
//    private Article article;
//    private String content;
//    private String author;
//    private String password;
//    private LocalDateTime createdAt;
//
//    public Comment(Long id, String author, String password, Article article, String content){
//        this.id = id;
//        this.author = author;
//        this.password =password;
//        this.article = article;
//        this.content = content;
//        this.createdAt = LocalDateTime.now();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Article getArticle() {
//        return article;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//}

import jakarta.persistence.*;
import likelion13th.blog.domain.Article;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}