package ohara.ac.jp.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "TEST")
public class Test {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "STUDENT_NO")
	private String student_no;
	
	@NotBlank
	@Column(name = "SUBJECT_CD")
	private String subject_cd;
	
	@NotBlank
	@Column(name = "SCHOOL_CD")
	private String school_cd;
	
	@Column(name = "NO")
	private Integer no;
	
	@Column(name = "POINT")
	private Integer point;
	
	@Column(name = "CLASS_NUM")
	private String class_num;
}
