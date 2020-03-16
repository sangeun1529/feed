package com.itsdcode.feed.auditor;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class AuditorHisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public void persistenceSetPK(Long id){
        this.id = id;
    }

    @Column(nullable = false , updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
