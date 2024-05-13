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
		sql.append("SELECT b From Score b WHERE ");
		
		boolean ent_yearFlg   = false;
		boolean class_numFlg  = false;
		boolean subject_cdFlg = false;
		boolean noFlg         = false;
		boolean andFlg        = false;
		
		if(ent_year != null) {
			sql.append("b.student.ent_year = :ent_year");
			ent_yearFlg  = true;
			andFlg       = true;
		}	

		if(class_num != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("b.class_num = :class_num");
			class_numFlg  = true;
			andFlg       = true;
		}
		
		if(subject_cd != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("b.subject_cd = :subject_cd");
			subject_cdFlg = true;
			andFlg        = true;
		}
		
		if(no != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("b.no = :no");
			noFlg  = true;
			andFlg = true;
		}
		
		System.out.println("テスト9");
		Query query = entityManager.createQuery(sql.toString());
		System.out.println("テスト10");
		
		if (ent_yearFlg) query.setParameter("ent_year",ent_year);
		if (class_numFlg) query.setParameter("class_num",class_num);
		if (subject_cdFlg) query.setParameter("subject_cd",subject_cd);
		if (noFlg) query.setParameter("no",no);
		System.out.println("テスト11");
		return query.getResultList();
	}
}
