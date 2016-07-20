package com.spm.ad.bean;

import java.util.Date;

import javax.json.JsonObject;


public class AdServiceBean {
	private String partner_id;
	private Date expiry_date;
	private String ad_content;
	public AdServiceBean(){
		super();
	}
	public AdServiceBean(JsonObject bean){
		super();
		this.partner_id = bean.getString("partner_id");
		this.ad_content = bean.getString("ad_content");
		int duration = bean.getInt("duration");
		Date now = new Date();
		long expiry = now.getTime() + duration*1000;
		this.expiry_date = new Date(expiry);
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	@Override
	public String toString() {
		return "AdServiceBean [partner_id=" + partner_id + ", expiry_date=" + expiry_date + ", ad_content=" + ad_content
				+ "]";
	}
}
