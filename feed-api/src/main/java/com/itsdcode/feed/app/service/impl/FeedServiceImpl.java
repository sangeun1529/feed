package com.itsdcode.feed.app.service.impl;

import com.itsdcode.feed.app.service.FeedService;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.dto.md.MDInfo;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.handle.FeedDetailHandler;
import com.itsdcode.feed.handle.MDHandler;
import com.itsdcode.feed.util.LongUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.data.domain.Sort.by;

@Service
@Slf4j
public class FeedServiceImpl implements FeedService {
    @Autowired
    private FeedDetailHandler feedDetailHandler;
    @Autowired
    private MDHandler mdHandler;

    @Override
    public FeedDetail getFeedDetail(Long id) {
        return feedDetailHandler.getFeedDetail(id);
    }

    @Override
    public List<FeedDetail> getFeedList(int pageNo, int pageSize) {
        Page<FeedDetail> feedDetails = feedDetailHandler.getFeedList(PageRequest.of(pageNo,pageSize, by("createdAt").descending()));

        long totalCount = feedDetails.getTotalElements();
        int totalPage = feedDetails.getTotalPages();
        boolean isNext = feedDetails.getSize() == feedDetails.getNumberOfElements() ? true : false;

        return feedDetails.getContent();
    }

    @Override
    public FeedDetail putFeedLike(Long id, Long userId) {
        FeedDetail fd = feedDetailHandler.getFeedDetail(id);
        if (fd.getFeedLikeList().isEmpty() ||
                !fd.getFeedLikeList().stream()
                        .anyMatch(x -> LongUtils.contains(x.getUserId(),userId))) {
            feedDetailHandler.putFeedLiked(fd, FeedLike.builder()
                    .userId(userId)
                    .feedDetail(fd)
                    .build());
        }
        return fd;
    }
    @Override
    public FeedDetail deleteFeedLike(Long id, Long userId) {
        FeedDetail fd = feedDetailHandler.getFeedDetail(id);
        if (!fd.getFeedLikeList().isEmpty() && fd.getLikedCount()>0){
            Optional<FeedLike> fl = fd.getFeedLikeList().stream().filter(x-> LongUtils.contains(x.getUserId() , userId)).findFirst();
            if(fl.isPresent())
                feedDetailHandler.deleteFeedLiked(fd,fl.get() );
            log.error("deleteLiked reqeust is bad ...");
        }else
            log.error(String.format("FeedLikedList = %s , likedCount = %d ", fd.getFeedLikeList() , fd.getLikedCount()));
        return fd;
    }

    @Override
    public void dummyFeedDetail(List<FeedDetail> tt) {
        for(FeedDetail t : tt){
            FeedDetail feedDetail = feedDetailHandler.postFeedDetail(t);
        }
    }
    @Override
    public MDInfo getMDInfo(Long id) {
        return mdHandler.getMDInfo(id);
    }
    @Override
    public MDInfo dummyMDInfo(MDInfo mdInfo) {
        return mdHandler.postMDInfo(mdInfo);
    }
}
