package org.irushu.demo.service;

import org.irushu.demo.persistence.model.Mysql;
import org.irushu.demo.persistence.repository.MysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MysqlService {

    @Autowired
    private MysqlRepository mysqlRepository;

    public String findByInput(String input){
        List<Mysql> lMysql = mysqlRepository.findByInput(input);

        if(lMysql !=null && !lMysql.isEmpty()) {
            return lMysql.get(0).getOutput();
        }
        else{
            return "";
        }
    }

}
