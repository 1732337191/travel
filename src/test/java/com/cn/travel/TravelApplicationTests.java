package com.cn.travel;

import com.cn.travel.role.user.service.imp.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.travel.role.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelApplicationTests {

	@Autowired
    UserService userService;
	@Test
	public void contextLoads() {
		PageHelper.startPage(2,1);
		try {
			List<User> list = userService.findList();
			PageInfo<User> pageInfo=new PageInfo<User>(list);
			System.out.print(pageInfo);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Test
	public void getDirectPath1() throws IOException {
//		System.out.println(System.getProperty("user.dir"));
//		File upload = new File(path.getAbsolutePath(),"static/images/upload/")
		Resource resource = new ClassPathResource("/static/images");
		System.out.println(resource.getFile().getAbsolutePath());
	}
	@Test
	public void getDirectPath2() throws IOException {
//		String path1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();//当前文件编译后路径
		String path2 = ResourceUtils.getURL("classpath:").getPath();//获取编译后的：target/test-classes/
		String path3 = ResourceUtils.getURL("").getPath();//获取工程路径
//		File upload = new File(path2.getAbsolutePath(),"static/images/");
		System.out.println(path2);

	}

}
