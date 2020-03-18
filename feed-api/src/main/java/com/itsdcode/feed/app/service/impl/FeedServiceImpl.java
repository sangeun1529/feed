package com.itsdcode.feed.app.service.impl;

import com.itsdcode.feed.app.code.ErrorCode;
import com.itsdcode.feed.app.service.FeedService;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.dto.md.MDInfo;
import com.itsdcode.feed.domain.vo.feed.FeedLike;
import com.itsdcode.feed.domain.vo.feed.FeedShared;
import com.itsdcode.feed.exception.PException;
import com.itsdcode.feed.format.KakaoFormat;
import com.itsdcode.feed.handle.FeedDetailHandler;
import com.itsdcode.feed.handle.MDHandler;
import com.itsdcode.feed.util.LongUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.domain.Sort.by;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final FeedDetailHandler feedDetailHandler;
    private final MDHandler mdHandler;

    @Override
    public FeedDetail getFeedDetail(Long id) {
        return getFeedDetailById(id);
    }

    @Override
    public List<FeedDetail> getFeedList(int pageNo, int pageSize) {
        Page<FeedDetail> feedDetails = feedDetailHandler.getFeedList(PageRequest.of(pageNo, pageSize, by("createdAt").descending()));

        long totalCount = feedDetails.getTotalElements();
        int totalPage = feedDetails.getTotalPages();
        boolean isNext = feedDetails.getSize() == feedDetails.getNumberOfElements() ? true : false;

        return feedDetails.getContent();
    }

    @Override
    public FeedDetail putFeedLike(Long id, Long userId) {
        FeedDetail fd = getFeedDetailById(id);
        if (!fd.getFeedLikeList().stream()
                        .anyMatch(x -> LongUtils.contains(x.getUserId(), userId))) {
            feedDetailHandler.putFeedLiked(fd, FeedLike.builder()
                    .userId(userId)
                    .feedDetail(fd)
                    .build());
        }
        return fd;
    }

    @Override
    public FeedDetail deleteFeedLike(Long id, Long userId) {
        FeedDetail fd = getFeedDetailById(id);
        if (fd.getLikedCount() > 0) {
            Optional<FeedLike> fl = fd.getFeedLikeList().stream().filter(x -> LongUtils.contains(x.getUserId(), userId)).findFirst();
            if (fl.isPresent())
                feedDetailHandler.deleteFeedLiked(fd, fl.get());
        } else
            log.error(String.format("FeedLikedList = %s , likedCount = %d ", fd.getFeedLikeList(), fd.getLikedCount()));
        return fd;
    }

    @Override
    public Map<String, String> putFeedShared(Long id, Long userId) {
        FeedDetail fd = getFeedDetailById(id);
        String webUrl = "http://localhost:18080/feed/" + id;

        feedDetailHandler.putFeedShared(fd, FeedShared.builder()
                .platform("kakao")
                .url(webUrl)
                .userId(userId)
                .feedDetail(fd)
                .build());


        return KakaoFormat.builder()
                .method("POST")
                .authorization("test")
                .receiverUuids("2525")
                .url("https://kapi.kakao.com/v1/api/talk/friends/message/default/send")
                .webUrl(webUrl)
                .imageUrl("https://s3.ap-northeast-2.amazonaws.com/prod-drection/drection/200316_hm_promo/promo_hm_01.jpg")
                .imageHeight(640)
                .imageWidth(640)
                .commentCount(fd.getReplyCount())
                .likeCount(fd.getLikedCount())
                .sharedCount(fd.getSharedCount())
                .description(String.valueOf(fd.getTag()))
                .title(fd.getText())
                .build().kakaoJsonString();
    }


    @Override
    public void dummyFeedDetail(List<FeedDetail> tt) {
        for (FeedDetail t : tt) {
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

    private FeedDetail getFeedDetailById(Long Id){
        return feedDetailHandler.getFeedDetail(Id).orElseThrow(() -> new PException(ErrorCode.FEED_BY_ID_NULL));
    }
}
