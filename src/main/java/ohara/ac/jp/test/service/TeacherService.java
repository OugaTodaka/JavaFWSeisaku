package ohara.ac.jp.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.repository.TeacherRepository;


@Service
public class TeacherService implements UserDetailsService{
	@Autowired
    private TeacherRepository teacherRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String teacherId) throws UsernameNotFoundException {
        // DBベースのユーザー検索
        Teacher teacher = teacherRepository.findByTeacherId (teacherId);
                //.orElseThrow(() -> new UsernameNotFoundException("User not found with teacherId: " + teacherId));
        
        return teacher;
        }

	public boolean authenticate(String teacherId, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		//return null;
	//}
 
}