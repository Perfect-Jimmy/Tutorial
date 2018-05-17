package com.solr.repository;

import com.solr.domain.ProgramSeries;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jimmy. 2018/5/17  15:44
 */
@Repository
public interface ProgramSeriesRepository extends SolrCrudRepository<ProgramSeries,Long> {
    //findByAreaStartingWith 意思为:查询以参数Area开头的索引
}
