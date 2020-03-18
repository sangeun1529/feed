package com.itsdcode.feed.handle;

import com.itsdcode.feed.domain.dto.feed.FeedComment;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.domain.vo.feed.FeedShared;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FeedDetailHandler {

    public Optional<FeedDetail> getFeedDetail(Long id);

    public Page<FeedDetail> getFeedList(Pageable pageable);

    public void putFeedLiked(FeedDetail feedDetail , FeedLike feedLike);

    public void deleteFeedLiked(FeedDetail feedDetail , FeedLike feedLike);

    public void putFeedShared(FeedDetail feedDetail , FeedShared feedShared);

    public void postFeedComment(FeedDetail feedDetail , FeedComment feedComment);
    public void deleteFeedComment(FeedDetail feedDetail , FeedComment feedComment);
    public void putFeedComment(FeedComment feedComment , String comment);










    public FeedDetail postFeedDetail(FeedDetail feedDetail);
}
