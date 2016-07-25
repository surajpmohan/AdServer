package com.spm.test.intg.contr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spm.ad.ctrl.bean.AdServiceBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/ad-rest-servlet.xml")
public class AdControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/ad").contentType("application/json")
          .accept("application/json"))
          .andExpect(status().isOk())
          .andExpect(content().contentType("application/json"));
       
    }
    
    @Test
    public void postTest() throws Exception{
    	 ObjectMapper mapper = new ObjectMapper();
    	 AdServiceBean bean = new AdServiceBean();
         bean.setPartner_id("123_Neo");
         bean.setDuration(100);
         bean.setAd_content("This is my first ad");
         String jsonBean = mapper.writeValueAsString(bean);
         this.mockMvc.perform(post("/ad").contentType("application/json")
                 .accept("application/json").content(jsonBean))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.Message",is("The ad was saved successfully.")));
    }
    
    @Test
    public void getPartnerTest() throws Exception{
    	 ObjectMapper mapper = new ObjectMapper();
    	 AdServiceBean bean = new AdServiceBean();
         bean.setPartner_id("123_Sam");
         bean.setDuration(100);
         bean.setAd_content("This is sam's first ad");
         String jsonBean = mapper.writeValueAsString(bean);
         this.mockMvc.perform(post("/ad").contentType("application/json")
                 .accept("application/json").content(jsonBean))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.Message",is("The ad was saved successfully.")));
    	
         this.mockMvc.perform(get("/ad/123_Sam").contentType("application/json")
                 .accept("application/json"))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.partner_id",is("123_Sam")))
         		 .andExpect(MockMvcResultMatchers.jsonPath("$.ad_content",is("This is sam's first ad")));
    }
    
    @Test
    public void getAllPartnerTest() throws Exception{
    	 ObjectMapper mapper = new ObjectMapper();
    	 AdServiceBean bean = new AdServiceBean();
         bean.setPartner_id("123_Tom");
         bean.setDuration(100);
         bean.setAd_content("This is my first ad");
         String jsonBean = mapper.writeValueAsString(bean);
         this.mockMvc.perform(post("/ad").contentType("application/json")
                 .accept("application/json").content(jsonBean))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.Message",is("The ad was saved successfully.")));
         mapper = new ObjectMapper();
    	 bean = new AdServiceBean();
         bean.setPartner_id("123_Jerry");
         bean.setDuration(100);
         bean.setAd_content("This is my first ad");
         jsonBean = mapper.writeValueAsString(bean);
         this.mockMvc.perform(post("/ad").contentType("application/json")
                 .accept("application/json").content(jsonBean))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$.Message",is("The ad was saved successfully.")));
    	
         this.mockMvc.perform(get("/ad").contentType("application/json")
                 .accept("application/json"))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].partner_id",is("123_Tom")))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].partner_id",is("123_Tom")));
    }
}
