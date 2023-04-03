package org.antwalk.ems.view;

import java.sql.Date;

public interface ResignationListAdminView {
    Long getResignationId();
    String getResignationReason();
    Date getResignationDate();
    Boolean getIsApproved();
    Long getEmpId();
}
