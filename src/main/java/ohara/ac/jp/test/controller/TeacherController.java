package ohara.ac.jp.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ohara.ac.jp.test.model.School;
import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.service.SchoolService;
import ohara.ac.jp.test.service.TeacherService;
import ohara.ac.jp.test.service.TeacherhashService;
@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherhashService teacherhashService;
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/loginSuccess")
	public String test(Model model) {
		return "loginSuccess";
	}

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("teacherModel",new Teacher());
		System.out.println("ログインページ１");
		return "login";
	}
	
	@PostMapping("/login")
    public String login(String teacherId,String password, HttpSession session) {  //ここあやしい
		System.out.println("ログインページ２"+teacherId);
	   	 if (teacherService.authenticate(teacherId, password)) {
	         // 認証に成功した場合、セッションを開始してログイン状態にする
	         session.setAttribute("teacherId", teacherId);
	         return "redirect:loginSuccess" ; // ログイン後のリダイレクト先を指定
	     } else {
	         // 認証に失敗した場合はログインフォームに戻す
	    	 System.out.println("認証失敗" + teacherId);
	    	 System.out.println("認証失敗1");
	         return "redirect:/login?error";
	     }
	} 

	@GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("teacherModel", new Teacher());
        List<School>list = schoolService.searchAll();
        model.addAttribute("school",list);
        return "signup";
    }
 
    @PostMapping("/signup")
    public String signup(@ModelAttribute("teacherModel") @Validated Teacher teacher, BindingResult result) {
        // サインアップ処理を実行
    	if (result.hasErrors()) {
            return "signup";
        }
        
        // 登録処理を行う
        teacherhashService.hashTeacher(teacher);
        return "redirect:/signup/success"; // ログインページにリダイレクト
    }
    
	@RequestMapping("/signup/success")
	public ModelAndView signupSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("signupsuccess");
		return model;
	}
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // セキュリティコンテキストから現在の認証情報を取得し、ログアウト処理を実行
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/logout/success"; // ログアウト後のリダイレクト先を指定
    }
    
	@RequestMapping("/logout/success")
	public ModelAndView logoutSuccess(ModelAndView model,@AuthenticationPrincipal Teacher teacher) {
		model.addObject("username",teacher);
		model.setViewName("logoutsuccess");
		return model;
	}
	
}
