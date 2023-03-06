package org.antwalk.ems.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;

@Entity
@Table
public class FamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column(columnDefinition = "ENUM('MARRIED','SINGLE','UNDISCLOSED')")
    private String maritalStatus;

    @Column(columnDefinition = "ENUM('FATHER','MOTHER','BROTHER','SISTER','CHILD')")
    private String relation;

    @Column
    @Past
    private Date dob;

    @Column(length = 30)
    private String occupation;

    @Column(length = 10)
    private String contactno;

    public FamilyDetails() {
    }

    



    
}
