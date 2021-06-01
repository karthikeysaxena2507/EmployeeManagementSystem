package demoProject.departmentTests;

import demoProject.controllers.DepartmentController;
import demoProject.services.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

// FOR MANUAL CONFIGURATION OF MOCK MVC
//@SpringBootTest
//@ContextConfiguration
//@ExtendWith(MockitoExtension.class)

@Import(DepartmentController.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DepartmentController.class)
@ComponentScan(basePackages = "demoProject")
public class DepartmentControllerTest {

    @MockBean
    DepartmentService departmentService;

    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    /**
     * // FOR MANUAL CONFIGURATION OF MOCK MVC, REMOVE AUTOWIRING OF MOCKMVC INSTANCE
     */
//    @BeforeEach
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .build();
//    }

    @Test
    @DisplayName("Test Get All Departments API")
    public void testGetAllDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test Get Departments By Id")
    public void testGetDepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
