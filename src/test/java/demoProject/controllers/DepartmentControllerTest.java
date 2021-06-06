package demoProject.controllers;

import demoProject.DemoProjectTests;
import demoProject.models.Department;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class DepartmentControllerTest extends DemoProjectTests {

    private Department department;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://localhost:5001/";
    }

    @BeforeEach
    public void setup() {
        department = new Department("A", new ArrayList<>());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(department)
                .when()
                    .post("/departments");
    }

    @Test
    @DisplayName("test get department by id")
    public void testGetDepartmentById() {
        Department actualDepartment = RestAssured
                .given()
                    .pathParam("id", 1L)
                .when()
                    .get("/departments/{id}")
                    .as(new TypeRef<Department>() {});
        Assertions.assertEquals(department.getDepartmentName(), actualDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("test get departments")
    public void testGetDepartments() {
        List<Department> departments = RestAssured
                .when()
                    .get("/departments")
                    .as(new TypeRef<List<Department>>() {});
        Assertions.assertFalse(departments.isEmpty());
    }

    @Test
    @DisplayName("test get department by name")
    public void testGetDepartmentByName() {
        List<Department> departments = RestAssured
                .given()
                    .pathParam("name", "A")
                .when()
                    .get("/departments/name/{name}")
                    .as(new TypeRef<List<Department>>() {});
        Assertions.assertFalse(departments.isEmpty());
    }

    @Test
    @DisplayName("test get department by min no of employees")
    public void testGetDepartmentsByMinNoOfEmployees() {
        List<Department> departments = RestAssured
                .given()
                    .pathParam("count", 0)
                .when()
                    .get("/departments/count/{count}")
                    .as(new TypeRef<List<Department>>() {});
        Assertions.assertFalse(departments.isEmpty());
    }

    @Test
    @DisplayName("test add department")
    public void testAddDepartment() {
        Department newDepartment = new Department("B", new ArrayList<>());
        Department actualDepartment = RestAssured
                .given()
                    .contentType("application/json")
                    .body(newDepartment)
                .when()
                    .post("/departments")
                    .as(new TypeRef<Department>() {});
        Assertions.assertEquals("B", actualDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("test delete department")
    public void deleteDepartment() {
        RestAssured
                .given()
                    .pathParam("id", 2L)
                .when()
                    .delete("/departments/{id}")
                .then()
                    .statusCode(200);
    }

    @Test
    @DisplayName("test update department")
    public void testUpdateDepartment() {
        Department newDepartment = new Department(3L, "C", new ArrayList<>());
        Department updatedDepartment = RestAssured
                .given()
                    .contentType("application/json")
                    .body(newDepartment)
                .when()
                    .put("/departments")
                    .as(new TypeRef<Department>() {});
        Assertions.assertEquals(newDepartment.getDepartmentName(), updatedDepartment.getDepartmentName());
    }

}
