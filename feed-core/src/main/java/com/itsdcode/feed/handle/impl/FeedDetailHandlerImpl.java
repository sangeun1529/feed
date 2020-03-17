package com.itsdcode.feed.handle.impl;

import com.itsdcode.feed.domain.dto.feed.FeedComment;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.domain.vo.feed.FeedShared;
import com.itsdcode.feed.handle.FeedDetailHandler;
import com.itsdcode.feed.repository.FeedRepository;
import com.itsdcode.feed.util.LongUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class FeedDetailHandlerImpl implements FeedDetailHandler {

    private final FeedRepository feedRepository;

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
    public void putFeedShared(FeedDetail feedDetail, FeedShared feedShared) {
        feedDetail.addFeedShared(feedShared);
    }



    @Override
    public void postFeedComment(FeedDetail feedDetail, FeedComment feedComment) {
        feedDetail.addFeedComment(feedComment);
    }
    @Override
    public void putFeedComment(FeedComment feedComment , String comment) {
        feedComment.putComment(comment);
    }
    @Override
    public void deleteFeedComment(FeedDetail feedDetail, FeedComment feedComment) {
        feedDetail.removeFeedComment(feedComment);
    }


    @Override
    public FeedDetail postFeedDetail(FeedDetail feedDetail) {
        return feedRepository.save(feedDetail);
    }
}
