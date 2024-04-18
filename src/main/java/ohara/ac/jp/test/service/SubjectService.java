package ohara.ac.jp.test.service;

import java.util.List;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;

import ohara.ac.jp.test.repository.SubjectRepository;

public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> searchAll(){
		return subjectRepository.findAll();
	}

	
	public void insert(Subject subject) {
		subjectRepository.save(subject);
	}
	
	public void update(Subject subject) {
		subjectRepository.save(subject);
	}
	
	public void delete(Long id) {
		subjectRepository.deleteById(id);
	}
	
	public Subject get(Long id) {
		Subject stu = this.subjectRepository.findById(id).orElse(new Subject());
		return stu;
	}
}
