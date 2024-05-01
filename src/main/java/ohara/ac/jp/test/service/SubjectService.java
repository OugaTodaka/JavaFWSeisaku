package ohara.ac.jp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.model.Subject;
import ohara.ac.jp.test.repository.SubjectRepository;

@Service
@Transactional
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
		Subject sub = this.subjectRepository.findById(id).orElse(new Subject());
		return sub;
	}
	
	public List<Subject> getbySchool_cd(String school_cd) {
		List<Subject>sub = this.subjectRepository.findBySchool_cd(school_cd);
		return sub;
	}
	
	public List<Subject> getbyCd(String cd) {
		List<Subject>sub = this.subjectRepository.findByCd(cd);
		return sub;
	}
}
