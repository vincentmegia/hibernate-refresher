package com.vincentmegia.processors;

import com.vincentmegia.dao.FamilyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FamilyProcessor {
    @Autowired
    private FamilyDao familyDao;

    public FamilyProcessor() {}

    @Transactional
    public void process() {
//        familyDao.addFamily();
//        familyDao.getByI("1");
        var family = familyDao.addFamily2();
//        var family = familyDao.getByI("1");
//        var family = familyDao.getById2("1");
        family.setSurname("MEGIA");
//        var family1 = familyDao.getById2("1");
        familyDao.updateFamily(family);
        var family2 = familyDao.getById2("1");
    }
}
