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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBJECT")
public class Subject implements Serializable{
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SCHOOL_CD")
	private String school_cd;

	@ManyToOne()
	@JoinColumn(name="school_cd",referencedColumnName="cd",insertable=false,updatable=false)
	private School school;

	@Column(name = "CD")
	private String cd;

	@Column(name = "NAME")
	private String name;

}