package com.example.lifedebug.domain.board.service;

import com.example.lifedebug.domain.board.dto.PostRequest;
import com.example.lifedebug.domain.board.dto.PostResponse;
import com.example.lifedebug.domain.board.entity.AuthorRole;
import com.example.lifedebug.domain.board.entity.Post;
import com.example.lifedebug.domain.board.mapper.PostMapper;
import com.example.lifedebug.domain.board.repository.BoardRepository;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.service.MenteeService;
import com.example.lifedebug.domain.user.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostMapper postMapper;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final CommentService commentService;

    public PostResponse createPost(String loginId, String role, PostRequest postDto) {
        Mentor mentor = null;
        Mentee mentee = null;
        AuthorRole authorRole = null;
        if(role.equals("MENTOR")){
            mentor = mentorService.findByLoginId(loginId);
            authorRole = AuthorRole.MENTOR;
        } else {
            mentee = menteeService.findByLoginId(loginId);
            authorRole = AuthorRole.MENTOR;
        }
        Mentor broadOwner = mentorService.findById(postDto.mentorId);

        Post post = new Post();
        post.setAuthorRole(authorRole);
        post.setTitle(postDto.title);
        post.setContent(postDto.content);
        post.setPrivate(postDto.isPrivate);
        post.setBoardOwner(broadOwner); // ✅ 직접 조회해서 설정
        post.setMentor(mentor);
        post.setMentee(mentee);

        // 작성자 정보도 authorRole에 따라 설정 필요
        // if (MENTOR) → post.setMentor(조회된 mentor)
        // if (MENTEE) → post.setMentee(조회된 mentee)

        boardRepository.save(post);
        return postMapper.toResponse(post);
    }


    public PostResponse getPost(Long postId) {
        Post result = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        PostResponse postResponse = postMapper.toResponse(result);
        postResponse.setComments(commentService.getComments(result));
        return postResponse ;
    }

    public Page<PostResponse> getPosts(Pageable pageable){
        return boardRepository.findPageBy(pageable)
                .map(postMapper::toResponse);
    }

    public PostResponse editPost(Long postid) {
        Post result = boardRepository.findById(postid)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return postMapper.toResponse(result);
    }

    public PostResponse editPost2(String loginId, String role, Long postid, PostRequest postRequest) {
        AuthorRole authorRole = null;
        Mentor mentor = null;
        Mentee mentee = null;
        if(role.equals("MENTOR")){
            mentor = mentorService.findByLoginId(loginId);
            authorRole = AuthorRole.MENTOR;
        } else {
            mentee = menteeService.findByLoginId(loginId);
            authorRole = AuthorRole.MENTOR;
        }

        Mentor broadOwner = mentorService.findById(postRequest.mentorId);

        Post post = new Post();
        post.setId(postid);
        post.setAuthorRole(authorRole);
        post.setTitle(postRequest.title);
        post.setContent(postRequest.content);
        post.setPrivate(postRequest.isPrivate);
        //post.setBoardOwner(auth.get ??);
        post.setBoardOwner(broadOwner); // 직접 조회해서 설정
        post.setMentor(mentor);
        post.setMentee(mentee);

        boardRepository.save(post);

        return postMapper.toResponse(post);
    }
}
