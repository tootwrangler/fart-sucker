package com.fart.sucker.fartsucker.repo;

import com.fart.sucker.fartsucker.entity.S3LinkEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3LinkRepository extends CrudRepository<S3LinkEntity, String> {

    @Query(nativeQuery=true, value="SELECT * FROM s3links ORDER BY random() LIMIT 1")
    S3LinkEntity getRandomS3Link();
}
