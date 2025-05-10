package com.example.lifedebug.domain.board.controller;


import com.example.lifedebug.domain.board.dto.CommentDto;
import com.example.lifedebug.domain.board.dto.CommentResponse;
import com.example.lifedebug.domain.board.dto.PostDto;
import com.example.lifedebug.domain.board.dto.PostResponse;
import com.example.lifedebug.domain.board.entity.Post;
import com.example.lifedebug.domain.board.repository.BoardRepository;
import com.example.lifedebug.domain.board.repository.CommentRepository;
import com.example.lifedebug.domain.board.service.BoardService;
import com.example.lifedebug.domain.board.service.CommentService;
import com.example.lifedebug.domain.user.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping("/board/page") // 게시판 화면 목록
    public ResponseEntity<Page<PostResponse>> postList(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PostResponse> result = boardService.getPosts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시글 단건 조회
    @GetMapping("/board/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(boardService.getPost(postId));
    }

    // 게시글 작성
    @PostMapping("/board/create")              // userId??, authorRole, title, content, isprivate
    public ResponseEntity<PostResponse> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PostDto request) {
        String loginId = userDetails.getUsername();
        String role = null;
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            role = customUserDetails.getRole();
        } else {
            throw new IllegalStateException("Unexpected userDetails type");
        }
        return ResponseEntity.ok(boardService.createPost(loginId, role, request)); // getUsername이 loginId??
    }

    // 게시글 삭제
    @DeleteMapping("board/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        boardRepository.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

    // 게시글 수정
    @PutMapping("/board/edit/{postId}")                  // userId??, authorRole, title, content, isprivate
    public ResponseEntity<PostResponse> editPost(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long postId, @RequestBody PostDto request) {
        String loginId = userDetails.getUsername();
        String role = null;
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            role = customUserDetails.getRole();
        } else {
            throw new IllegalStateException("Unexpected userDetails type");
        }

        return ResponseEntity.ok(boardService.editPost2(loginId, role, postId, request));
    }

    // 댓글 작성
    @PostMapping("/comment/create")                     //    작성자id??, postid, content, authorRole
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentDto request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    // 댓글 삭제
    @DeleteMapping("/comment/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }





}
