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
		System.out.println("テスト3");
		List<Score> result = new ArrayList<Score>();
		System.out.println("テスト4");
		if (ent_year == null && class_num == null && subject_cd == null && no == null) {
			System.out.println("テスト5");
			result = scoreRepository.findAll();
			System.out.println("全件表示");
		}else {
			System.out.println("テスト6");
			result = scoreDaoImpl.search(ent_year, class_num, subject_cd, no);
			System.out.println("表示");
		}
		return result;
	}
}
