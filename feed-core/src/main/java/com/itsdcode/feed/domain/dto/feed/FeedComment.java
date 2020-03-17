package com.itsdcode.feed.domain.dto.feed;

import com.google.common.base.Strings;
import com.itsdcode.feed.auditor.AuditorBaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = "feedDetail")
@AttributeOverride(name = "id", column = @Column(name = "FeedComment_Id"))
public class FeedComment extends AuditorBaseEntity {

    @Column
    @Getter
    private Long userId;

    @Column
    @Getter
    private String userName;

    @Column
    @Getter
    private String comment;

    @ManyToOne
    @JoinColumn(name = "FeedDetail_Id")
    private FeedDetail feedDetail;

    public void putComment(String comment){
        this.comment=comment;
    }
}
