package ohara.ac.jp.test.dao;

import java.io.Serializable;
import java.util.List;

import ohara.ac.jp.test.model.Student;

public interface StudentDao extends Serializable{
	
	public List<Student> search(Integer ent_year,String class_num,Boolean is_attend);
	
}
