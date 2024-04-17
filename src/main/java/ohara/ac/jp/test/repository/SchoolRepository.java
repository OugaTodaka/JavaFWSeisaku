package ohara.ac.jp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ohara.ac.jp.test.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long>{

}
