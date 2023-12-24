package com.example.schoolproject.controller;

import com.example.schoolproject.mapper.CourseMapper;
import com.example.schoolproject.pojo.Course;
import com.example.schoolproject.pojo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//单例模式
@Controller
@RequestMapping("/Course")
@CrossOrigin
public class CourseController {
    @Autowired
    CourseMapper courseMapper;
    /*
        private ThreadLocal<String> data = new ThreadLocal<>();
    */
    private int final_id;

    @RequestMapping("selectAll")
    @ResponseBody
    public Page selectAll(HttpServletRequest request, @RequestParam int pageNum) {
        Page<List<Course>> page = new Page<>();
        page.setPageSize(3); // 分页条数2条
        List list = courseMapper.selectAll();
        int totalCount = list.size();
        // 计算总页数  **需要看懂这个逻辑计算**
        page.setTotalPages(totalCount / page.getPageSize() + (totalCount % page.getPageSize() != 0 ? 1 : 0));
        page.setCurrentPage(pageNum);
        // 输入页数小于等于总页数才去查询列表数据
        if (pageNum <= page.getTotalPages()) {
            int start = (page.getCurrentPage() - 1) * page.getPageSize();
            List<Course> mainUserList = courseMapper.getCourseList(start, page.getPageSize());
            page.setData(mainUserList);
        }
        // List<RecordChapgpt> list =recordMapper.selectRecordList(1);
        // mv会自动寻找templates/page/index.html，并进行渲染
        //文件路径
        return page;
    }
    @RequestMapping ("all")
    @ResponseBody
    public List getAllCourse(){
        List<Course> list = courseMapper.selectAll();
        return list;
    }

//    @GetMapping("insert")
//    public ModelAndView insert(HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        // mv会自动寻找templates/page/index.html，并进行渲染
//        //文件路径
//        mv.setViewName("addCourse");
//        return mv;
//    }

    @PostMapping("/doInsert")
    @ResponseBody
    public String doInsert(@RequestBody Course course) {
//        ModelAndView mv = new ModelAndView();
        courseMapper.addCourse(course);
        return  "success";
        // 重定向至需求页
//        mv.setViewName("redirect:/Course/selectAll");

    }

//    @GetMapping("deleteCourse/{id}")
//    public ModelAndView deleteCourse(HttpServletRequest request, @PathVariable("id") int id) {
//        ModelAndView mv = new ModelAndView();
//        courseMapper.deleteCourse(id);
//        // 重定向至需求页
//        mv.setViewName("redirect:/Course/selectAll");
//        return mv;
//    }
    @DeleteMapping("/deleteCourse")
    @ResponseBody
    public String deleteCourse( @RequestParam Integer id) {
        courseMapper.deleteCourse(id);
        return  "success";
    }


//    @GetMapping("update/{id}")
//    public ModelAndView update(HttpServletRequest request, @PathVariable("id") int id) {
//        ModelAndView mv = new ModelAndView();
//        // mv会自动寻找templates/page/index.html，并进行渲染
//        //文件路径
//        /*data.set(Integer.toString(id));
//        Thread t = new Thread(() -> {
//            // 在新建线程中获取线程本地变量中的数据
//            String value = data.get();
//            // 处理请求并调用Service层执行业务逻辑
//
//
//        });
//        t.start();*/
//        Course course = courseMapper.select(id);
//        final_id = course.getTeacher_id();
//        mv.addObject("course", course);
//        mv.setViewName("updateCourse");
//        return mv;
//    }

//    @RequestMapping("doUpdate")
//    public ModelAndView doUpdate(HttpServletRequest request, Course course) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject(course);
//        Course course1 = new Course();
//
//        if (course.getName().length() > 100) {
//            mv.addObject("errorMessage", "课程名称超过100.请重新输入");
//            mv.setViewName("updateCourse");
//        } else {
//            course1.setId(course.getId());
//            course1.setName(course.getName());
//            course1.setHours(course.getHours());
//            course1.setSchools(course.getSchools());
//            course1.setUrl(course.getUrl());
//            course1.setTeacher_id(course.getTeacher_id());
//            courseMapper.updateCourse(course1);
//            // 重定向至需求页
//            mv.setViewName("redirect:/Course/selectAll");
//        }
//        return mv;
//    }
        @PutMapping("/doUpdate")
        @ResponseBody
        public String doUpdate(@RequestBody Course course) {
            Course course1 = new Course();
            course1.setId(course.getId());
            course1.setName(course.getName());
            course1.setHours(course.getHours());
            course1.setSchools(course.getSchools());
            course1.setUrl(course.getUrl());
            course1.setTeacher_id(course.getTeacher_id());
            courseMapper.updateCourse(course1);
            return "success";
    }

    @RequestMapping("teacher_name")
    @ResponseBody
    public List teacher_name(Model model) {
        List<Course> list = courseMapper.selectName();
        int j;
        for (j = 0; j < list.size(); j++) {
            if (list.get(j).getId() == final_id)
                break;
        }
        Course course = list.get(0);
        Course course1 = list.get(j);
        list.set(0, course1);
        list.set(j, course);
        model.addAttribute("list", list);
        return list;
    }


}
