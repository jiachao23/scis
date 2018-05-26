package com.jcohy.scis.common;

import java.util.List;

/**
 * Created by jiac on 2018/5/26.
 * ClassName  : com.jcohy.scis.common
 * Description  :
 */
public class Graph {
        private List<Datasets> datasets ;

        private List<String> labels ;

        public void setDatasets(List<Datasets> datasets){
            this.datasets = datasets;
        }
        public List<Datasets> getDatasets(){
            return this.datasets;
        }
        public void setString(List<String> labels){
            this.labels = labels;
        }
        public List<String> getString(){
            return this.labels;
        }

}
