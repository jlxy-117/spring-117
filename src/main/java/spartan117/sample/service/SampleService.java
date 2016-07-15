package spartan117.sample.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yecq
 */
@Service
@Transactional
public class SampleService {

    @Autowired
    private JdbcTemplate jdbc;

    // 获取所有学生名单
    public List<Map<String, Object>> getStudentList() {
        return this.jdbc.queryForList("select * from student");
    }

    // 获取指定学号的学生
    public Map<String, Object> getStudentById(String id) {
        return this.jdbc.queryForMap("select * from student where id = ?", id);
    }

    // 增加学生
    public void addStudent(String stu_number, String name, String depart) {
        this.jdbc.update("insert into student (stu_number,name,depart) values(?,?,?)", stu_number, name, depart);
    }

    // 删除学生
    public void removeStudent(String id) {
        this.jdbc.update("delete from student where id = ?", id);
    }

    // 修改学生
    public void modifyStudent(String id, String stu_number, String name, String depart) {
        this.jdbc.update("update student set stu_number=?, name=?, depart=? where id = ?", stu_number, name, depart, id);
    }

    // 抛出异常
    public void exception() {
        throw new IllegalStateException("产生未知错误");
    }
}
