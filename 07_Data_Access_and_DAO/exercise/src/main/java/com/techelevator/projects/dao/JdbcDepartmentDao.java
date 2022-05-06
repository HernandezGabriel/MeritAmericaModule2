package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;

public class JdbcDepartmentDao implements DepartmentDao {
	
	private final JdbcTemplate jdbcTemplate;

	public JdbcDepartmentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Department getDepartment(Long id) {
		String findDepartmentSql =
				"SELECT * FROM department " +
						"WHERE department_id=?;";
		SqlRowSet results =
				jdbcTemplate.queryForRowSet(findDepartmentSql,id);

		while(results.next()){
			return new Department(results.getLong("department_id"), results.getString("name"));

		}
		return null;

	}

	@Override
	public List<Department> getAllDepartments() {
		String sql =
				"SELECT * FROM department;";
		SqlRowSet results =
				jdbcTemplate.queryForRowSet(sql);
		ArrayList resultsArray= new ArrayList<Department>();

		while(results.next()){
			resultsArray.add(new Department(results.getLong("department_id"), results.getString("name") ));
		}
		return resultsArray;
	}

	@Override
	public void updateDepartment(Department updatedDepartment) {
		String sql =
				"update department set name = ? "+
						"WHERE department_id = ? ;";
		jdbcTemplate.update(sql,updatedDepartment.getName(),updatedDepartment.getId());

	}

}
