package com.itsdcode.feed.handle;

import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedDetailHandler {

    public FeedDetail getFeedDetail(Long id);

    public FeedDetail postFeedDetail(FeedDetail feedDetail);

    public Page<FeedDetail> getFeedList(Pageable pageable);

    public void putFeedLiked(FeedDetail feedDetail , FeedLike feedLike);

    public void deleteFeedLiked(FeedDetail feedDetail , FeedLike feedLike);
}
