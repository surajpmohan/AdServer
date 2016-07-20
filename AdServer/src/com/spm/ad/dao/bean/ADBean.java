package com.spm.ad.dao.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.spm.ad.ctrl.bean.AdServiceBean;


public class ADBean {
	private String partnerId;
	private Timestamp expiryDate;
	private String adContent;
	public ADBean(){
		super();
	}
	public ADBean(AdServiceBean bean){
		this.adContent = bean.getAd_content();
		this.partnerId = bean.getPartner_id();
		int duration = bean.getDuration();
		Date now = new Date();
		long expiry = now.getTime() + duration*1000;
		this.expiryDate = new Timestamp(expiry);
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
}
