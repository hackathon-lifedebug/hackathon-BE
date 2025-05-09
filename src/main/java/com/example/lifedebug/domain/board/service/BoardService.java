package com.example.lifedebug.domain.board.service;

import com.example.lifedebug.domain.board.dto.PostDto;
import com.example.lifedebug.domain.board.dto.PostResponse;
import com.example.lifedebug.domain.board.entity.Post;
import com.example.lifedebug.domain.board.mapper.PostMapper;
import com.example.lifedebug.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostMapper postMapper;

    public PostResponse createPost (PostDto postDto) {

        Post post = new Post();
        // dto에서 userid 뭐에써야되지
        post.setAuthorRole(postDto.authorRole);
        post.setTitle(postDto.title);
        post.setContent(postDto.content);
        post.setPrivate(postDto.isPrivate);
        //post.setBoardOwner(auth.get ??);

        boardRepository.save(post);
        return postMapper.toResponse(post);
    }

    public PostResponse getPost(Long postId) {
        Post result = boardRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(""));
        return postMapper.toResponse(result);
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

    public PostResponse editPost2(Long postid, PostDto postDto) {
        Post post = new Post();
        post.setId(postid);
        post.setAuthorRole(postDto.authorRole);
        post.setTitle(postDto.title);
        post.setContent(postDto.content);
        post.setPrivate(postDto.isPrivate);
        //post.setBoardOwner(auth.get ??);

        boardRepository.save(post);

        return postMapper.toResponse(post);
    }
}
