package ohara.ac.jp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.model.School;
import ohara.ac.jp.test.repository.SchoolRepository;

@Service
@Transactional
public class SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;
	
	public List<School> searchAll(){
		return schoolRepository.findAll();
	}
	
	public void insert(School school) {
		schoolRepository.save(school);
	}

}
