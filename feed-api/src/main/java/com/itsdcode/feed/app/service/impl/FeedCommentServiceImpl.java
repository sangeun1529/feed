package com.itsdcode.feed.app.service.impl;

import com.itsdcode.feed.app.code.ErrorCode;
import com.itsdcode.feed.app.service.FeedCommentService;
import com.itsdcode.feed.domain.dto.feed.FeedComment;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.exception.PException;
import com.itsdcode.feed.exception.PExceptionCode;
import com.itsdcode.feed.handle.FeedDetailHandler;
import com.itsdcode.feed.handle.MDHandler;
import com.itsdcode.feed.util.LongUtils;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedCommentServiceImpl implements FeedCommentService {


    private final FeedDetailHandler feedDetailHandler;
    private final MDHandler mdHandler;

    @Override
    public List<FeedComment> getFeedComment(Long id , Long userId) {
        FeedDetail fd = getFeedDetailById(id);
        return fd.getFeedCommentList();
    }

    @Override
    public List<FeedComment> postFeedComment(Long id, Long userId, String comment) {
        FeedDetail fd = getFeedDetailById(id);

        FeedComment feedComment = FeedComment.builder()
                .comment(comment)
                .userId(userId)
                .userName("dcode")
                .feedDetail(fd)
                .build();
        feedDetailHandler.postFeedComment(fd, feedComment);

        return fd.getFeedCommentList();
    }

    @Override
    public FeedComment putFeedComment(Long id, Long userId, Long commentId, String comment) {
        FeedDetail fd = getFeedDetailById(id);
        if (fd.getReplyCount() > 0) {
            Optional<FeedComment> fl = fd.getFeedCommentList().stream().filter(x -> LongUtils.contains(x.getId(), commentId)).findFirst();
            if (fl.isPresent()) {
                feedDetailHandler.putFeedComment(fl.get(), comment);
                return fl.get();
            }
        }
        return null;
    }

    @Override
    public List<FeedComment> deleteFeedComment(Long id, Long userId, Long commentId) {
        FeedDetail fd = getFeedDetailById(id);
        if (fd.getReplyCount() > 0) {
            Optional<FeedComment> fl = fd.getFeedCommentList().stream().filter(x -> LongUtils.contains(x.getId(), commentId)).findFirst();
            if (fl.isPresent())
                feedDetailHandler.deleteFeedComment(fd, fl.get());
        }
        return fd.getFeedCommentList();
    }

    private FeedDetail getFeedDetailById(Long Id){
        return feedDetailHandler.getFeedDetail(Id).orElseThrow(() -> new PException(ErrorCode.FEED_BY_ID_NULL));
    }
}
