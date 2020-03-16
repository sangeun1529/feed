package com.itsdcode.feed.repository;

import com.itsdcode.feed.domain.dto.feed.FeedDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<FeedDetail, Long> {
}
