package com.config.boot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	
	
	@Autowired
	private Course course;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@PostMapping("/addstudent")
	public void table(@RequestBody Student stud)
	{
		jdbctemplate.execute("create table Student (name varchar(20),email varchar(20),roll varchar(20))");
		String sql3="Insert into Student(name,email,roll) values(?,?,?)";
		jdbctemplate.update(sql3, new Object[]{stud.getName(),stud.getEmail(),stud.getRoll()});
	}
	
	
	@RequestMapping("/getcourses")
	public List<Course> display()
	{
	List<Course> std= new ArrayList<>();
	std.add(new Course(10,"java"));
	std.add(new Course(20,"python"));
	//std.add(new Course(30,"data science"));
	
	return std;
	}
	
	@RequestMapping("/message")
	public String message()
	{	
		return "Hi";
	}

	@RequestMapping("/header")
	public String headerMsg(@RequestHeader(name="ID") String name)
	
	{
		return "header msg "+ name;
		
	}
	
	@RequestMapping("/fetchname")
	public String fetchStudentName(@RequestHeader(name="roll") String id)
	{
		
		String sql2 = "select name from student where roll=?";
		return jdbctemplate.queryForObject(sql2, new Object[] {id}, String.class);
	}
	
	@RequestMapping("/fetchstudent")
	public Student fetchStudent(@RequestHeader(name="sid") String id)
	{
		String sql2 = "select * from student where roll=?";
		return jdbctemplate.queryForObject(sql2, new Object[] {id}, new StudentMapper());
		
	}
	
	@GetMapping("/fetchall")
	public List<Student> fetchall() {
		
		String sql2 = "select * from student";
		return jdbctemplate.query(sql2,new StudentMapper());
		
	}
	@PutMapping("/update/{id}")
	public int updateStudent(@RequestBody Student stud,@PathVariable String id)
	{
		String sql5 ="update Student set name=?, email=? where roll=?";
		return jdbctemplate.update(sql5, new Object[] {stud.getName(),stud.getEmail(),id});
		
	}
	
	private static final class StudentMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student std = new Student();
			std.setName(rs.getString("name"));
			std.setEmail(rs.getString("email"));
			std.setRoll(rs.getInt("roll"));
			return std;
		}
		
	}
	
	@GetMapping("/check/{id}")
	public String path(@PathVariable String id)
	{
	if(id.equalsIgnoreCase("1"))
		return "One";
	else return "Not one";
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/student")
	public String Studentdetails(@RequestBody Student st)
	{
		return "student name is:"+ st.getName()+"email is:"+st.getEmail()+"roll no is:"+st.getRoll()+"Course id is:"+course.getCourseid()+"course name is:"+course.getCoursename();
		
	}
	
	
	
	
	
	
	
	
}
