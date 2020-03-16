package com.itsdcode.feed.handle;

import com.itsdcode.feed.domain.dto.md.MDInfo;

public interface MDHandler {

    public MDInfo getMDInfo(Long id);

    public MDInfo postMDInfo(MDInfo mdInfo);
}
