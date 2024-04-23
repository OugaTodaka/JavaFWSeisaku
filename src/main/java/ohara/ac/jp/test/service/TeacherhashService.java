package ohara.ac.jp.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.repository.TeacherRepository;

@Service
public class TeacherhashService {
    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // パスワードエンコーダーの注入
    
    public void hashTeacher(Teacher teacherModel) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherModel.getTeacherId());

        teacher.setPassword(passwordEncoder.encode(teacherModel.getPassword()));

        //teacher.setPassword(teacherModel.getPassword());
        teacher.setSchoolCd(teacherModel.getSchoolCd());
        teacher.setName(teacherModel.getName());
        teacherRepository.save(teacher);
    }
}
