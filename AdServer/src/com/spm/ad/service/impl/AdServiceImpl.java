package com.spm.ad.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.ad.ctrl.bean.AdServiceBean;
import com.spm.ad.dao.AdRepository;
import com.spm.ad.dao.bean.ADBean;
import com.spm.ad.exception.AdException;
import com.spm.ad.service.AdService;

@Service
public class AdServiceImpl implements AdService {
	@Autowired
	private AdRepository adRepository;

	public AdRepository getAdRepository() {
		return adRepository;
	}

	@Override
	public AdServiceBean insert(AdServiceBean adBean) {
		List<ADBean> dbBeans = adRepository.getActiveAD(adBean.getPartner_id());
		int size = dbBeans.size();
		if(size<=0){
			ADBean adDbBean = new ADBean(adBean);
			adRepository.insert(adDbBean);
		}
		else{
			throw new AdException("An active ad for the partner is already present.");
		}
		return adBean;
	}

	@Override
	public AdServiceBean getActiveAd(String partnerId) {
		List<ADBean> dbBeans = adRepository.getActiveAD(partnerId);
		if(dbBeans!=null && dbBeans.size()==1){
			AdServiceBean bean = new AdServiceBean(dbBeans.get(0));
			return bean;
		}
		else{
			throw new AdException("There is no active ad present for the partner.");
		}
	}

	@Override
	public List<AdServiceBean> getAllActiveAd() {
		List<ADBean> dbBeans = adRepository.getAll();
		List<AdServiceBean> serviceBeanList = new ArrayList<AdServiceBean>();
		for(ADBean dbBean: dbBeans){
			AdServiceBean bean = new AdServiceBean(dbBean);
			serviceBeanList.add(bean);
		}
		return serviceBeanList;
	}
}
