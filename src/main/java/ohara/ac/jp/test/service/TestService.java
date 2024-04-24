package ohara.ac.jp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ohara.ac.jp.test.model.Test;
import ohara.ac.jp.test.repository.TestRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestRepository testRepository;
	
	public List<Test> searchAll(){
		return testRepository.findAll();
	}
	
	public void update(Test test) {
		testRepository.save(test);
	}
}
