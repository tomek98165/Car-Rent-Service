package com.carrentservice.domain.crud.util;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class InMemoryCustomerRepositoryImpl implements CustomerRepository{
    Map<Long, Customer> db = new HashMap<>();
    Long idCounter = 1L;
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        List<Customer> customers = new ArrayList<>(db.values());
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), customers.size());

        List<Customer> pagedCustomers = start < customers.size() ? customers.subList(start,end): List.of();
        return new PageImpl<>(pagedCustomers, pageable, customers.size());
    }

    @Override
    public <S extends Customer> S save(S entity) {
        if(entity.getId() == null) {
            if (findByPesel(entity.getPesel()).isEmpty()) {
                Customer customer = new Customer(
                        idCounter,
                        entity.getName(),
                        entity.getLastName(),
                        entity.getAddress(),
                        entity.getZipCode(),
                        entity.getTown(),
                        entity.getPesel(),
                        entity.getBirthDate(),
                        entity.getGender(),
                        entity.getPhoneNumber(),
                        entity.getEmail()
                        );
                db.put(idCounter, customer);
                idCounter++;
                return (S) customer;
            } else {
                throw new DuplicateKeyException("Customer with pesel " + entity.getPesel() + " already exist");
            }
        }else {
            db.remove(entity.getId());
            if (findByPesel(entity.getPesel()).isEmpty()) {
                db.put(entity.getId(), entity);
                return (S) entity;
            } else {
                throw new DuplicateKeyException("Customer with pesel " + entity.getPesel() + " already exist");

            }
        }
    }

    @Override
    public Optional<Customer> findById(Long aLong) {
        return Optional.ofNullable(db.get(aLong));
    }

    @Override
    public Optional<Customer> findByPesel(String pesel) {
        return db.values().stream()
                .filter(customer -> customer.getPesel() == pesel)
                .findFirst();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Customer> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }
    @Override
    public void deleteAllInBatch(Iterable<Customer> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Customer getOne(Long aLong) {
        return null;
    }

    @Override
    public Customer getById(Long aLong) {
        return null;
    }

    @Override
    public Customer getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public List<Customer> findAllById(Iterable<Long> longs) {
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
    public void delete(Customer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return null;
    }
}
