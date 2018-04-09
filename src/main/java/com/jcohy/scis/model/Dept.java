package com.jcohy.scis.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :院系表
 */
@Entity
@Table(name = "dept")
public class Dept implements Serializable{

    private static final long serialVersionUID = 3L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //院系编号
    @Column(name = "num")
    private Integer num;
    //名字
    @Column(name = "name")
    private String name;
    //院系主任
    @Column(name = "chairman")
    private String chairman;
    //电话
    @Column(name = "tel")
    private String tel;
    //简介
    @Column(name = "description")
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dept{");
        sb.append("id=").append(id);
        sb.append(", num=").append(num);
        sb.append(", name='").append(name).append('\'');
        sb.append(", chairman='").append(chairman).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
