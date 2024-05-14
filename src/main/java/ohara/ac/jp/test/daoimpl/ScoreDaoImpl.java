package ohara.ac.jp.test.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import ohara.ac.jp.test.dao.ScoreDao;
import ohara.ac.jp.test.model.Score;

@Repository
public class ScoreDaoImpl implements ScoreDao{
	@Autowired
	private EntityManager entityManager;

	public ScoreDaoImpl() {
		super();
	}

	public ScoreDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Score> search(Integer ent_year,Integer class_num,String subject_cd,Integer no){
		System.out.println("テスト7");
		StringBuilder sql = new StringBuilder();
		System.out.println("テスト8");
		sql.append("SELECT b From Score b WHERE b.student.ent_year = :ent_year AND b.class_num = :class_num AND b.subject_cd = :subject_cd AND b.no = :no");
		Query query = entityManager.createQuery(sql.toString());		
		query.setParameter("ent_year",ent_year);
		query.setParameter("class_num",class_num);
		query.setParameter("subject_cd",subject_cd);
		query.setParameter("no",no);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Score> subjectSearch(Integer ent_year,Integer class_num,String subject_cd){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b From Score b WHERE b.student.ent_year = :ent_year AND b.class_num = :class_num AND b.subject_cd = :subject_cd");
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("ent_year",ent_year);
		query.setParameter("class_num",class_num);
		query.setParameter("subject_cd",subject_cd);
		return query.getResultList();
	}
}
