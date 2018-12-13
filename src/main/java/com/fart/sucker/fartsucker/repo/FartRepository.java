package com.fart.sucker.fartsucker.repo;

import com.fart.sucker.fartsucker.entity.FartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "farts", path = "farts")
public interface FartRepository extends PagingAndSortingRepository<FartEntity, String>, JpaSpecificationExecutor<FartEntity> {

    @Query(nativeQuery=true, value="SELECT * FROM farts ORDER BY random() LIMIT 1")
    FartEntity getRandomFart();

    @Query(value = "SELECT f FROM FartEntity f WHERE LOWER(f.fart) LIKE CONCAT('%', LOWER(:fart), '%')")
    @RestResource(path = "/searchByFart", rel = "searchByFart")
    Page<FartEntity> searchFarts(@Param("fart") String fart, Pageable pageable);
}
