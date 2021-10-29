package org.example.dao;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rest",
                    "root",
                    "Github@2020"
            );

            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from employee;");

            while (res.next()) {

                employees.add(new Employee(res.getInt(1), res.getString(2), res.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static int addNewEmployee(Employee employee) {

        int status = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rest",
                    "root",
                    "Github@2020"
            );

            employee.setId(employee.getId());
            employee.setName(employee.getName());
            employee.setAge(employee.getAge());


            PreparedStatement preparedStatement = conn.prepareStatement(
                    "insert into employee values(?, ?, ?);"
            );

            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());

            status = preparedStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int updateExistingEmployee(int id, Employee employee) {

        int status = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rest",
                    "root",
                    "Github@2020"
            );

            employee.setId(employee.getId());
            employee.setName(employee.getName());
            employee.setAge(employee.getAge());


            PreparedStatement preparedStatement = conn.prepareStatement(
                    "update employee set name=?, age=? where id=?;"
            );


            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setInt(3, id);

            status = preparedStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int deleteEmployee(int id) {

        int status = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rest",
                    "root",
                    "Github@2020"
            );

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "delete from employee where id=?;"
            );

            preparedStatement.setInt(1, id);

            status = preparedStatement.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
