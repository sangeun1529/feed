package com.itsdcode.feed.domain.vo.feed;


import com.itsdcode.feed.auditor.AuditorHisEntity;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "feedDetail")
@Builder
@AttributeOverride(name = "id" , column = @Column(name = "FeedShared_Id"))
public class FeedShared extends AuditorHisEntity {

    @Column(nullable = false)
    @Getter
    private Long userId;

    @Column(nullable = false)
    @Getter
    private String platform;

    @Column(nullable = false)
    @Getter
    private String url;

    @ManyToOne
    @JoinColumn(name = "FeedDetail_Id")
    private FeedDetail feedDetail;


}
