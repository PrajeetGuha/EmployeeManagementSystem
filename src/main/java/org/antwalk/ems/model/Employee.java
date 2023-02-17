package org.antwalk.ems.model;

import java.sql.Blob;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column
    private String name;

    @Column
    private char gender;

    @Column
    private Date doj;

    @Column
    private String contactNo;

    @Column
    private Date dob;

    public Employee() {
    }

    public Employee(Long empId, String name, char gender, Date doj, String contactNo, Date dob) {
        this.empId = empId;
        this.name = name;
        this.gender = gender;
        this.doj = doj;
        this.contactNo = contactNo;
        this.dob = dob;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", gender=" + gender + ", doj=" + doj + ", contactNo="
                + contactNo + ", dob=" + dob + "]";
    }



    // public Blob getAadhaarCard() {
    //     return aadhaarCard;
    // }



    // public void setAadhaarCard(Blob aadhaarCard) {
    //     this.aadhaarCard = aadhaarCard;
    // }

    
}
