package ohara.ac.jp.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import ohara.ac.jp.test.model.School;
import ohara.ac.jp.test.model.Student;
import ohara.ac.jp.test.model.Subject;
import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.service.SchoolService;
import ohara.ac.jp.test.service.StudentService;
import ohara.ac.jp.test.service.SubjectService;

@Controller
public class MainController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private SubjectService subjectService;

	@RequestMapping("")
	public ModelAndView index(ModelAndView mav,HttpServletRequest HttpServletRequest,@AuthenticationPrincipal Teacher teacher) {
//		System.out.println(username);
		mav.addObject("username",teacher);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/student")
	public ModelAndView student(ModelAndView mav,@AuthenticationPrincipal Teacher teacher){
		mav.setViewName("student");
		mav.addObject("username",teacher);
		List<Student>student = studentService.searchAll();
		List<School>school = schoolService.searchAll();
		mav.addObject("student",student);
		mav.addObject("school",school);
		return mav;
	}
	
	@PostMapping("/student")
	public String studentSeach(Student student,Model model,@AuthenticationPrincipal Teacher teacher) {
		model.addAttribute("username",teacher);
		model.addAttribute("msg","検索結果");
		List<School>school = schoolService.searchAll();
		List<Student> result = studentService.search(student.getEnt_year(), student.getClass_num(), student.getIs_attend());
		System.out.println("入学年度："+student.getEnt_year());
		System.out.println("クラスコード："+student.getClass_num());
		System.out.println("在学中："+student.getIs_attend());
		System.out.println(result);
		model.addAttribute("school",school);
		model.addAttribute("student",result);
		return "student";
	}

	@RequestMapping("/subject")
	public ModelAndView subject(ModelAndView mav,@AuthenticationPrincipal Teacher teacher){
		mav.setViewName("subject");
		List<Subject>subject = subjectService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("username",teacher);
		mav.addObject("subject",subject);
		return mav;
	}
	
	@RequestMapping("/score")
	public ModelAndView score(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("score");
		return mav;
	}

	@GetMapping("/student/add")
	public ModelAndView studentAdd(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.setViewName("studentadd");
		List<School>list = schoolService.searchAll();
		model.addObject("username",teacher);
		model.addObject("school",list);
		return model;
	}

	@PostMapping("/student/add")
	public String studentAddRun(@ModelAttribute Student stu) {
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
	
	@GetMapping("/subject/add")
	public ModelAndView subjectAdd(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("subjectadd");
		return model;
	}
	
	@PostMapping("/subject/add")
	public String subjectAddRun(@ModelAttribute Subject sub,@AuthenticationPrincipal Teacher teacher) {
		try {
			sub.setCd(sub.getCd());
			sub.setSchool_cd(teacher.getSchool_cd());
			sub.setName(sub.getName());
			subjectService.insert(sub);
			System.out.println(sub);
			return "redirect:/subject";
		}catch(DataIntegrityViolationException e) {
			return "addError";
		}
	}
	
	@GetMapping("/score/add")
	public ModelAndView scoreAdd(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("scoreadd");
		return mav;
	}
	
	@PostMapping("/score/add")
	public String scoreAddRun(ModelAndView mav) {
		return "redirect:/score";
	}
	
	@GetMapping("/student/edit/{id}")
	public ModelAndView studentEdit(@PathVariable(name="id")Long id,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("studentedit");
		Student stu = studentService.get(id);
		List<School>list = schoolService.searchAll();
		mav.addObject("school",list);
		mav.addObject("stu",stu);
		return mav;
	}

	@PostMapping("/student/edit/{id}")
	public String studentEditRun(@PathVariable(name="id")Long id,@ModelAttribute Student form) {
		Student stu = studentService.get(id);
		stu.setName(form.getName());
		stu.setClass_num(form.getClass_num());
		stu.setSchool_cd(form.getSchool_cd());
		studentService.update(stu);
		System.out.println("更新："+stu);
		return "redirect:/student";
	}
	
	@GetMapping("/subject/edit/{id}")
	public ModelAndView subjectEdit(@PathVariable(name="id")Long id,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("subjectedit");
		Subject sub = subjectService.get(id);
		mav.addObject("sub",sub);
		return mav;
	}

	@PostMapping("/subject/edit/{id}")
	public String subjectEditRun(@PathVariable(name="id")Long id,@ModelAttribute Subject form) {
		Subject sub = subjectService.get(id);
		sub.setName(form.getName());
		subjectService.update(sub);
		System.out.println("更新："+sub);
		return "redirect:/subject";
	}
	
	@GetMapping("/student/delete/{id}")
	public ModelAndView studentDelete(@PathVariable(name="id")Long id,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("studentdelete");
		Student stu = studentService.get(id);
		List<School>list = schoolService.searchAll();
		mav.addObject("school",list);
		mav.addObject("stu",stu);
		return mav;
	}
	
	@PostMapping("/student/delete/{id}")
	public String studentDeleteRun(@PathVariable(name="id")Long id) {
		Student stu = studentService.get(id);
		stu.setIs_attend(false);
		studentService.update(stu);
		System.out.println("削除："+stu);
		return "redirect:/student";
	}
	
	@GetMapping("/subject/delete/{id}")
	public ModelAndView subjectDelete(@PathVariable(name="id")Long id,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("subjectdelete");
		Subject sub = subjectService.get(id);
		mav.addObject("sub",sub);
		return mav;
	}

	@PostMapping("/subject/delete/{id}")
	public String subjectDeleteRun(@PathVariable(name="id")Long id) {
		Subject sub = subjectService.get(id);
		subjectService.delete(id);
		System.out.println("削除："+sub);
		return "redirect:/subject";
	}
}
