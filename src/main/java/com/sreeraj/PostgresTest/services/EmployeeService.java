package com.sreeraj.PostgresTest.services;

import com.sreeraj.PostgresTest.dto.EmployeeDto;
import com.sreeraj.PostgresTest.exceptions.EmployeeIdNotFoundException;
import com.sreeraj.PostgresTest.model.Employee;
import com.sreeraj.PostgresTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public String createEmployee(EmployeeDto emp){
        Employee employee = new Employee();
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setAge(emp.getAge());
        return employeeRepository.save(employee).toString();
    }

    @Transactional
    public String getEmployee(Long id) throws EmployeeIdNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent())
            throw new EmployeeIdNotFoundException("id does not exist");
        return employee.get().toString();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String deleteEmployee(Long id) throws EmployeeIdNotFoundException{
        if(!employeeRepository.findById(id).isPresent())
            throw new EmployeeIdNotFoundException("id does not exist");
        employeeRepository.deleteById(id);
        return "Deleted " + id;
    }

    @Transactional
    public String getEmployeesByFirstName(String firstName) {
        List<Employee> employees = employeeRepository.findByFirstName(firstName);
        if(employees.isEmpty())
            return "No Such Employees exist";
        else
            return employees.toString();
    }

    @Transactional
    public String getEmployeesByLastName(String lastName) {
        List<Employee> employees = employeeRepository.findByLastName(lastName);
        if(employees.isEmpty())
            return "No Such Employees exist";
        else
            return employees.toString();
    }

    @Transactional
    public String updateEmployeeById(Long id, EmployeeDto employeeDto) throws EmployeeIdNotFoundException{
        if(!employeeRepository.findById(id).isPresent())
            throw new EmployeeIdNotFoundException("id does not exist");

        Employee employee = employeeRepository.findById(id).get();
        if(null != (employeeDto.getFirstName()))
            employee.setFirstName(employeeDto.getFirstName());
        if(null != (employeeDto.getLastName()))
            employee.setLastName(employeeDto.getLastName());
        if(null != (employeeDto.getAge()))
            employee.setAge(employeeDto.getAge());

        return employeeRepository.save(employee).toString();
    }

    @Transactional
    public EmployeeDto getEmployeeAsEmployeeDto(Long id) throws EmployeeIdNotFoundException{
        Optional<Employee> emp = employeeRepository.findById(id);
        if(!emp.isPresent())
            throw new EmployeeIdNotFoundException("id does not exist");
        Employee employee = emp.get();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getFirstName());
        employeeDto.setAge(employee.getAge());
        return employeeDto;
    }
}
