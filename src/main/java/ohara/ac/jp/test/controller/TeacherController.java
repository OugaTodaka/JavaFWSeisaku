package ohara.ac.jp.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import ohara.ac.jp.test.model.Teacher;
import ohara.ac.jp.test.service.TeacherService;
import ohara.ac.jp.test.service.TeacherhashService;
@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherhashService teacherhashService;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("teacherModel",new Teacher());
		return "login";
	}
	
	@PostMapping("/login")
    public String login(String teacher_id,String password, HttpSession session) {
	   	 if (teacherService.authenticate(teacher_id, password)) {
	         // 認証に成功した場合、セッションを開始してログイン状態にする
	         session.setAttribute("teacher_id", teacher_id);
	         return "redirect:top" ; // ログイン後のリダイレクト先を指定
	     } else {
	         // 認証に失敗した場合はログインフォームに戻す
	    	 System.out.println("認証失敗" + teacher_id);
	         return "redirect:/login?error";
	     }
	}
	@GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("teacherModel", new Teacher());
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
        return "redirect:/login"; // ログインページにリダイレクト
    }
	
}
