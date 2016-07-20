package com.spm.ad.ctrl;

import java.io.IOException;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spm.ad.bean.AdServiceBean;


/**
 * Servlet implementation class AdController
 */
@WebServlet("/ad/*")
public class AdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public AdController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uris = uri.split("/");
		String partnerId = uris[uris.length-1];
		System.out.println("Partner id: " + partnerId);
		JsonObject output;
		AdServiceBean bean = (AdServiceBean) this.getServletContext().getAttribute(partnerId);
		if(bean!=null){
			Date date = bean.getExpiry_date();
			Date now = new Date();
			long expiry = date.getTime();
			long timeNow = now.getTime();
			System.out.println(bean.toString());
			if(expiry>timeNow){
				int duration = (int)(expiry - timeNow)/1000;
				output = Json.createObjectBuilder().add("partner_id", bean.getPartner_id()).add("ad_content", bean.getAd_content())
						.add("duration", duration).build();
			}
			else{
				output = Json.createObjectBuilder().add("Message", "There is no active ad present for the partner.").build();
			}
		}
		else{
			output = Json.createObjectBuilder().add("Message", "There is no active ad present for the partner.").build();
		}
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(output.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject input = Json.createReader(request.getInputStream()).readObject();
		AdServiceBean bean = new AdServiceBean(input);
		AdServiceBean savedAd = (AdServiceBean) this.getServletContext().getAttribute(bean.getPartner_id());
		JsonObject output;
		if(savedAd==null){
			this.getServletContext().setAttribute(bean.getPartner_id(), bean);
			output = Json.createObjectBuilder().add("Message", "Ad is saved successfully.").build();
		}
		else{
			Date date = savedAd.getExpiry_date();
			Date now = new Date();
			long expiry = date.getTime();
			long timeNow = now.getTime();
			if(expiry<timeNow){
				this.getServletContext().setAttribute(bean.getPartner_id(), bean);
				output = Json.createObjectBuilder().add("Message", "Ad is saved successfully.").build();
			}
			else{
				output = Json.createObjectBuilder().add("Message", "An active ad is already present for the partner.").build();
			}
		}
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(output.toString());
		
	}

}
