package native_jdbc_programing.dao;

import java.util.List;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByNo(Department department);
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
	
	List<Employee> selectDepartmentByDeptno(Department dept);
	

}
