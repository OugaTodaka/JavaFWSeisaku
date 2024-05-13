package ohara.ac.jp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ohara.ac.jp.test.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long>{
}
