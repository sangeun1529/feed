package com.itsdcode.feed.app.service;

import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.dto.md.MDInfo;

import java.util.List;

public interface FeedService {
    public FeedDetail getFeedDetail(Long id);

    public List<FeedDetail> getFeedList(int pageNo, int pageSize);

    public FeedDetail putFeedLike(Long id , Long userId);

    public FeedDetail deleteFeedLike(Long id, Long userId);









    public void dummyFeedDetail(List<FeedDetail> tt);

    public MDInfo getMDInfo(Long id);

    public MDInfo dummyMDInfo(MDInfo mdInfo);
}
