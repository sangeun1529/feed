package com.itsdcode.feed.app.controller;

import com.itsdcode.feed.app.service.FeedCommentService;
import com.itsdcode.feed.domain.dto.feed.FeedComment;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed/{id}/comments")
@RequiredArgsConstructor
@Slf4j
public class FeedCommentController {

    private final FeedCommentService feedCommentService;

    @GetMapping
    public List<FeedComment> getFeedComment(@PathVariable Long id ,
                                            @RequestHeader(value="userId") Long userId){
        return feedCommentService.getFeedComment(id,userId);
    }

    @PostMapping
    public List<FeedComment> postFeedComment(@PathVariable Long id ,
                                             @RequestHeader(value="userId") Long userId,
                                             @RequestHeader(value = "comment") String comment){

        return feedCommentService.postFeedComment(id,userId,comment);
    }
    @PutMapping
    public FeedComment putFeedComment(@PathVariable Long id ,
                                      @RequestHeader(value="userId") Long userId ,
                                      @RequestHeader(value = "commentId") Long commentId ,
                                      @RequestHeader(value = "comment") String comment){
        return feedCommentService.putFeedComment(id,userId , commentId ,comment);
    }
    @DeleteMapping
    public List<FeedComment> deleteFeedComment(@PathVariable Long id ,
                                               @RequestHeader(value="userId") Long userId ,
                                               @RequestHeader(value = "commentId") Long commentId){
        return feedCommentService.deleteFeedComment(id,userId , commentId);
    }
}
