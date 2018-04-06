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
    @Column(name = "status")
    private Integer status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
