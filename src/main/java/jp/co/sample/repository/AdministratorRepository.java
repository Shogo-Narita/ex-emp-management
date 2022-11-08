package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	private static final RowMapper<Administrator>Administrator_ROW_MAPPER
	=new BeanPropertyRowMapper<>(Administrator.class);
	
	public void insert(Administrator administrator) {
		SqlParameterSource param=new BeanPropertySqlParameterSource(administrator);
			//if(administrator.getId()==null) {
				
				String insertSql="insert into administrators(name,mail_Address,password)"
						+ " Values(:name,:mailAddress,:password)";
				template.update(insertSql,param);
		//}else {
//			String updateSql="update Administrators set id=:id"
//					+ " name=:name,mailAddress=:mailAddress,password=:password"
//					+ " where id=:id";
//			template.update(updateSql, param);

	}
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		
		String sql="select id,name,maill_Address,password from administrators order by maillAddress,password";
		 List<Administrator>administratorList
		 =template.query(sql,Administrator_ROW_MAPPER);
		 if(administratorList.size()==0) {
			 return null;
	}
		 return administratorList.get(0);
		 
		//return template.query(sql, param, Administrator_ROW_MAPPER);
		
		
		
	}

}
