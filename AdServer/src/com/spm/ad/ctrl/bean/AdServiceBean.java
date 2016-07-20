package com.spm.ad.ctrl.bean;

import java.sql.Timestamp;
import java.util.Date;

import com.spm.ad.dao.bean.ADBean;

public class AdServiceBean {
	private String partner_id;
	private int duration;
	private String ad_content;
	public AdServiceBean(){
		super();
	}
	public AdServiceBean(ADBean bean){
		super();
		this.partner_id = bean.getPartnerId();
		this.ad_content = bean.getAdContent();
		Timestamp expiry_date = bean.getExpiryDate();
		Date dbDate = new Date(expiry_date.getTime());
		Date now = new Date();
		long duration = (dbDate.getTime() - now.getTime())/1000;
		this.duration = (int)duration;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
}
