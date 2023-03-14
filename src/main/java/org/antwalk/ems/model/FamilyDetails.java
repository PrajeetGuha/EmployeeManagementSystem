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

    @Column
    private String relation;

    @Column
    private String name;

    @Column
    @Past
    private Date dob;

    @Column(length = 30)
    private String occupation;

    @Column(length = 10)
    private String contactno;


    
    public FamilyDetails() {
    }

    public FamilyDetails(Long familyId, String relation, String name, @Past Date dob, String occupation,
            String contactno) {
        this.familyId = familyId;
        this.relation = relation;
        this.name = name;
        this.dob = dob;
        this.occupation = occupation;
        this.contactno = contactno;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
    

    
    
    
}
