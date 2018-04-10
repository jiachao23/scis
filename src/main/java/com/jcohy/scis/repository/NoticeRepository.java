package com.jcohy.scis.repository;

import com.jcohy.scis.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.repository
 * Description  :
 */
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

    List<Notice> findByStudentNum(Integer studentName);
}
