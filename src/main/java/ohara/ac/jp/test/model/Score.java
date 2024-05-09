package ohara.ac.jp.test.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEST")
public class Score implements Serializable{
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "STUDENT_NO")
	@Size(min=7,max=7)
	private String student_no;
	
	@NotBlank
	@Column(name = "SUBJECT_CD")
	private String subject_cd;
	
	@NotBlank
	@Column(name = "SCHOOL_CD")
	private String shool_cd;
	
	@NotBlank
	@Column(name = "NO")
	private int no;
	
	@Column(name = "POINT")
	private int point;
	
	@Column(name = "CLASS_NUM")
	private int class_num;
}
