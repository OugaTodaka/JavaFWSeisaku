package ohara.ac.jp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.model.ClassNum;
import ohara.ac.jp.test.repository.ClassNumRepository;

@Service
@Transactional
public class ClassNumService {
	
	@Autowired
	private ClassNumRepository classNumRepository;
	
	public List<ClassNum> searchAll(){
		return classNumRepository.findAll();
	}
	
	public void update(ClassNum classNum) {
		classNumRepository.save(classNum);
	}
	
	public void insert(ClassNum classNum) {
		classNumRepository.save(classNum);
	}
	
	public void delete(Long id) {
		classNumRepository.deleteById(id);
	}
	
	public List<ClassNum> getbySchool_cd(String school_cd){
		List<ClassNum>cla = this.classNumRepository.findBySchool_cd(school_cd);
		return cla;
	}
}
