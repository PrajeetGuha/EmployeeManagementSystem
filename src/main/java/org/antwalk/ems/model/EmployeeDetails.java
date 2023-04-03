package org.antwalk.ems.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empDetId;

    // @OneToOne
    // private DocFile profilePicDoc;

    @Column
    private String maritalStatus;

    @Column
    private String permaAddress;

    @Column(length = 255)
    private String primaryContactno;

    @Column(length = 255)
    private String emergencyContactno;

    @Column(length = 255, unique = true)
    @Email
    private String emailId;

    @Column
    private String presentAddress;

    @Column(length = 255)
    private String nationality;

    @Column(length = 255)
    private String bloodGrp;

    // @OneToOne
    // private DocFile pancardDoc;

    @Column(length = 255)
    private String pancardnno;

    // @OneToOne
    // private DocFile adhaarDoc;

    @Column(length = 255)
    private String adhaarno;

    // @OneToOne
    // private DocFile passportDoc;

    @Column(length = 255)
    private String passportno;

    @OneToMany
    @JoinColumn
    private List<QualificationDetails> listQualificationDetails;

    @OneToMany
    @JoinColumn
    private List<FamilyDetails> listFamilyDetails;

    @OneToMany
    @JoinColumn
    private List<ProfDetails> listProfDetails;
    
    public void addQualification(QualificationDetails qualificationDetails) {
		this.listQualificationDetails.add(qualificationDetails);
	}
    
    public void addProfessionalDetails(ProfDetails profDetails) {
		this.listProfDetails.add(profDetails);
	}

    public void addFamilyDetails(FamilyDetails familyDetails) {
		this.listFamilyDetails.add(familyDetails);
	}
    public EmployeeDetails() {
    }

    

    @Override
    public String toString() {
        return "EmployeeDetails [empDetId=" + empDetId + ", maritalStatus=" + maritalStatus + ", permaAddress="
                + permaAddress + ", primaryContactno=" + primaryContactno + ", emergencyContactno=" + emergencyContactno
                + ", emailId=" + emailId + ", presentAddress=" + presentAddress + ", nationality=" + nationality
                + ", bloodGrp=" + bloodGrp + ", pancardnno=" + pancardnno + ", adhaarno=" + adhaarno + ", passportno="
                + passportno + ", listQualificationDetails=" + listQualificationDetails + ", listFamilyDetails="
                + listFamilyDetails + ", listProfDetails=" + listProfDetails + "]";
    }

    // public EmployeeDetails(Long empDetId, DocFile profilePicDoc, String permaAddress, String primaryContactno,
    //         String emergencyContactno, @Email String emailId, String presentAddress, String nationality,
    //         String bloodGrp, DocFile pancardDoc, String pancardnno, DocFile adhaarDoc, String adhaarno,
    //         DocFile passportDoc, String passportno, List<QualificationDetails> listQualificationDetails,
    //         List<FamilyDetails> listFamilyDetails, List<ProfDetails> listProfDetails) {
    //     this.empDetId = empDetId;
    //     this.profilePicDoc = profilePicDoc;
    //     this.permaAddress = permaAddress;
    //     this.primaryContactno = primaryContactno;
    //     this.emergencyContactno = emergencyContactno;
    //     this.emailId = emailId;
    //     this.presentAddress = presentAddress;
    //     this.nationality = nationality;
    //     this.bloodGrp = bloodGrp;
    //     this.pancardDoc = pancardDoc;
    //     this.pancardnno = pancardnno;
    //     this.adhaarDoc = adhaarDoc;
    //     this.adhaarno = adhaarno;
    //     this.passportDoc = passportDoc;
    //     this.passportno = passportno;
    //     this.listQualificationDetails = listQualificationDetails;
    //     this.listFamilyDetails = listFamilyDetails;
    //     this.listProfDetails = listProfDetails;
    // }

    

    public Long getEmpDetId() {
        return empDetId;
    }

    public EmployeeDetails(Long empDetId, String maritalStatus, String permaAddress, String primaryContactno,
            String emergencyContactno, @Email String emailId, String presentAddress, String nationality,
            String bloodGrp, String pancardnno, String adhaarno, String passportno,
            List<QualificationDetails> listQualificationDetails, List<FamilyDetails> listFamilyDetails,
            List<ProfDetails> listProfDetails) {
        this.empDetId = empDetId;
        this.maritalStatus = maritalStatus;
        this.permaAddress = permaAddress;
        this.primaryContactno = primaryContactno;
        this.emergencyContactno = emergencyContactno;
        this.emailId = emailId;
        this.presentAddress = presentAddress;
        this.nationality = nationality;
        this.bloodGrp = bloodGrp;
        this.pancardnno = pancardnno;
        this.adhaarno = adhaarno;
        this.passportno = passportno;
        this.listQualificationDetails = listQualificationDetails;
        this.listFamilyDetails = listFamilyDetails;
        this.listProfDetails = listProfDetails;
    }

    public void setEmpDetId(Long empDetId) {
        this.empDetId = empDetId;
    }

    // public DocFile getProfilePicDoc() {
    //     return profilePicDoc;
    // }

    // public void setProfilePicDoc(DocFile profilePicDoc) {
    //     this.profilePicDoc = profilePicDoc;
    // }

    public String getPermaAddress() {
        return permaAddress;
    }

    public void setPermaAddress(String permaAddress) {
        this.permaAddress = permaAddress;
    }

    public String getPrimaryContactno() {
        return primaryContactno;
    }

    public void setPrimaryContactno(String primaryContactno) {
        this.primaryContactno = primaryContactno;
    }

    public String getEmergencyContactno() {
        return emergencyContactno;
    }

    public void setEmergencyContactno(String emergencyContactno) {
        this.emergencyContactno = emergencyContactno;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    // public DocFile getPancardDoc() {
    //     return pancardDoc;
    // }

    // public void setPancardDoc(DocFile pancardDoc) {
    //     this.pancardDoc = pancardDoc;
    // }

    public String getPancardnno() {
        return pancardnno;
    }

    public void setPancardnno(String pancardnno) {
        this.pancardnno = pancardnno;
    }

    // public DocFile getAdhaarDoc() {
    //     return adhaarDoc;
    // }

    // public void setAdhaarDoc(DocFile adhaarDoc) {
    //     this.adhaarDoc = adhaarDoc;
    // }

    public String getAdhaarno() {
        return adhaarno;
    }

    public void setAdhaarno(String adhaarno) {
        this.adhaarno = adhaarno;
    }

    // public DocFile getPassportDoc() {
    //     return passportDoc;
    // }

    // public void setPassportDoc(DocFile passportDoc) {
    //     this.passportDoc = passportDoc;
    // }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public List<QualificationDetails> getListQualificationDetails() {
        return listQualificationDetails;
    }

    public void setListQualificationDetails(List<QualificationDetails> listQualificationDetails) {
        this.listQualificationDetails = listQualificationDetails;
    }

    public List<FamilyDetails> getListFamilyDetails() {
        return listFamilyDetails;
    }

    public void setListFamilyDetails(List<FamilyDetails> listFamilyDetails) {
        this.listFamilyDetails = listFamilyDetails;
    }

    public List<ProfDetails> getListProfDetails() {
        return listProfDetails;
    }

    public void setListProfDetails(List<ProfDetails> listProfDetails) {
        this.listProfDetails = listProfDetails;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    
}
