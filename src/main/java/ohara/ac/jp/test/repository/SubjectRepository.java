package ohara.ac.jp.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ohara.ac.jp.test.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{
	public List<Subject> findBySchool_cd(String school_cd);
	public Subject findByCd(String cd);
}
