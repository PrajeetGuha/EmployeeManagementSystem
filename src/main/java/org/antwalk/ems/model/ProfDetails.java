package org.antwalk.ems.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

@Entity
@Table
public class ProfDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profDelId;

    @Column(length = 50)
    private String nameOfPrevOrg;

    @Column(length = 30)
    private String designation;

    @Column
    @Past
    private Date fromDate;

    @Column
    @Past
    private Date toDate;

    @OneToOne
    private DocFile releaseLetterDoc;

    public ProfDetails() {
    }

    public ProfDetails(Long profDelId, String nameOfPrevOrg, String designation, @Past Date fromDate, @Past Date toDate,
            DocFile releaseLetterDoc) {
        this.profDelId = profDelId;
        this.nameOfPrevOrg = nameOfPrevOrg;
        this.designation = designation;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.releaseLetterDoc = releaseLetterDoc;
    }

    public Long getProfDelId() {
        return profDelId;
    }

    public void setProfDelId(Long profDelId) {
        this.profDelId = profDelId;
    }

    public String getNameOfPrevOrg() {
        return nameOfPrevOrg;
    }

    public void setNameOfPrevOrg(String nameOfPrevOrg) {
        this.nameOfPrevOrg = nameOfPrevOrg;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public DocFile getReleaseLetterDoc() {
        return releaseLetterDoc;
    }

    public void setReleaseLetterDoc(DocFile releaseLetterDoc) {
        this.releaseLetterDoc = releaseLetterDoc;
    }

    

    
}
