package com.itsdcode.feed.handle.impl;

import com.itsdcode.feed.domain.dto.md.MDInfo;
import com.itsdcode.feed.repository.MDRepository;
import com.itsdcode.feed.handle.MDHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class MDHandlerImpl implements MDHandler {

    private final MDRepository mdRepository;

    @Override
    public MDInfo getMDInfo(Long id){
        return mdRepository.findById(id).get();
    }
    @Override
    public MDInfo postMDInfo(MDInfo mdInfo) {
        return mdRepository.save(mdInfo);
    }


}
