package com.config.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Course {

	//@Value("${course.id}")
	private int courseid;
	
	//@Value("${course.name}")
	private String coursename;
	
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Course(int courseid, String coursename) {
		super();
		this.courseid = courseid;
		this.coursename = coursename;
	}
	public Course() {
		super();
	}
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", coursename=" + coursename + "]";
	}
	
	
}
