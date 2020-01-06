package com.bjbz.test.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import com.yaj.common.generate.GenerateService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseTestService.class)
public class BaseTestService {

	@Resource
	//private GenerateService gs;
	
	@Test
	public void get() throws Exception {
        //gs.test2();
    }
}
