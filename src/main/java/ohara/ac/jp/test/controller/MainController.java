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
import ohara.ac.jp.test.model.ClassNum;
import ohara.ac.jp.test.model.School;
import ohara.ac.jp.test.model.Score;
import ohara.ac.jp.test.model.Student;
import ohara.ac.jp.test.model.Subject;
import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.service.ClassNumService;
import ohara.ac.jp.test.service.SchoolService;
import ohara.ac.jp.test.service.ScoreService;
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
	@Autowired
	private ClassNumService classNumService;
	@Autowired
	private ScoreService scoreService;
	

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
		List<Subject>subject =subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("subject",subject);
		mav.addObject("class",cla);
		mav.setViewName("score");
		return mav;
	}

	@PostMapping("/scoresubject")
	public ModelAndView scoresubject(@ModelAttribute Student stu,@ModelAttribute Score sco,@ModelAttribute Subject sub,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.setViewName("scoresubject");
		mav.addObject("username",teacher);
		String sub_cd = sco.getSubject_cd();
		List<Score>scoresub = scoreService.subjectSearch(stu.getEnt_year(), sco.getClass_num(), sub_cd);
		Subject subject = subjectService.getByCdAndSchool_cd(sub_cd,teacher.getSchool_cd());
		System.out.println(scoresub);
		System.out.println(subject);
		List<School>school = schoolService.searchAll();
		List<Subject>subjectlist = subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("school",school);
		mav.addObject("subjectlist",subjectlist);
		mav.addObject("subject",subject);
		mav.addObject("sub",scoresub);
		mav.addObject("class",cla);

		if (scoresub.size() != 0) {
			return mav;
		}else {
			mav.setViewName("redirect:/score/searchfaild");
			return mav;
		}
	}

	@PostMapping("/scorestudent")
	public ModelAndView scorestudent(@ModelAttribute Student stu,ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.setViewName("scorestudent");
		mav.addObject("username",teacher);
		String stu_no = stu.getNo();
		List<Score> scorestu = scoreService.studentSearch(teacher.getSchool_cd(),stu_no);
		Student student = studentService.getbyNo(stu_no);
		System.out.println(teacher.getSchool_cd());
		System.out.println(stu.getNo());
		System.out.println(scorestu);
		List<School>school = schoolService.searchAll();
		List<Subject>subject =subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("school",school);
		mav.addObject("subject",subject);
		mav.addObject("student",student);
		mav.addObject("stu",scorestu);
		mav.addObject("class",cla);

		if (scorestu.size() != 0) {
			return mav;
		}else {
			mav.setViewName("redirect:/score/searchfaild");
			return mav;
		}
	}

	@RequestMapping("/score/searchfaild")
	public ModelAndView searchFaild(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("notfound","・・・なにも!!! な゛かったッ...!!!!");
		mav.setViewName("score");
		mav.addObject("username",teacher);
		List<Subject>subject =subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("subject",subject);
		mav.addObject("class",cla);
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
	public String studentAddRun(Model model,@ModelAttribute Student stu,@AuthenticationPrincipal Teacher teacher) {
		try {
			stu.setNo(stu.getNo());
			stu.setName(stu.getName());
			stu.setEnt_year(stu.getEnt_year());
			stu.setClass_num(stu.getClass_num());
			stu.setIs_attend(true);
			stu.setSchool_cd(stu.getSchool_cd());
			studentService.insert(stu);
			System.out.println("登録："+stu);
			return "redirect:/student/add/success";
		}catch(DataIntegrityViolationException e){
			model.addAttribute("username",teacher);
			return "addError";
		}
	}

	@RequestMapping("/student/add/success")
	public ModelAndView studentAddSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher, Student stu) {
		model.addObject("username",teacher);
		model.setViewName("studentaddsuccess");
		return model;
	}

	@GetMapping("/subject/add")
	public ModelAndView subjectAdd(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("subjectadd");
		return model;
	}

	@PostMapping("/subject/add")
	public String subjectAddRun(Model model,@ModelAttribute Subject sub,@AuthenticationPrincipal Teacher teacher) {
		try {
			sub.setCd(sub.getCd());
			sub.setSchool_cd(teacher.getSchool_cd());
			sub.setName(sub.getName());
			subjectService.insert(sub);
			System.out.println(sub);
			return "redirect:/subject/add/success";
		}catch(DataIntegrityViolationException e) {
			model.addAttribute("username",teacher);
			return "addError";
		}
	}

	@RequestMapping("/subject/add/success")
	public ModelAndView subjectAddSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("subjectaddsuccess");
		return model;
	}

	@GetMapping("/score/add")
	public ModelAndView scoreAdd(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		List<Subject>subject =subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		mav.addObject("subject",subject);
		mav.addObject("class",cla);
		mav.setViewName("scoreadd");
		return mav;
	}

	@PostMapping("/score/add")
	public String scoreAddRun(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		return "redirect:/score/add/success";
	}

	@PostMapping("/score/add/result")
	public ModelAndView scoreAddResult(ModelAndView mav,@ModelAttribute Score sco,@ModelAttribute Student stu, @ModelAttribute Subject sub,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		List<Subject>subject =subjectService.getbySchool_cd(teacher.getSchool_cd());
		List<ClassNum>cla = classNumService.getbySchool_cd(teacher.getSchool_cd());
		System.out.println("テスト1");
		Subject choicesub = subjectService.getByCd(sub.getCd());
		System.out.println(choicesub);
		System.out.println("テスト2");
		mav.addObject("sub",choicesub);
		
		List<Score>score = scoreService.search(stu.getEnt_year(), sco.getClass_num(), sco.getSubject_cd(), sco.getNo());
		System.out.println("テスト3");
		mav.addObject("score",score);

		System.out.println(score);

		mav.addObject("subject",subject);
		mav.addObject("class",cla);
		mav.setViewName("scoreaddresult");
		return mav;
	}

	@RequestMapping("/score/add/success")
	public ModelAndView scoreAddSuccess(ModelAndView mav,@AuthenticationPrincipal Teacher teacher) {
		mav.addObject("username",teacher);
		mav.setViewName("scoreaddsuccess");
		return mav;
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
		return "redirect:/student/edit/success";
	}

	@RequestMapping("/student/edit/success")
	public ModelAndView studentEditSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("studenteditsuccess");
		return model;
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
		return "redirect:/subject/edit/success";
	}

	@RequestMapping("/subject/edit/success")
	public ModelAndView subjectEditSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("subjecteditsuccess");
		return model;
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
		return "redirect:/student/delete/success";
	}

	@RequestMapping("/student/delete/success")
	public ModelAndView studentDeleteSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("studentdeletesuccess");
		return model;
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
		return "redirect:/subject/delete/success";
	}

	@RequestMapping("/subject/delete/success")
	public ModelAndView subjectDeleteSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("subjectdeletesuccess");
		return model;
	}
}
