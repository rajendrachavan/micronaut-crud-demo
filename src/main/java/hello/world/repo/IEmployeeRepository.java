package hello.world.repo;

import hello.world.model.Employee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
