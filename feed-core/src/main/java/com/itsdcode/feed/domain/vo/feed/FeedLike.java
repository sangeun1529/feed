package com.itsdcode.feed.domain.vo.feed;


import com.itsdcode.feed.auditor.AuditorHisEntity;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"userId" , "FeedDetail_Id"}
        )})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "feedDetail")
@AttributeOverride(name = "id" , column = @Column(name = "FeedLike_Id"))
public class FeedLike extends AuditorHisEntity {

    @Column(nullable = false)
    @Getter
    private Long userId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "FeedDetail_Id")
    private FeedDetail feedDetail;

}
