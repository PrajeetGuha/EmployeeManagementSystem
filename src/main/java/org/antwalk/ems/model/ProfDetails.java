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

    

    
}
