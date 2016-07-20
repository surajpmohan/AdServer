package com.spm.ad.dao;

import java.util.List;

import com.spm.ad.dao.bean.ADBean;

public interface AdRepository {
	public int insert(ADBean adBean);
	public List<ADBean> getActiveAD(String partnerId);
	public List<ADBean> getAll();
}
