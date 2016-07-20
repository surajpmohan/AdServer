package com.spm.test.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.spm.ad.dao.AdRepository;
import com.spm.ad.dao.impl.AdRepositoryImpl;
import com.spm.ad.dao.bean.ADBean;

import org.junit.After;
import org.junit.Assert;
public class AdRepositoryImplTest {
    private EmbeddedDatabase db;
    AdRepository adRepository;
    
    @Before
    public void setUp() {
    	db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).addScript("db/sql/create_ad_db.sql").build();
    }
    @After
    public void shutDown() {
    	db.shutdown();
    }
    
    @Test
    public void testInsert() {
    	AdRepository userDao = new AdRepositoryImpl(db);
    	ADBean adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	Date today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()));
    	int count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    }
   
    @Test
    public void testGetAll() {
    	AdRepository userDao = new AdRepositoryImpl(db);
    	ADBean adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	Date today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()+10000));
    	int count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()+10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()-10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123XYZ");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()+10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123XYZ");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()-10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	this.testInsert();
    	userDao = new AdRepositoryImpl(db);
    	List<ADBean> ads = userDao.getAll();
    	Assert.assertTrue(ads.size()==3);
    }
    
    @Test
    public void testGetActiveADBean() {
    	AdRepository userDao = new AdRepositoryImpl(db);
    	ADBean adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	Date today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()+10000));
    	int count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()+10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	
    	userDao = new AdRepositoryImpl(db);
    	adBean = new ADBean();
    	adBean.setPartnerId("123ABC");
    	today = new Date();
    	adBean.setExpiryDate(new Timestamp(today.getTime()-10000));
    	count = userDao.insert(adBean);
    	Assert.assertTrue(count==1);
    	
    	
    	this.testInsert();
    	userDao = new AdRepositoryImpl(db);
    	List<ADBean> ads = userDao.getActiveAD("123ABC");
    	Assert.assertTrue(ads.size()==2);
    }
}
