package com.spm.ad.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spm.ad.dao.AdRepository;
import com.spm.ad.dao.bean.ADBean;

@Repository
public class AdRepositoryImpl implements AdRepository {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AdRepositoryImpl(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	@Override
	public int insert(ADBean adBean) {
		final String sql = "INSERT INTO ad (partner_id, expiry_date, ad_content) VALUES (?, ?,?)";
		int updated = jdbcTemplate.update(sql, adBean.getPartnerId(), adBean.getExpiryDate(), adBean.getAdContent());
		return updated;
	}

	@Override
	public List<ADBean> getActiveAD(String partnerId) {
		String sql = "SELECT id, partner_id, expiry_date, ad_content FROM ad where expiry_date>CURRENT_TIMESTAMP AND partner_id=?";
		return jdbcTemplate.query(sql, new RowMapper<ADBean>() {
			@Override
			public ADBean mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ADBean adBean = new ADBean();
				adBean.setPartnerId(rs.getString("partner_id"));
				adBean.setExpiryDate(rs.getTimestamp("expiry_date"));
				adBean.setAdContent(rs.getString("ad_content"));
				return adBean;
			}
		}, partnerId);
	}

	@Override
	public List<ADBean> getAll() {
		String sql = "SELECT id, partner_id, expiry_date, ad_content FROM ad where expiry_date>CURRENT_TIMESTAMP";
		return jdbcTemplate.query(sql, new RowMapper<ADBean>() {
			@Override
			public ADBean mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ADBean adBean = new ADBean();
				adBean.setPartnerId(rs.getString("partner_id"));
				adBean.setExpiryDate(rs.getTimestamp("expiry_date"));
				adBean.setAdContent(rs.getString("ad_content"));
				return adBean;
			}
		});
	}
	

}
