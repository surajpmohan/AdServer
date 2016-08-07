package com.spm.ad.ctrl;

import java.util.List;
import java.util.Map;

import com.spm.ad.ctrl.bean.AdServiceBean;

public interface AdController {
	Map<String, String> post(AdServiceBean adBean);
	List<AdServiceBean> get();
	AdServiceBean get(String partnerId);
}
