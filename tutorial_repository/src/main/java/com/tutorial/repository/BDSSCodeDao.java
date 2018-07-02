package com.tutorial.repository;

import com.tutorial.domain.BDSSCode;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BDSSCodeDao extends PagingAndSortingRepository<BDSSCode, Integer>,
        JpaSpecificationExecutor<BDSSCode> {

    /**
     * 根据ss_code查询
     * @param ssCode
     * @return
     */
    BDSSCode findBySsCode(String ssCode);
}
