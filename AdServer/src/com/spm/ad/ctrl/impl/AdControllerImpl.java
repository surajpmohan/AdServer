package com.spm.ad.ctrl.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spm.ad.ctrl.AdController;
import com.spm.ad.ctrl.bean.AdServiceBean;
import com.spm.ad.exception.AdException;
import com.spm.ad.service.AdService;

@Controller
@RequestMapping("/ad")
public class AdControllerImpl implements AdController {
	@RequestMapping(method=RequestMethod.GET, value="/{partnerId}",produces="application/json")
	public @ResponseBody AdServiceBean get(@PathVariable String partnerId){
		AdServiceBean adServiceBean = adService.getActiveAd(partnerId);
		return adServiceBean;
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<AdServiceBean> get(){
		List<AdServiceBean> beans = adService.getAllActiveAd();
		return beans;
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Map<String,String> post(@RequestBody AdServiceBean adBean){
		adService.insert(adBean);
		Map<String, String> map = new HashMap<>();
		map.put("Message", "The ad is saved successfully.");
		return map;
	}
	@ExceptionHandler(AdException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Map<String,String> errorHandler(AdException e) {
	    Map<String, String> map = new HashMap<>();
	    map.put("Message", e.getMessage());
	    return map;
	}
	@Autowired
	private AdService adService;


	public AdService getAdService() {
		return adService;
	}
	public void setAdService(AdService adService) {
		this.adService = adService;
	}


	
	
}
