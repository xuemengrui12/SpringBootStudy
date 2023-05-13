package com.xmr.jpa.service.impl;

import com.xmr.jpa.domain.Girl;
import com.xmr.jpa.repository.GirlRepository;
import com.xmr.jpa.service.IGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by xmr on 2018/8/31.
 */
@Service
public class GirlServiceImpl implements IGirlService {
    @Autowired
    private GirlRepository girlRepository;
//    @Autowired
//    private DataSourceTransactionManager manager;


    @Override
    public Girl saveGirlWithRollBack(Girl girl) {
        Girl g = girlRepository.save(girl);

        if (girl.getName().equals("xmr")) {
            //硬编码手动触发异常
            throw new IllegalArgumentException("xmr已存在，数据将回滚");
        }
        return g;
    }

    /**
     * @param girl
     * @return
     */

    @Override
    public Girl saveGirlWithoutRollBack(Girl girl) {
        Girl g = girlRepository.save(girl);

        if (girl.getName().equals("xmr")) {
            throw new IllegalArgumentException("xmr已存在，但数据将不会回滚");
        }
        return g;
    }

    @Override
    @Transactional //事物注解
    public void insertTwo() {

//        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
//        definition.setName("transaction");
//        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = manager.getTransaction(definition);
        try {
            Girl girl1 = new Girl();
            girl1.setAge(19);
            girl1.setName("aaa");
            girlRepository.save(girl1);
//            int i = 5 / 0;
        } catch (Exception e) {
//            manager.rollback(status);
        }

    }

    @Override
    public List<Girl> findAll() {
        return (List<Girl>) girlRepository.findAll();
    }

    @Override
    public Girl save(Girl girl) {
        return girlRepository.save(girl);
    }

    @Override
    public Optional<Girl> findById(Integer id) {
        return girlRepository.findById(id);
    }

    @Override
    public List<Girl> findByAge(Integer age) {
        return girlRepository.findByAge(age);
    }

    @Override
    public List<Girl> findByName(String name) {
        return girlRepository.findByName(name);


    }

    @Override
    public List<Girl> findByName(String name, Sort sort) {

        return girlRepository.findByName(name,Sort.by(Sort.Direction.ASC,"age"));
//        return girlRepository.findByName(name, PageRequest.of(0,10));
//        return null;
    }

    @Override
    public List<Girl> findByName(String name, Pageable pageable) {
        return null;
    }


    @Override
    public List<Girl> findByNameLike(String name) {
        return girlRepository.findByNameLike(name);
    }

    @Override
    public void deleteById(Integer id) {
        girlRepository.deleteById(id);
    }
}
