package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		String sql = "Select * from employee";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		List<Employee> list = new ArrayList<>();

		while (results.next()){
			Employee temp = new Employee(
					results.getLong("employee_id"),
					results.getLong("department_id"),
					results.getString("first_name"),
					results.getString("last_name"),
					results.getDate("birth_date").toLocalDate(),
					results.getDate("hire_date").toLocalDate()
			);

			list.add(temp);



		}
		return list;

	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {

		String sql;
		SqlRowSet results;

		if(firstNameSearch.equals("")&&lastNameSearch.equals("")) {
			sql = "select * from employee;";
			results = jdbcTemplate.queryForRowSet(sql);
		}
		else if (firstNameSearch.equals("")){
			sql = "Select * from employee where last_name iLike ?;";
			results = jdbcTemplate.queryForRowSet(sql,lastNameSearch);

		}
		else if (lastNameSearch.equals("")){
			sql = "Select * from employee where first_name iLike ?;";
			results = jdbcTemplate.queryForRowSet(sql,firstNameSearch);

		}
		else{
			sql = "Select * from employee where first_name ilike ? and last_name ilike ?;";
			results = jdbcTemplate.queryForRowSet(sql,"%"+firstNameSearch+"%","%"+lastNameSearch+"%");
		}


		List<Employee> list = new ArrayList<>();

		while (results.next()){
			Employee temp = new Employee(
					results.getLong("employee_id"),
					results.getLong("department_id"),
					results.getString("first_name"),
					results.getString("last_name"),
					results.getDate("birth_date").toLocalDate(),
					results.getDate("hire_date").toLocalDate()
			);

			list.add(temp);



		}
		return list;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		String sql = "select * from employee " +
				"join project_employee using(employee_id) " +
				"where project_id = ? ; ";

		SqlRowSet results=jdbcTemplate.queryForRowSet(sql,projectId);
		List<Employee> list = new ArrayList<>();

		while (results.next()){
			Employee temp = new Employee(
					results.getLong("employee_id"),
					results.getLong("department_id"),
					results.getString("first_name"),
					results.getString("last_name"),
					results.getDate("birth_date").toLocalDate(),
					results.getDate("hire_date").toLocalDate()
			);
			list.add(temp);
		}
		return list;

	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sql = "Insert into project_employee " +
				"values(?,?);";
      	jdbcTemplate.update(sql,projectId,employeeId);
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sql = "delete from project_employee " +
				"where project_id = ? and employee_id = ? ;";
		jdbcTemplate.update(sql,projectId,employeeId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		String sql = "select * from employee " +
				"left join project_employee using(employee_id) where project_id is null ;" ;

		SqlRowSet results=jdbcTemplate.queryForRowSet(sql);
		List<Employee> list = new ArrayList<>();

		while (results.next()){
			Employee temp = new Employee(
					results.getLong("employee_id"),
					results.getLong("department_id"),
					results.getString("first_name"),
					results.getString("last_name"),
					results.getDate("birth_date").toLocalDate(),
					results.getDate("hire_date").toLocalDate()
			);
			list.add(temp);
		}
		return list;
	}


}
