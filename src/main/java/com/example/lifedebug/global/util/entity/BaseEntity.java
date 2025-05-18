package com.example.lifedebug.global.util.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 직접 테이블로 매핑은 x, 상속받는 엔티티가 컬럼들 포함하게 됨
@EntityListeners(AuditingEntityListener.class) // 아래같은 감시용 어노테이션들 쓸 수 있게
// springApplication에 어노테이션 하나 붙여줘야함
public class BaseEntity {
    @CreatedDate // 처음 저장시 자동저장
    @Column(updatable = false) // 수정시에도 안 변하게
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정시 업데이트
    private LocalDateTime updatedAt;
}
