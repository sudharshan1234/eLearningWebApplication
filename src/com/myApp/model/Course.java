package com.myApp.model;

public class Course {

    private int courseId;
    private int userId;
	private String cName;
    private String cDesp;
    private String cFees;
    private String cResource;

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDesp() {
        return cDesp;
    }

    public void setcDesp(String cDesp) {
        this.cDesp = cDesp;
    }

    public String getcFees() {
        return cFees;
    }

    public void setcFees(String cFees) {
        this.cFees = cFees;
    }

    public String getcResource() {
        return cResource;
    }

    public void setcResource(String cResource) {
        this.cResource = cResource;
    }

}
