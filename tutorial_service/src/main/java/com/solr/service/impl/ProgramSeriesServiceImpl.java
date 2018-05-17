package com.solr.service.impl;

import com.solr.domain.ProgramSeries;
import com.solr.repository.ProgramSeriesRepository;
import com.solr.service.ProgramSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy. 2018/5/17  15:48
 */
@Service
public class ProgramSeriesServiceImpl implements ProgramSeriesService {
    @Autowired
    private ProgramSeriesRepository programSeriesRepository;

    @Override
    public void save(ProgramSeries programSeries) {
        programSeriesRepository.save(programSeries);
    }

    @Override
    public void delete(Long id) {
        programSeriesRepository.deleteById(id);
    }
}
