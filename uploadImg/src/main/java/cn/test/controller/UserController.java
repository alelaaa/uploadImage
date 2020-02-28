package cn.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.test.entity.User;
import cn.test.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/index")
	public String index(ModelMap map) {
		List<User> userList = userService.selectAll();
		map.addAttribute("userList",userList);
		return "showAll";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,MultipartFile file,HttpServletRequest request) throws IOException {
		System.out.println("提交的用户："+user);
		String filePath = "E:\\upload"; //定义图片上传后的路径
		String alies = "upload";//tomcat设置的别名
		String newFileName = "/" + alies + "/" + fileOperate(file,filePath);
		user.setUserImg(newFileName);
		System.out.println("最后的user:"+user);
		userService.insertUser(user);
		return "redirect:/index";
	}
	
	
	/**
	 * 跳转到修改用户信息页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/toUpdateUser")
	public String toUpdateUserPage(Integer id,ModelMap map) {
		System.out.println("id:"+id);
		User user = userService.selectById(id);
		System.out.println("修改后获取的user："+user);
		map.addAttribute("user",user);
		return "updateUser";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,MultipartFile file,HttpServletRequest request) throws Exception{
		System.out.println("修改提交的用户："+user);
		String filePath = request.getSession().getServletContext().getRealPath("/upload");; //定义图片上传后的路径
		String newFileName = fileOperate(file,filePath);
		user.setUserImg(newFileName);
		System.out.println("修改最后的user:"+user);
		userService.updateUser(user);
		return "redirect:/index";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id) {
		System.out.println("id"+id);
		userService.deleteUser(id);
		return "redirect:/index";
	}
	
	/**
	 * 封装操作文件方法， 添加用户 和修改用户都会用到
	 * @param file
	 * @param filePath
	 * @return
	 */
	private String fileOperate(MultipartFile file,String filePath) {
		String originalFileName = file.getOriginalFilename();//获取原始图片的扩展名
		System.out.println("图片原始名称："+originalFileName);
		String newFileName = UUID.randomUUID()+originalFileName;  //新的文件名称
		System.out.println("新的文件名称："+newFileName);
		File targetFile = new File(filePath,newFileName); //创建新文件
		try {
			file.transferTo(targetFile); //把本地文件上传到文件位置 , transferTo()是springmvc封装的方法，用于图片上传时，把内存中图片写入磁盘
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return newFileName;
	}
	
	
	
}
