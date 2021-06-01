package demoProject;

import demoProject.controllers.DepartmentController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoProjectApplicationTests {

	private DepartmentController departmentController;

	@Autowired
	public DemoProjectApplicationTests(DepartmentController departmentController) {
		this.departmentController = departmentController;
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(departmentController, () -> "Class Load Failure");
	}

}
