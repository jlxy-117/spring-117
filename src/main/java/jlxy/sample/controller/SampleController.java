package jlxy.sample.controller;

import java.util.List;
import java.util.Map;
import jlxy.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yecq
 */
@RestController
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    private SampleService sv;

    @RequestMapping("/get_student_list")
    public List<Map<String, Object>> do_getStudentList() {
        return sv.getStudentList();
    }

    @RequestMapping("/get_student")
    public Map<String, Object> do_getStudentById(@RequestParam("id") String id) {
        return sv.getStudentById(id);
    }

    @RequestMapping("/add_student")
    public void do_addStudent(@RequestParam("stu_number") String stu_number, @RequestParam("name") String name, @RequestParam("depart") String depart) {
        sv.addStudent(stu_number, name, depart);
    }

    @RequestMapping("/remove_student")
    public void do_removeStudent(@RequestParam("id") String id) {
        sv.removeStudent(id);
    }

    @RequestMapping("/modify_student")
    public void do_modifyStudent(@RequestParam("id") String id, @RequestParam("stu_number") String stu_number, @RequestParam("name") String name, @RequestParam("depart") String depart) {
        sv.modifyStudent(id, stu_number, name, depart);
    }
}
