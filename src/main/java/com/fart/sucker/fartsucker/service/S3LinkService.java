package com.fart.sucker.fartsucker.service;

import com.fart.sucker.fartsucker.entity.S3LinkEntity;
import com.fart.sucker.fartsucker.repo.S3LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S3LinkService {

    @Autowired
    private S3LinkRepository s3LinkRepository;

    public S3LinkEntity getRandomS3Link() {
        return s3LinkRepository.getRandomS3Link();
    }
}
