package com.sreeraj.PostgresTest.model;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

@Entity
@Table(name="employee")
public class Employee {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;
    @Id
    @SequenceGenerator(name = "mySequence", allocationSize = 1,
            sequenceName = "mySequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequence")
    private Long id;

    public Employee(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Employee(){

    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, age, id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
