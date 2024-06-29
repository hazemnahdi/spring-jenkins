package com.alpha.pointage.repository;

import com.alpha.pointage.dto.EmployeeDTO;
import com.alpha.pointage.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    long count();

    @Query("SELECT AVG(e.salaire) FROM EmployeeEntity e")
    Double calculateAverageSalary();


    @Query("SELECT MIN (e.salaire) FROM EmployeeEntity e")
    Double calculateMinSalary();

    List<EmployeeEntity> findByName(String name);

    @Query("SELECT e.id as id, e.name as name, e.salaire as salaire, " +
            "p as primes, COALESCE(SUM(p.montant), 0) as totalPrimes " +
            "FROM EmployeeEntity e LEFT JOIN e.primes p GROUP BY e.id")
    List<EmployeeDTO> findAllEmployeesWithPrimes();

}
