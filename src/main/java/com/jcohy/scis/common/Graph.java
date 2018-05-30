package com.jcohy.scis.common;

import java.util.Map;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:51 2018/5/30
 * Email: jia_chao23@126.com
 * ClassName: Graph
 * Description:
 **/
public class Graph {

    private Map<String,Integer> type;

    private Map<String,Integer> deptMap;

    private Map<String,Integer> year;

    public Map<String, Integer> getType() {
        return type;
    }

    public void setType(Map<String, Integer> type) {
        this.type = type;
    }

    public Map<String, Integer> getDeptMap() {
        return deptMap;
    }

    public void setDeptMap(Map<String, Integer> deptMap) {
        this.deptMap = deptMap;
    }

    public Map<String, Integer> getYear() {
        return year;
    }

    public void setYear(Map<String, Integer> year) {
        this.year = year;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph{");
        sb.append("type=").append(type);
        sb.append(", deptMap=").append(deptMap);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }
}
