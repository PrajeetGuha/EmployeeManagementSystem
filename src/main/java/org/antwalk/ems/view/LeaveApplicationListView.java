package org.antwalk.ems.view;

import java.sql.Date;

public interface LeaveApplicationListView {
    String getLeaveType();
    String getLeaveReason();
    Date getLeaveAppliedFor();
    Boolean getIsApproved();
}
