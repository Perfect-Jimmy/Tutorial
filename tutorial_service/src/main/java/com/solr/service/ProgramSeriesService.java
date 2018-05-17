package com.solr.service;

import com.solr.domain.ProgramSeries;

/**
 * Created by Jimmy. 2018/5/17  15:47
 */
public interface ProgramSeriesService {

    public void save(ProgramSeries programSeries);

    public void delete(Long id);
}
