package com.itsdcode.feed.app.controller;

import com.itsdcode.feed.app.service.FeedService;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Map<String,String> putFeedShared(@PathVariable Long id , @RequestHeader(value="userId") Long userId){
        return feedService.putFeedShared(id , userId);
    }

}
