package com.jisoo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.MyPageDto;
import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.security.SecurityUser;
import com.jisoo.board.service.BoardService;
import com.jisoo.board.service.UserService;

@Controller
public class MainController {

	private final UserService userService;
	private final BoardService boardService;
	
	public MainController(UserService userService, BoardService boardService) {
		this.userService = userService;
		this.boardService = boardService;
	}
	
	
    @GetMapping("/")
    String home(Model model) {
        return "views/home";
    }

    @GetMapping("/login")
    String goLogin() {
        return "views/login";
    }
    
    @GetMapping("/signup")
    String goSignup() {
    	return "views/signup";
    }
    
    @GetMapping("/signup/check-id")
    @ResponseBody
    public Map<String, Boolean> checkIdAvailabile(@RequestParam String id){
    	boolean available = userService.isLoginIdAvailable(id);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("available", available);
    	
    	return result;
    }
    
    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Boolean> signup(UserSignupDto dto
    ) {
        boolean success = userService.signup(dto);

//        System.out.println("result : " + success);
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
    
    @GetMapping("/auth/check-id")
    @ResponseBody
    public Map<String, Boolean> checkIdExists(@RequestParam String id) {
		boolean isChecked = userService.existsLoginId(id);
//		System.out.println("isChecked: " + isChecked);
		Map<String, Boolean> result = new HashMap<>();
		result.put("isChecked", isChecked);
    	
    	return result;
    }
    
    @GetMapping("/mypage/verify")
    public String verifyPage() {
        return "views/mypageVerify";
    }
    
    @PostMapping("/mypage/verify")
    public String mypageVerify(@RequestParam String password, @AuthenticationPrincipal SecurityUser user, Model model) {
//    	System.out.println("userName: " + user.getUserId());
//    	System.out.println("typed pw: " +  pw);
    	boolean isVerified = userService.checkPassword(user.getUserId(), password);
    	if(isVerified) {
    		UserVo userVo = userService.findByUserId(user.getUserId());
    		MyPageDto mypageDto = new MyPageDto();
    		mypageDto.setLoginId(userVo.getLoginId());
    		mypageDto.setNickname(userVo.getNickname());
    		model.addAttribute("user", mypageDto);
    		return "views/mypage";
    	}else {
    		return "views/mypageVerify";
    	}
    	
//    	System.out.println("result: " + isOk);
    }
    
    @PostMapping("/mypage/edit")
    @ResponseBody
    public Map<String, Boolean> updateUserInfo(@RequestParam String nickname, @AuthenticationPrincipal SecurityUser user) {
    	boolean update = userService.updateUserInfo(user.getUserId(), nickname);
    	Map<String, Boolean> result = new HashMap<>();
    	result.put("update", update); 
    	return result;
    }
    
    @PostMapping("/mypage/password")
    @ResponseBody
    public Map<String, Boolean> updatePassword(@RequestParam String password, @AuthenticationPrincipal SecurityUser user) {
//    	System.out.println("updatePassword");
    	Map<String, Boolean> result = new HashMap<>();
    	if (password == null) {
    		result.put("update", false);
    	}
    	boolean update = userService.updatePassword(user.getUserId(), password);
    	result.put("update", update);
//    	System.out.println("result: " + result);
    	return result;
    }
    
    @GetMapping("/board")
    public String boards(Model model) {
    	List<BoardVo> list = boardService.getAllBoards();
    	model.addAttribute("list", list);
        return "views/boardList";
    }
    
    
}
