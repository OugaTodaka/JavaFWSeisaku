package ohara.ac.jp.test.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import ohara.ac.jp.test.dao.StudentDao;
import ohara.ac.jp.test.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private EntityManager entityManager;

	public StudentDaoImpl() {
		super();
	}

	public StudentDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> search(Integer ent_year,Integer class_num,Boolean is_attend){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b From Student b WHERE ");
		
		boolean ent_yearFlg  = false;
		boolean class_numFlg = false;
		boolean is_attendFlg = false;
		boolean andFlg       = false;
		
		if(ent_year != null) {
			sql.append("b.ent_year = :ent_year");
			ent_yearFlg  = true;
			andFlg       = true;
		}	

		if(class_num != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("b.class_num = :class_num");
			class_numFlg  = true;
			andFlg       = true;
		}
		
		if(is_attend != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("b.is_attend = :is_attend");
			is_attendFlg  = true;
			andFlg       = true;
		}
		
		Query query = entityManager.createQuery(sql.toString());
		
		if (ent_yearFlg) query.setParameter("ent_year",ent_year);
		if (class_numFlg) query.setParameter("class_num",class_num);
		if (is_attendFlg) query.setParameter("is_attend",is_attend);
		return query.getResultList();
	}

}
