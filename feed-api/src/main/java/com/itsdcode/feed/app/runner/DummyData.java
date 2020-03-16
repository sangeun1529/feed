package com.itsdcode.feed.app.runner;

import com.itsdcode.feed.app.service.FeedService;
import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import com.itsdcode.feed.domain.dto.md.MDInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DummyData {

    @Autowired
    FeedService feedService;

    @EventListener(ApplicationReadyEvent.class)
    public void dummyData() {

        feedService.dummyMDInfo(MDInfo.builder()
                .mdName("KSE")
                .mdThumb("https://raw.githubusercontent.com/sangeun1529/sangeun1529.github.io/master/img/portrait.jpg")
                .mdDesc("dummyData")
                .build());

                    MDInfo mdinfo = feedService.getMDInfo(1L);

        List<FeedDetail> tt = new ArrayList();

        for (int i = 0; i < 23; i++)
            tt.add(FeedDetail.builder()
                    .text("dummy data inserting...")
                    .replyCount(0)
                    .likedCount(0)
                    .tag(new String[]{"fashion", "acc", "hip"})
                    .mdInfo(mdinfo)
                    .feedLikeList(new ArrayList<>())
                    .build());

        feedService.dummyFeedDetail(tt);
    }
}
