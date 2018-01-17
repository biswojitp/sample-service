package io.swagger.samples.inflector.springboot.models;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Link;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserResource implements Resource {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Link> getLinks() {
    return Collections.emptyList();
  }
  
  public String getDoB() {
	  String sql = "SELECT DoB from UserDetails";

		Map<String, Object> row = jdbcTemplate.queryForList(sql).get(0);
		
		String dob = (String) row.get("DoB");
		
		String day =  dob.substring(0, dob.indexOf("/"));	 
		String month =  dob.substring(dob.indexOf("/")+1, dob.lastIndexOf("/"));	
		String year =  dob.substring(dob.lastIndexOf("/")+1, dob.length());	
	
		if (month.equals("Jan")) {
			month = "01";
		}
			
		String finalDob = year + "-" + month + "-" + day;
		return finalDob;
  }
  
  public String getGivenName() {
	  String sql = "SELECT NAME from UserDetails";

		Map<String, Object> row = jdbcTemplate.queryForList(sql).get(0);
		
		String name = (String) row.get("NAME");
		
		String givenName = name.substring(name.indexOf(" ") + 1, name.length());	
		
		return givenName;
  }

  public String getSurname() {
	  
	  String sql = "SELECT NAME from UserDetails";

		Map<String, Object> row = jdbcTemplate.queryForList(sql).get(0);
		
		String name = (String) row.get("NAME");
		
		String surName = name.substring(0, name.indexOf(","));	

		return surName;
    
  }

}
