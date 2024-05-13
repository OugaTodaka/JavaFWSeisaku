package ohara.ac.jp.test.dao;

import java.io.Serializable;
import java.util.List;

import ohara.ac.jp.test.model.Score;

public interface ScoreDao extends Serializable{

	public List<Score> search(Integer ent_year,Integer class_num,String subject_cd,Integer no);
	public List<Score> subjectSearch(Integer ent_year,Integer class_num,String subject_cd);

}
