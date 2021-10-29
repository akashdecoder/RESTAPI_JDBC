package org.example;


import org.example.dao.Employee;
import org.example.dao.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList = EmployeeService.getAllEmployees();

        return employeeList;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) {

        int status = EmployeeService.addNewEmployee(employee);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {

        int status = EmployeeService.updateExistingEmployee(id, employee);

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") int id) {

        int status = EmployeeService.deleteEmployee(id);

        return Response.ok().build();
    }

}
