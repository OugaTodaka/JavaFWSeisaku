package ohara.ac.jp.test.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "STUDENT")
public class Student implements Serializable{
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "NO")
	@Size(min=7, max=7)
	private String no;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ENT_YEAR")
	private Integer ent_year;

	@Column(name = "CLASS_NUM")
	private Integer class_num;

	@Column(name = "IS_ATTEND")
	private Boolean is_attend;

	@Column(name = "SCHOOL_CD")
	private String school_cd;

	@ManyToOne()
	@JoinColumn(name="school_cd",referencedColumnName="cd",insertable=false,updatable=false)
	private School school;

}