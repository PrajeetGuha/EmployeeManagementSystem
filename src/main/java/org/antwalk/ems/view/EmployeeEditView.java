package org.antwalk.ems.view;

import java.sql.Date;

public interface EmployeeEditView {
    Long getEmpId();
    String getEmpName();
    String getGender();
    String getGradeLevel();
    Date getDoj();
    String getDesignation();
    String getEmptype();
    Integer getProbPeriod();
    Date getProbCompDate();
    Integer getTrainPeriod();
    Date getContractEndDate();
    Integer getServPeriod();
    String getWorkEmail();
    String getBranch();
    String getOffice();
    String getWorkstationId();
    Double getCtc();
    Integer getYearOfExperience();
    String getDepartment();
    Long getTeam();
}
