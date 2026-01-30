package com.jisoo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jisoo.board.domain.BoardVo;
import com.jisoo.board.domain.CommentVo;
import com.jisoo.board.domain.MyPageDto;
import com.jisoo.board.domain.PageDto;
import com.jisoo.board.domain.ReportVo;
import com.jisoo.board.domain.UserSignupDto;
import com.jisoo.board.domain.UserVo;
import com.jisoo.board.security.SecurityUser;
import com.jisoo.board.service.AdminService;
import com.jisoo.board.service.BoardService;
import com.jisoo.board.service.CommentService;
import com.jisoo.board.service.ReportService;
import com.jisoo.board.service.UserService;


@Controller
public class MainController {

	private final UserService userService;
	private final BoardService boardService;
	private final CommentService commentService;
	private final ReportService reportService;
	private final AdminService adminService;
	
	public MainController(UserService userService, BoardService boardService, CommentService commentService, ReportService reportService, AdminService adminService) {
		this.userService = userService;
		this.boardService = boardService;
		this.commentService = commentService;
		this.reportService = reportService;
		this.adminService = adminService;
	}
	
	private Map<String, Integer> addStats(Model model) {
	    Map<String, Integer> stats = new HashMap<>();
	    stats.put("todayUsers", userService.countTodayUser());
	    stats.put("todayBoards", boardService.countTodayBoard());
	    stats.put("pendingReports", reportService.countPendingReport());
	    return stats;
	}
	
    @GetMapping("/")
    String home(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
    	List<BoardVo> boardList = boardService.getTopBoardsByViews(3);
    	List<BoardVo> noticeList = boardService.getRecentNotice();
    	model.addAttribute("user", securityUser);
    	model.addAttribute("boardList", boardList);
    	model.addAttribute("noticeList", noticeList);
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
    public String boards(@ModelAttribute PageDto pageDto, Model model) {
    	System.out.println("pageDto :" + pageDto);
    	int pages = boardService.getPageCount(pageDto);
    	pageDto.calcPage(pages);
    	System.out.println("pageDto :" + pageDto);

    	List<BoardVo> list = boardService.getBoardsByKeword(pageDto);
    	
    	model.addAttribute("pageDto", pageDto);
    	model.addAttribute("list", list);
    	
        return "views/boardList";
    }
    
    // 수정이나 상세보기에서도 같은 폼을 이용하기 위해(th:field) 빈 BoardVo를 전달 
    @GetMapping("/board/write")
    public String boardWrite(Model model) {
    	model.addAttribute("board", new BoardVo());
        return "views/boardWrite";
    }
    
    @PostMapping("/board/write")
    public String write(@ModelAttribute BoardVo boardVo, @AuthenticationPrincipal SecurityUser user, Model model) {
//    	System.out.println("isNotice: " + boardVo.getIsNotice());
    	boolean isNotice = "Y".equals(String.valueOf(boardVo.getIsNotice()));
    	boolean isAdmin = user.getAuthorities().stream()
    	        .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
    	
    	boardVo.setWriterId(user.getUserId());
    	boardVo.setWriterName(user.getUsername());
    	
//    	System.out.println("isNotice: " + isNotice);
//    	System.out.println("isAdmin: " + isAdmin);
//    	System.out.println("boardVo: " + boardVo);
    	
    	if(isAdmin && isNotice) {
    		adminService.writeNotice(boardVo);
    	}
    	else {
    		boardService.registerBoard(boardVo);
    	}
        return "redirect:/board";
    }
    
    @GetMapping("/board/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId,
    		@AuthenticationPrincipal SecurityUser securityUser, Model model) {
    	BoardVo boardVo = boardService.getBoard(boardId);
    	boardService.increaseViewCount(boardId);
    	boolean isOwner = (securityUser != null) && boardService.isOwner(boardId, securityUser.getUserId());
    	
    	List<CommentVo> comment = commentService.getComments(boardId);
    	
    	model.addAttribute("loginId", securityUser != null ? securityUser.getUserId() : null);
    	model.addAttribute("board", boardVo);
    	model.addAttribute("isOwner", isOwner);
    	model.addAttribute("comment", comment);
    	
    	return "views/boardDetail";
    }
    
    @GetMapping("/board/edit/{boardId}")
    public String boardEdit(@PathVariable("boardId") Long boardId,
    		@AuthenticationPrincipal SecurityUser securityUser, Model model) {
    	
    	if(securityUser == null || !boardService.isOwner(boardId, securityUser.getUserId())) {
            return "redirect:/board/" + boardId;
        }

        BoardVo board = boardService.getBoard(boardId);

        model.addAttribute("board", board);
        model.addAttribute("mode", "edit");

        return "views/boardWrite";
    }
 
    @PostMapping("/board/update/{boardId}")
    public String updateBoard(@PathVariable("boardId") Long boardId,
    		@ModelAttribute BoardVo boardVo,
    		@AuthenticationPrincipal SecurityUser securityUser,
    		Model model) {
//    	System.out.println("updateBoard: /board/update/" + boardId);
//    	System.out.println("updateBoard: boardVo" + boardVo);
//    	System.out.println("isOwner: " + boardService.isOwner(boardId, securityUser.getUserId()));
    	
    	if(securityUser == null || !boardService.isOwner(boardId, securityUser.getUserId())) {
    		return "redirect:/board";
    	}
    	boardVo.setBoardId(boardId);
    	boardService.updateBoard(boardVo);
    	
    	return "redirect:/board/" + boardId;
    }
    
    
    @PostMapping("/board/delete/{boardId}")
    public String deleteBoard(@PathVariable("boardId") Long boardId,
    		@AuthenticationPrincipal SecurityUser securityUser) {
    	if(securityUser == null || !boardService.isOwner(boardId, securityUser.getUserId())) {
    		return "redirect:/board";
    	}
    	
    	boardService.deleteBoard(boardId);
    	
		return "redirect:/board";
    }
    
    @PostMapping("/board/{boardId}/like")
    @ResponseBody
    public int like(@PathVariable("boardId") Long boardId,
                    @AuthenticationPrincipal SecurityUser user) {
//    	System.out.println("controller - like");
        return boardService.toggleLike(boardId, user.getUserId());
    }
    
    @PostMapping("/board/{boardId}/comment")
    public String writeComment(@PathVariable("boardId") Long boardId,
    		@AuthenticationPrincipal SecurityUser securityUser,
    		@ModelAttribute CommentVo commentVo) {
    	commentVo.setWriterId(securityUser.getUserId());
//    	System.out.println("ctr - commentVo: " + commentVo);
    	
    	commentService.insertComment(commentVo);
    	return "redirect:/board/" + boardId;
    }
    
    @PostMapping("/board/{boardId}/comment/{commentId}/update")
    @ResponseBody
    public Map<String, Boolean> updateComment(@PathVariable Long boardId,
    		@PathVariable Long commentId,
            @AuthenticationPrincipal SecurityUser securityUser,
            @ModelAttribute CommentVo commentVo) {
    	
    	boolean update = false;
    	
    	if(securityUser != null && commentService.isOwner(commentId, securityUser.getUserId())) {
    		update = commentService.updateComment(commentVo);
    	}
    	Map<String, Boolean> result = new HashMap<>();
    	result.put("update", update);
        return result;
    }
    
    @PostMapping("/board/{boardId}/comment/{commentId}/delete")
    public String postMethodName(@PathVariable Long boardId,
    		@PathVariable Long commentId,
    		@AuthenticationPrincipal SecurityUser securityUser) {

    	if(securityUser != null && commentService.isOwner(commentId, securityUser.getUserId())) {
    		commentService.delteComment(commentId);
    	}
    	
        return "redirect:/board/" + boardId;
    }
    
    @PostMapping("/board/{boardId}/report")
    public String reportBoard(@PathVariable Long boardId, @ModelAttribute ReportVo reportVo, @AuthenticationPrincipal SecurityUser securityUser) {
    	reportVo.setReporterId(securityUser.getUserId());
    	System.out.println(reportVo);
    	reportService.reportBoard(reportVo);
    	
    	
    	return "redirect:/board/" + boardId;
    }
    
    @PostMapping("/comment/{commentId}/report")
    public String reportComment(@PathVariable Long commentId, @ModelAttribute ReportVo reportVo,
    		@AuthenticationPrincipal SecurityUser securityUser,
    		HttpServletRequest request) {
    	reportVo.setReporterId(securityUser.getUserId());
    	System.out.println(reportVo);
    	reportService.reportBoard(reportVo);
    	
    	return "redirect:" + request.getHeader("Referer");
    }
    
    @GetMapping("/admin")
    public String admin(Model model) {
        Map<String, Integer> stats = addStats(model);
        model.addAttribute("stats", stats);
        return "/views/admin";
    }
    
    @GetMapping("/admin/users")
    public String users(
        @RequestParam(required = false) String keyword, @RequestParam(required = false) String role,
        @RequestParam(defaultValue = "1") int page, Model model) {

    	Map<String, Integer> stats = addStats(model);
        List<UserVo> users = adminService.searchUsers(keyword, role, page);

        model.addAttribute("stats", stats);
        model.addAttribute("users", users);
        model.addAttribute("page", page);

        return "views/admin";
    }
    
    @PostMapping("/admin/users/{userId}/role")
    public String promoteToManager(@PathVariable Long userId) {
        adminService.changeRole(userId, "ROLE_ADMIN");
        return "redirect:/admin#users";
    }
    
    @PostMapping("/admin/users/{userId}/suspend")
    public String suspendUser(@PathVariable Long userId, @RequestParam int days) {
        adminService.suspendUser(userId, days);
        return "redirect:/admin#users";
    }
    
}
