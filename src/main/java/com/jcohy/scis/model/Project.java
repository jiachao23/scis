package com.jcohy.scis.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ClassName: Project
 * Description:
 **/
@Entity
@Table(name = "project")
public class Project implements Serializable{

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "pro_resource")
    private String proResource;
    @Column(name = "money_resource")
    private String moneyResource;
    @Column(name = "desc")
    private String desc;
    @Column(name = "e_status")
    private Integer EStatus;
    @Column(name = "e_reason")
    private String EReason;
    @Column(name = "a_status")
    private Integer AStatus;
    @Column(name = "a_reason")
    private String AReason;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProResource() {
        return proResource;
    }

    public void setProResource(String proResource) {
        this.proResource = proResource;
    }

    public String getMoneyResource() {
        return moneyResource;
    }

    public void setMoneyResource(String moneyResource) {
        this.moneyResource = moneyResource;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getEStatus() {
        return EStatus;
    }

    public void setEStatus(Integer EStatus) {
        this.EStatus = EStatus;
    }

    public String getEReason() {
        return EReason;
    }

    public void setEReason(String EReason) {
        this.EReason = EReason;
    }

    public Integer getAStatus() {
        return AStatus;
    }

    public void setAStatus(Integer AStatus) {
        this.AStatus = AStatus;
    }

    public String getAReason() {
        return AReason;
    }

    public void setAReason(String AReason) {
        this.AReason = AReason;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
