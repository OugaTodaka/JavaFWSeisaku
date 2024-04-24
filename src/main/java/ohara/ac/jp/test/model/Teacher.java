package ohara.ac.jp.test.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEACHER")
public class Teacher implements UserDetails,Serializable{
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TEACHER_ID")
	private String teacherId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SCHOOL_CD")
	private String school_cd;
	
	@ManyToOne()
	@JoinColumn(name="school_cd",referencedColumnName="cd",insertable=false,updatable=false)
	private School school;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		return authorityList;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return teacherId;
	}

//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true; // アカウントの有効期限が切れていないことを示す
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true; // アカウントがロックされていないことを示す
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // 資格情報が有効であることを示す
	}

	@Override
	public boolean isEnabled() {
	    return true; // アカウントが有効化されていることを示す
	}

	@Override
	public String getPassword() {
		return password;
	}


}