package com.itsdcode.feed.app.controller;

import com.itsdcode.feed.app.service.FeedService;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.dto.md.MDInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedController {

    private final FeedService feedService ;

    @GetMapping(value = "/{id}")
    public FeedDetail getFeedDetail(@PathVariable Long id){
        return feedService.getFeedDetail(id);
    }

    @GetMapping
    public List<FeedDetail> getFeedList(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize ){

        return feedService.getFeedList(pageNo , pageSize);
    }

    @PutMapping(value = "/{id}/liked")
    public FeedDetail putFeedLike(@PathVariable Long id ,@RequestHeader(value="userId") Long userId){
        return feedService.putFeedLike(id,userId);
    }

    @DeleteMapping(value = "/{id}/liked")
    public FeedDetail deleteFeedLike(@PathVariable Long id,@RequestHeader(value="userId") Long userId){
        return feedService.deleteFeedLike(id,userId);
    }

    @PutMapping(value = "/{id}/shared")
    public void putFeedShared(@PathVariable Long id ,@RequestHeader(value="userId") String authorization){

    }

    @GetMapping(value = "/{id}/comments")
    public void curdFeedShared(@PathVariable Long id ,@RequestHeader(value="userId") String authorization){
        // 댓글 CURD
    }

}
