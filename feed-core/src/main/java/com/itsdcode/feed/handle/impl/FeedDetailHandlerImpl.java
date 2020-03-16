package com.itsdcode.feed.handle.impl;

import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.handle.FeedDetailHandler;
import com.itsdcode.feed.repository.FeedRepository;
import com.itsdcode.feed.util.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class FeedDetailHandlerImpl implements FeedDetailHandler {

    @Autowired
    private FeedRepository feedRepository;

    @Override
    public FeedDetail getFeedDetail(Long id) {
        return feedRepository.findById(id).get();
    }

    @Override
    public Page<FeedDetail> getFeedList(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

    @Override
    public void putFeedLiked(FeedDetail feedDetail, FeedLike feedLike) {
        feedDetail.addFeedLike(feedLike);
    }
    @Override
    public void deleteFeedLiked(FeedDetail feedDetail, FeedLike feedLike) {
        feedDetail.removeFeedLike(feedLike);
    }



    @Override
    public FeedDetail postFeedDetail(FeedDetail feedDetail) {
        return feedRepository.save(feedDetail);
    }
}
