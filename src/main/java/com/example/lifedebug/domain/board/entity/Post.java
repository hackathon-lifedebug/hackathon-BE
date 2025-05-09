package com.example.lifedebug.domain.board.entity;

import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.global.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글이 소속된 멘토 (멘토별 게시판)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor boardOwner;

    // 작성자 구분
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthorRole authorRole; // MENTEE or MENTOR

    // 작성자 (둘 중 하나만 존재)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id_writer")
    private Mentor mentor;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean isPrivate;
}
