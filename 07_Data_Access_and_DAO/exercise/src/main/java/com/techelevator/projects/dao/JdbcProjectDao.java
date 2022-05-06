package com.techelevator.projects.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(Long projectId) {

		String sql =
				"SELECT * FROM project " +
						"WHERE project_id=?;";
		SqlRowSet results =
				jdbcTemplate.queryForRowSet(sql,projectId);
		Project temp = new Project();

		while(results.next()) {

			temp.setId(results.getLong("project_id"));
			temp.setName(results.getString("name"));

			if(results.getDate("from_date")!=null){
				temp.setFromDate(results.getDate("from_date").toLocalDate());
			}
			if(results.getDate("to_date")!=null){
				temp.setToDate(results.getDate("to_date").toLocalDate());
			}


			return temp;
		}

		return null;
	}

	@Override
	public List<Project> getAllProjects() {
		String sql =
				"SELECT * FROM project ;";
		SqlRowSet results =
				jdbcTemplate.queryForRowSet(sql);
		List<Project> list = new ArrayList<>();

		while (results.next()) {

			Project temp = new Project();
			temp.setId(results.getLong("project_id"));
			temp.setName(results.getString("name"));

			if (results.getDate("from_date") != null) {
				temp.setFromDate(results.getDate("from_date").toLocalDate());
			}
			if (results.getDate("to_date") != null) {
				temp.setToDate(results.getDate("to_date").toLocalDate());
			}


			list.add(temp);
		}


		return list;
	}

	@Override
	public Project createProject(Project newProject) {
		String sql =
				"Insert into project(name, from_date, to_date)" +
						"values(?,?,?) returning project_id;";




		SqlRowSet results =
				jdbcTemplate.queryForRowSet(sql,newProject.getName(),newProject.getFromDate(),newProject.getToDate());

		if(results.next()){
			newProject.setId(results.getLong("project_id"));
		}
		return newProject;

	}

	@Override
	public void deleteProject(Long projectId) {
		String sql ="delete from project_employee where project_id=?; " +
						"DELETE from project where project_id=?; " ;

				jdbcTemplate.update(sql,projectId,projectId);

	}
	

}
