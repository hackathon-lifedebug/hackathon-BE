package com.example.lifedebug.domain.board.service;


import com.example.lifedebug.domain.board.dto.CommentRequest;
import com.example.lifedebug.domain.board.dto.CommentResponse;
import com.example.lifedebug.domain.board.entity.AuthorRole;
import com.example.lifedebug.domain.board.entity.Comment;
import com.example.lifedebug.domain.board.entity.Post;
import com.example.lifedebug.domain.board.mapper.CommentMapper;
import com.example.lifedebug.domain.board.repository.BoardRepository;
import com.example.lifedebug.domain.board.repository.CommentRepository;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import com.example.lifedebug.domain.user.service.MenteeService;
import com.example.lifedebug.domain.user.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper commentMapper;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;

    public CommentResponse createComment(CommentRequest dto) {
        Post post = boardRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(dto.getContent());
        comment.setAuthorRole(dto.getAuthorRole());


        AuthorRole authorRole = dto.getAuthorRole();
        Mentor mentor = null;
        Mentee mentee = null;
        if(authorRole == AuthorRole.MENTOR){
            mentor = mentorService.findById(dto.getMentorId());
        }
        else {
            mentee = menteeService.findById(dto.getMenteeId());
        }
        comment.setMentor(mentor);
        comment.setMentee(mentee);

        post.getComments().add(comment);

        commentRepository.save(comment);

        CommentResponse commentResponse = commentMapper.toResponse(comment);
        commentResponse.setWriterName(authorRole == AuthorRole.MENTOR ? mentor.getName() : mentee.getName());

        return commentResponse; // writerName 저장 못 했어 ,,


    }
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        Post post = comment.getPost(); // 댓글이 속한 게시글
        post.getComments().remove(comment); // Post의 리스트에서도 제거

        // 이제 orphanRemoval = true 덕분에 DB에서도 삭제됨
        // 별도로 commentRepository.delete()는 호출하지 않아도 됨
    }

    // comment post별 조회
    public List<CommentResponse> getComments(Post post){
        return commentRepository.findAllByPost(post).stream().map(commentMapper::toResponse).toList();
    }

}
