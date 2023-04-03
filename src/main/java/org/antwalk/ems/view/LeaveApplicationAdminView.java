package org.antwalk.ems.view;

import java.sql.Date;

public interface LeaveApplicationAdminView {
    Long getEmpLeaveId();
    String getLeaveType();
    String getLeaveReason();
    Date getLeaveAppliedFor();
    Date getApplicationDate();
    Boolean getIsApproved();
    Long getEmpId();
}
