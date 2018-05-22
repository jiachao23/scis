package com.jcohy.scis.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

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
    @Column(name = "description")
    private String desc = " ";
    @Column(name = "e_status")
    private Integer EStatus;
    @Column(name = "e_reason")
    private String EReason;
    @Column(name = "t_status")
    private Integer TStatus;
    @Column(name = "t_reason")
    private String TReason;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne
    @JoinColumn(name = "book_video_id")
    private Book video;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "update_date")
    private String uodateDate;

    @Transient
    private Expert expert;
    @Transient
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

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

    public Integer getTStatus() {
        return TStatus;
    }

    public void setTStatus(Integer TStatus) {
        this.TStatus = TStatus;
    }

    public String getTReason() {
        return TReason;
    }

    public void setTReason(String TReason) {
        this.TReason = TReason;
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

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUodateDate() {
        return uodateDate;
    }

    public void setUodateDate(String uodateDate) {
        this.uodateDate = uodateDate;
    }

    public Book getVideo() {
        return video;
    }

    public void setVideo(Book video) {
        this.video = video;
    }

}
