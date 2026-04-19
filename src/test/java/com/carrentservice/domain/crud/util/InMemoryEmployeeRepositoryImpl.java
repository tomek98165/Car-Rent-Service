package com.carrentservice.domain.crud.util;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class InMemoryEmployeeRepositoryImpl implements EmployeeRepository{
    private final Map<Long, Employee> db = new HashMap<>();
    private long idCounter = 1;
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        List<Employee> employees = new ArrayList<>(db.values());
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), employees.size());

        List<Employee> pagedEmployees = start < employees.size() ? employees.subList(start, end): List.of();
        return new PageImpl<>(pagedEmployees, pageable, employees.size());
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        return db.values().stream()
                .filter(employee -> Objects.equals(employee.getUsername(), username))
                .findFirst();
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return Optional.ofNullable(db.get(aLong));
    }

    @Override
    public <S extends Employee> S save(S entity) {
        if(!findById(entity.getId()).isEmpty()){
            Employee employee = new Employee(
                    entity.getId(),
                    entity.getName(),
                    entity.getLastName(),
                    entity.getUsername(),
                    entity.getPassword(),
                    entity.getAuthorities()
            );
            db.put(entity.getId(), employee);
            return (S)employee;
        }
        else if(findByUsername(entity.getUsername()).isEmpty()){
            Employee employee = new Employee(
                    idCounter,
                    entity.getName(),
                    entity.getLastName(),
                    entity.getUsername(),
                    entity.getPassword(),
                    entity.getAuthorities()
            );
            db.put(idCounter, employee);
            idCounter++;
            return (S)employee;
        }else{
            throw new DuplicateKeyException("Employee with username: " + entity.getUsername() + " already exist");
        }


    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Employee> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Employee> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Employee getOne(Long aLong) {
        return null;
    }

    @Override
    public Employee getById(Long aLong) {
        return null;
    }

    @Override
    public Employee getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Employee> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Employee> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Employee> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public List<Employee> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return null;
    }
}
