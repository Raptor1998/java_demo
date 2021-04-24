package com.hdu;

import com.hdu.mapper.BankMapper;
import com.hdu.entity.domain.Bank;
import com.hdu.entity.domain.BankExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raptor
 * @description MapperTest
 * @date 2021/4/16 14:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Test
    public void context() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Autowired
    BankMapper bankMapper;

    //xxxExample就是封装查询条件的
    @Test
    public void context2(){
        BankExample bankExample = new BankExample();
        BankExample.Criteria criteria = bankExample.createCriteria();
        criteria.andMoneyBetween(300,400);
        BankExample.Criteria criteria1 = bankExample.createCriteria();
        criteria1.andMoneyEqualTo(600);
        bankExample.or(criteria1);
        List<Bank> banks = bankMapper.selectByExample(bankExample);
        System.out.println(banks);
    }

}
