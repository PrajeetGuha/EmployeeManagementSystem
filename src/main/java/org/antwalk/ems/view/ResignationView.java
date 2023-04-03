package org.antwalk.ems.view;

import java.sql.Date;

import org.antwalk.ems.model.Admin;

public interface ResignationView {
    String getResignationReason();
    Date getResignationDate();
    Boolean getIsApproved();
    Long getApprovedBy();
}
