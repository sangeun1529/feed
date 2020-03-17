package com.itsdcode.feed.app.service;

import com.itsdcode.feed.domain.dto.feed.FeedComment;

import java.util.List;

public interface FeedCommentService {

    public List<FeedComment> getFeedComment(Long id , Long userId);

    public List<FeedComment> postFeedComment(Long id, Long userId, String comment);

    public FeedComment putFeedComment(Long id, Long userId, Long commentId, String comment);

    public List<FeedComment> deleteFeedComment(Long id, Long userId, Long commentId);


}
