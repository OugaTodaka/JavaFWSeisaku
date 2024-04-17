package ohara.ac.jp.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ohara.ac.jp.test.model.School;
import ohara.ac.jp.test.model.Student;
import ohara.ac.jp.test.service.SchoolService;
import ohara.ac.jp.test.service.StudentService;

@Controller
public class MainController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private SchoolService schoolService;

	@RequestMapping("/student")
	public ModelAndView student(ModelAndView model){
		model.setViewName("student");
		List<Student>student = studentService.searchAll();
		model.addObject("student",student);
		return model;
	}

	@GetMapping("/studentadd")
	public ModelAndView studentAdd(@ModelAttribute Student stu,ModelAndView model) {
		model.setViewName("studentadd");
		List<School>list = schoolService.searchAll();
		model.addObject("school",list);
		return model;
	}

	@PostMapping("/studentadd")
	public String studentAddRun(@ModelAttribute Student stu,ModelAndView mav) {
		try {
			stu.setNo(stu.getNo());
			stu.setName(stu.getName());
			stu.setEnt_year(stu.getEnt_year());
			stu.setClass_num(stu.getClass_num());
			stu.setIs_attend(true);
			stu.setSchool_cd(stu.getSchool_cd());
			studentService.insert(stu);
			System.out.println("登録："+stu);
			return "redirect:/student";
		}catch(DataIntegrityViolationException e){
			return "addError";
		}
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView studentEdit(@PathVariable(name="id")Long id,ModelAndView mav) {
		mav.setViewName("edit");
		Student stu = studentService.get(id);
		List<School>list = schoolService.searchAll();
		mav.addObject("school",list);
		mav.addObject("stu",stu);
		return mav;
	}
	
	@PostMapping("/edit/{id}")
	public String studentEditRun(@PathVariable(name="id")Long id,@ModelAttribute Student form) {
		Student stu = studentService.get(id);
		stu.setName(form.getName());
		stu.setClass_num(form.getClass_num());
		stu.setSchool_cd(form.getSchool_cd());
		studentService.update(stu);
		System.out.println("更新："+stu);
		return "redirect:/student";
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView studentDelete(@PathVariable(name="id")Long id,ModelAndView mav) {
		mav.setViewName("delete");
		return mav;
	}
	
	@PostMapping("/delete/{id}")
	public String studentDeleteRun(@PathVariable(name="id")Long id) {
		Student stu = studentService.get(id);
		stu.setIs_attend(false);
		studentService.update(stu);
		System.out.println("削除："+stu);
		return "redirect:/student";
	}

}
