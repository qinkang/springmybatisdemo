package com.qingofun.controller;

import com.qingofun.pojo.User;
import com.qingofun.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;

	@RequestMapping("/show")
	public String toShow() {
		return "showUser";
	}

	@RequestMapping("/index")
	public String toIndex() {
		return "showUser";
	}
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/showUser/{id}")
	public String toIndex1(@PathVariable(value = "id") String id,Model model){
		int userId = Integer.parseInt(id);
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/showAllUsers/{id}")
	@ResponseBody
	public User showUserById(@PathVariable(value = "id") Integer id) {
		User user = this.userService.getUserById(id);
		return user;
	}

	@RequestMapping("/showAllUsers")
	@ResponseBody
	public List<User> showAllUsers() {
		List<User> users = this.userService.getAllUsers();
		return users;
	}

	@RequestMapping("/showAllUsers1")
	@ResponseBody
	public List<Map<String, Object>> showAllUsers1() {
		List<Map<String, Object>> users = this.userService.getAllUsers1();
		return users;
	}

	@RequestMapping("/showAllUsers2")
	@ResponseBody
	public List<Map<String, Object>> showAllUsers2() {
		List<Map<String, Object>> users = this.userService.getAllUsers2();
		return users;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/showUsersByCre")
	@ResponseBody
	public List<Map<String, Object>> showAllUsersBySearch(@Param("user") User user) {
		List<Map<String, Object>> users = this.userService.getUsersByCre(user);
		return users;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/showUsersByCre1")
	@ResponseBody
	public List<User> showAllUsersBySearch1(@Param("user") User user) {
		List<User> users = this.userService.getUsersByCre1(user);
		return users;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/testRequestParam1")
	@ResponseBody
	public String testRequestParam1(@RequestParam(value="age") Integer age,
									String userName,
									@RequestParam(value="password", required = true, defaultValue = "nierya") String password) {

		System.out.println("age:" + age + "    userName:" + userName + "    password:" +password);
		return "success";
	}

	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest req, ModelMap modelMap) throws Exception{
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
		MultipartFile file = mreq.getFile("file");
		String fileName = file.getOriginalFilename();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
				"upload/"+/*sdf.format(new Date())+*/fileName);
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		User user = new User();
		user.setUserName("qink");
		modelMap.addAttribute("uploadFlag", "success");
		modelMap.addAttribute("user", user);

		return "成功";
	}



}
