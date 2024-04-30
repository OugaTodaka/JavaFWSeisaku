package ohara.ac.jp.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ohara.ac.jp.test.model.ClassNum;

@Repository
public interface ClassNumRepository extends JpaRepository<ClassNum,Long>{
	public List<ClassNum> findBySchool_cd(String school_cd);
}