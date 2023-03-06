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

    public QualificationDetails(Long qdId, String qual, DocFile qualDoc, double percent) {
        this.qdId = qdId;
        this.qual = qual;
        this.qualDoc = qualDoc;
        this.percent = percent;
    }

    public Long getQdId() {
        return qdId;
    }

    public void setQdId(Long qdId) {
        this.qdId = qdId;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public DocFile getQualDoc() {
        return qualDoc;
    }

    public void setQualDoc(DocFile qualDoc) {
        this.qualDoc = qualDoc;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

        
}
