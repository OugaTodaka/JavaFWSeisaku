package ohara.ac.jp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ohara.ac.jp.test.model.Teacher;

//public interface TeacherRepository extends JpaRepository<Teacher, Long>
//{
//  public Teacher findByTeacherId(String teacher_id);
//
//}
//
//@Repository
//public interface TeacherRepository extends JpaRepository<Teacher, Long>{
//
//    // メールアドレスによりユーザーを検索
//	Teacher findByTeacherId(String teacher_id);
//}

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByTeacherId(String teacher_id);
}


