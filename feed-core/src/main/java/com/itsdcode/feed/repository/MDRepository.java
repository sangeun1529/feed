package com.itsdcode.feed.repository;

import com.itsdcode.feed.domain.dto.md.MDInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MDRepository extends JpaRepository<MDInfo , Long> {
}
