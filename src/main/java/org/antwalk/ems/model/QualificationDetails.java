package org.antwalk.ems.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class QualificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qdId;
    
    @Column(length = 255)
    private String qual;

    @OneToOne
    private DocFile qualDoc;

    @Column
    private Double percent;
    
    @Column
    private Date startDate;
    
    @Column
    private Date endDate;
    
//    @ManyToOne
//    private EmployeeDetails employeeDetails;
//    
//    

    public QualificationDetails() {
    }


	












	public QualificationDetails(Long qdId, String qual, DocFile qualDoc, Double percent, Date startDate, Date endDate) {
		super();
		this.qdId = qdId;
		this.qual = qual;
		this.qualDoc = qualDoc;
		this.percent = percent;
		this.startDate = startDate;
		this.endDate = endDate;
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


	public Double getPercent() {
		return percent;
	}


	public void setPercent(Double percent) {
		this.percent = percent;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    



 
}
