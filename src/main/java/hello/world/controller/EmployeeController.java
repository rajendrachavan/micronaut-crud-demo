package hello.world.controller;

import hello.world.model.Employee;
import hello.world.service.IEmployeeService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@Controller("/employee")
public class EmployeeController {

    @Inject
    private IEmployeeService employeeService;

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Employee getEmployee(Long id) {
        return employeeService.getEmployee(id);
    }

    @Post(value = "/")
    public HttpResponse<Employee> saveEmployee(@Body Employee employee) {

        employeeService.saveEmployee(employee);

        return HttpResponse
                .created(employee)
                .headers(header -> header.location(toUri(employee)));

    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public List<Employee> list() {
        return employeeService.getAllEmployees();
    }

    @Put("/")
    public HttpResponse<?> update(@Body Employee employee) {
        Employee employee1 = employeeService.updateEmployee(employee.getId(), employee.getName());
        return HttpResponse
                .ok(employee1)
                .header(HttpHeaders.LOCATION, toUri(employee).getPath());
    }

    @Delete("/{id}")
    public HttpResponse<?> delete( Long id) {
        employeeService.deleteEmployeeById(id);
        return HttpResponse.noContent();
    }

    private URI toUri(Employee employee) {

        return URI.create("/employee/" + employee.getId());
    }

}
