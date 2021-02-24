package native_jdbc_programing.dao;

import java.util.List;

import native_jdbc_programing.dto.Employee;

public interface EmployeeDao {
	
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee employee);
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);

	List<Employee> selectJoinEmployeeByAllSimple(String sql);
	

}
