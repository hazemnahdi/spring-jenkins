package com.alpha.pointage.service;

import com.alpha.pointage.dto.EmployeeDTO;
import com.alpha.pointage.dto.PrimeDTO;
import com.alpha.pointage.entity.EmployeeEntity;
import com.alpha.pointage.entity.PrimeEntity;
import com.alpha.pointage.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        // Ajoutez des validations ou des logiques métier si nécessaire
        return employeeRepository.save(employee);
    }
    public long getTotalEmployees() {
        return employeeRepository.count();
    }
    public Double calculateAverageSalary() {
        return employeeRepository.calculateAverageSalary();
    }
    public Double calculateMinSalary() {
        return employeeRepository.calculateMinSalary();
    }
    public List<EmployeeEntity> getFirstFiveEmployees() {
        return employeeRepository.findAll().subList(0, 5);
    }
    public List<EmployeeEntity> getLastFiveEmployees() {
        // Créer un objet PageRequest pour récupérer les cinq derniers enregistrements triés par ordre décroissant d'ID
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));

        // Récupérer les cinq derniers enregistrements
        return employeeRepository.findAll(pageRequest).getContent();
    }
    public List<EmployeeEntity> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }
    private EmployeeDTO toDTO(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalaire(employee.getSalaire());
        dto.setPrimes(toPrimeDTOList(employee.getPrimes())); // Assurez-vous que le nom du champ est correct
        return dto;
    }

    private PrimeDTO toPrimeDTO(PrimeEntity prime) {
        if (prime == null) {
            return null;
        }

        PrimeDTO dto = new PrimeDTO();
        dto.setId(prime.getId());
        dto.setLibelle(prime.getLibelle());
        dto.setMontant(prime.getMontant());
        dto.setDate(prime.getDate());

        return dto;
    }

    private List<PrimeDTO> toPrimeDTOList(List<PrimeEntity> primes) {
        return primes.stream().map(this::toPrimeDTO).collect(Collectors.toList());
    }

    // Méthode pour obtenir une liste d'employés avec le total des montants de prime pour chaque employé
    public List<EmployeeDTO> getEmployeesWithTotalPrime() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeesWithTotalPrime = new ArrayList<>();
        for (EmployeeEntity employee : employees) {
            EmployeeDTO employeeDTO = convertToDTO(employee);
            employeeDTO.calculateTotalPrimes();
            employeeDTO.calculateTotalPrimesWithDate();
            employeesWithTotalPrime.add(employeeDTO);
        }
        return employeesWithTotalPrime;
    }

    // Méthode pour convertir EmployeeEntity en EmployeeDTO
    private EmployeeDTO convertToDTO(EmployeeEntity employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalaire(employee.getSalaire());

        // Convertir la liste de PrimeEntity en liste de PrimeDTO
        List<PrimeDTO> primeDTOs = new ArrayList<>();
        for (PrimeEntity prime : employee.getPrimes()) {
            PrimeDTO primeDTO = new PrimeDTO();
            primeDTO.setMontant(prime.getMontant());
            primeDTO.setDate(prime.getDate());
            primeDTO.setId(prime.getId());
            primeDTO.setLibelle(prime.getLibelle());
            // Autres attributs à transférer si nécessaire
            primeDTOs.add(primeDTO);
        }
        employeeDTO.setPrimes(primeDTOs);

        return employeeDTO;
    }
}
