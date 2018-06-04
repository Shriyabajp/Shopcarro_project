package com.sreeraj.PostgresTest.repository;

import com.sreeraj.PostgresTest.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//    @Query("update employee set employee.name = :name where employee.id=:id")
//    void updateName(@Param("name") String name, @Param("id") Long id );
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String LastName);
}
