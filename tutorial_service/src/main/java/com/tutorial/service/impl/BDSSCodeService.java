package com.tutorial.service.impl;


import com.tutorial.domain.BDSSCode;
import com.tutorial.repository.BDSSCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BDSSCodeService {
    @Autowired
    private BDSSCodeDao bdssCodeDao;

    /**
     * 保存or更新
     * @param bdssCode
     */
    public void saveOrUpdate(BDSSCode bdssCode){
        bdssCodeDao.save(bdssCode);
    }


    public BDSSCode findBySsCode(String ssCode){
        return bdssCodeDao.findBySsCode(ssCode);
    }
}
