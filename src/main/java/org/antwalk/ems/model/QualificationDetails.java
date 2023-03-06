package org.antwalk.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class QualificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qdId;
    
    @Column(length = 50)
    private String qual;

    @OneToOne
    private DocFile qualDoc;

    @Column
    private double percent;

    

    public QualificationDetails() {
    }

        
}
