package com.sreeraj.PostgresTest.controller;


import com.sreeraj.PostgresTest.dto.EmployeeDto;
import com.sreeraj.PostgresTest.exceptions.EmployeeIdNotFoundException;
import com.sreeraj.PostgresTest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createEmployee(@RequestParam(name = "first-name", defaultValue = "") String firstName, @RequestParam(name = "last-name", defaultValue = "") String lastName, @RequestParam(defaultValue = "") Integer age) {
        return employeeService.createEmployee(new EmployeeDto(firstName, lastName, age));
    }


//    @RequestMapping(value = "/create-test", method = RequestMethod.POST)
//    public String createEmployee(@RequestBody EmployeeDto employeeDto) {
//        return employeeService.createEmployee(employeeDto);
//    }



    @RequestMapping(value = "/get-json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public EmployeeDto getEmployeeAsJson(@RequestParam Long id) {
        try {
            return employeeService.getEmployeeAsEmployeeDto(id);
        } catch (EmployeeIdNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/get-xml", produces = {MediaType.APPLICATION_XHTML_XML_VALUE}, method = RequestMethod.GET)
    public EmployeeDto getEmployeeAsXml(@RequestParam Long id) {
        try {
            return employeeService.getEmployeeAsEmployeeDto(id);
        } catch (EmployeeIdNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getEmployee(@RequestParam Long id) {
        try {
            return employeeService.getEmployee(id);
        } catch (EmployeeIdNotFoundException e) {
            e.printStackTrace();
            return "Employee id " + id + " does not exist";
        }
    }

    @RequestMapping(value = "/get-by-firstname", method = RequestMethod.GET)
    public String getEmployeesByFirstName(@RequestParam(name = "first-name") String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @RequestMapping(value = "/get-by-lastname", method = RequestMethod.GET)
    public String getEmployeesByLastName(@RequestParam(name = "last-name") String lastName) {
        return employeeService.getEmployeesByLastName(lastName);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateEmployeeById(@RequestParam Long id, @RequestParam(name = "first-name", required = false) String firstName, @RequestParam(name = "last-name", required = false) String lastName, @RequestParam(required = false) Integer age) {

        EmployeeDto employeeDto = new EmployeeDto(firstName, lastName, age);
        try {
            return employeeService.updateEmployeeById(id, employeeDto);
        } catch (EmployeeIdNotFoundException e) {
            e.printStackTrace();
            return "Employee id " + id + " does not exist";
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestParam Long id) {
        try {
            return employeeService.deleteEmployee(id);
        } catch (EmployeeIdNotFoundException e) {
            e.printStackTrace();
            return "Employee id " + id + " does not exist";
        }
    }
}
