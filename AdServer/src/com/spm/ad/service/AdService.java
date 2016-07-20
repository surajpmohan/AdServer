package com.spm.ad.service;

import java.util.List;

import com.spm.ad.ctrl.bean.AdServiceBean;

public interface AdService {
	public AdServiceBean insert(AdServiceBean bean);
	public AdServiceBean getActiveAd(String partnerId);
	public List<AdServiceBean> getAllActiveAd();
}
