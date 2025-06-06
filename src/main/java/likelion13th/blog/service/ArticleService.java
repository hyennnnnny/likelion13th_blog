package likelion13th.blog.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import likelion13th.blog.domain.Article;
import likelion13th.blog.dto.request.AddArticleRequest;
import likelion13th.blog.dto.request.DeleteRequest;
import likelion13th.blog.dto.request.UpdateArticleRequest;
import likelion13th.blog.dto.response.ArticleDetailResponse;
import likelion13th.blog.dto.response.ArticleResponse;
import likelion13th.blog.dto.response.CommentResponse;
import likelion13th.blog.dto.response.SimpleArticleResponse;
import likelion13th.blog.exception.ArticleNotFoundException;
import likelion13th.blog.exception.PermissionDeniedException;
import likelion13th.blog.repository.ArticleRepository;
import likelion13th.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    
    // 글 추가
    @Transactional
    public ArticleResponse addArticle(AddArticleRequest request) {
        Article article=request.toEntity();

        article = articleRepository.save(article);
        return ArticleResponse.of(article);

    }

    // 전체 글 조회
    public List<SimpleArticleResponse> getAllArticles(){
        List<Article> articleList = articleRepository.findAll();

        List<SimpleArticleResponse> articleResponseList = articleList.stream()
                .map(article -> SimpleArticleResponse.of(article))
                .toList();

        return articleResponseList;

    }
    
    // 단일 글 조회
    public ArticleDetailResponse getArticle(Long id){
        /* 1. JPA의 findById()를 사용하여 DB에서 id가 일치하는 게시글 찾기.
              id가 일치하는 게시글이 DB에 없으면 에러 반환*/
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        // 2. 해당 게시글에 달려있는 댓글들 가져오기
        List<CommentResponse> comments=getCommentList(article);

        /*3. ArticleResponse DTO 생성하여 반환 */
        return ArticleDetailResponse.of(article, comments);
    }
    
    // 특정 게시글에 달려있는 댓글 목록 가져오기
    private List<CommentResponse> getCommentList(Article article){
        return commentRepository.findByArticle(article).stream()
                .map(comment -> CommentResponse.of(comment))
                .toList();
    }
    
    // 글 수정
    @Transactional
    public ArticleResponse updateArticle(Long id, UpdateArticleRequest request) {
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        if(!article.getPassword().equals(request.getPassword())) {
            throw new PermissionDeniedException("해당 글에 대한 수정 권한이 없습니다.");
        }

        article.update(request.getTitle(), request.getContent());
//        article=articleRepository.save(article);

        return ArticleResponse.of(article);

    }
    
    // 글 삭제
    @Transactional
    public void deleteArticle(Long id, DeleteRequest request){
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        if(!request.getPassword().equals(article.getPassword())){
            throw new PermissionDeniedException("해당 글에 대한 삭제 권한이 없습니다.");
        }

        articleRepository.deleteById(id);
    }




}


