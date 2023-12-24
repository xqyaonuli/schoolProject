package com.example.schoolproject.mapper;

import com.example.schoolproject.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
        List<Course> selectAll();
        Course select(@Param("id")int id);
        void addCourse(Course course);
        int deleteCourse(@Param("id")int id);
        int updateCourse(Course course);
        List<Course> selectName();
        /**
         * 获取当前用户所有问答总条数
         * @param userid
         * @return
         */
        int getTotalCount(@Param("id")int id);

        /**
         * 分页查询当前用户问答列表
         * @param userid
         * @param currentPage
         * @param pageSize
         * @return
         */
        List<Course> getCourseList(@Param("start") int start,@Param("pageSize") int pageSize);
        //Page<MainUser> getUserList(int pageNum, int pageSize);

}
