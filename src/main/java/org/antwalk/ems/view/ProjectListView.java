package org.antwalk.ems.view;

import java.sql.Date;

import org.antwalk.ems.model.Employee;

public interface ProjectListView {
	Long getProjId();
	String getProjectName();
	Date getStartDate();
	Date getEndDate();
	Employee getPm();
}
