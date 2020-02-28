package cn.test.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.test.entity.User;
import cn.test.mapper.UserMapper;
import cn.test.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	public User selectById(Integer id) {
		return userMapper.selectById(id);
	}

	public void deleteUser(Integer id) {
		userMapper.deleteUser(id);
	}

}
