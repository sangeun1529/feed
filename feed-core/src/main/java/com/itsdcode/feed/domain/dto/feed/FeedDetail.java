package com.itsdcode.feed.domain.dto.feed;

import com.itsdcode.feed.auditor.AuditorBaseEntity;
import com.itsdcode.feed.domain.dto.md.MDInfo;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.domain.vo.feed.FeedShared;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "mdInfo")
@AttributeOverride(name = "id", column = @Column(name = "FeedDetail_Id"))
public class FeedDetail extends AuditorBaseEntity {

    @Column(nullable = false, length = 2000)
    private String text;

    @Column(length = 200)
    private String[] tag; //binary

    @Column
    private int likedCount;

    @Column
    private int replyCount;

    @Column
    private int sharedCount;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "feedDetail")
    @Builder.Default
    private List<FeedLike> feedLikeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "feedDetail")
    @Builder.Default
    private List<FeedComment> feedCommentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "feedDetail")
    @Builder.Default
    private List<FeedShared> feedSharedList = new ArrayList<>();


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MDInfo_Id")
    private MDInfo mdInfo; // MD 사진 , MD이름 ...

    public void addFeedLike(FeedLike feedLike) {
        feedLikeList.add(feedLike);
        likedCount = feedLikeList.size();
    }
    public void removeFeedLike(FeedLike feedLike) {
        feedLikeList.remove(feedLike);
        likedCount = feedLikeList.size();
    }

    public void addFeedComment(FeedComment feedComment ) {
        feedCommentList.add(feedComment);
        replyCount = feedCommentList.size();
    }
    public void removeFeedComment(FeedComment feedComment) {
        feedCommentList.remove(feedComment);
        replyCount = feedCommentList.size();
    }

    public void addFeedShared(FeedShared feedShared) {
        feedSharedList.add(feedShared);
        sharedCount = feedSharedList.size();
    }


    // 미구현
//    @Transient
//    private List<Object> mediaList;

}















