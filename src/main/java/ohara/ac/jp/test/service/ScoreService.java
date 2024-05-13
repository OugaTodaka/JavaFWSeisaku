package ohara.ac.jp.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.daoimpl.ScoreDaoImpl;
import ohara.ac.jp.test.model.Score;
import ohara.ac.jp.test.repository.ScoreRepository;

@Service
@Transactional
public class ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private ScoreDaoImpl scoreDaoImpl;
	
	public List<Score> searchAll(){
		return scoreRepository.findAll();
	}

	public void insert(Score score) {
		scoreRepository.save(score);
	}
	
	public void update(Score score) {
		scoreRepository.save(score);
	}
	
	public void delete(Long id) {
		scoreRepository.deleteById(id);
	}

	public Score get(Long id) {
		Score sco = this.scoreRepository.findById(id).orElse(new Score());
		return sco;
	}
	
	public List<Score> search(Integer ent_year,Integer class_num,String subject_cd,Integer no){
		List<Score> result = new ArrayList<Score>();
		result = scoreDaoImpl.search(ent_year, class_num, subject_cd, no);
		System.out.println("表示");
		return result;
	}
	
	public List<Score> subjectSearch(Integer ent_year,Integer class_num,String subject_cd){
		List<Score> result = new ArrayList<Score>();
		result = scoreDaoImpl.subjectSearch(ent_year, class_num, subject_cd);
		System.out.println("科目検索");
		return result;

	}
}
