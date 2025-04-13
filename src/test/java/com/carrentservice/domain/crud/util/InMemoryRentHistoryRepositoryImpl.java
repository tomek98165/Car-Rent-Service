package com.carrentservice.domain.crud.util;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryRentHistoryRepositoryImpl implements RentHistoryRepository{
    @Override
    public List<RentHistory> findByReturnDateIsNull(Pageable pageable) {
        return null;
    }

    @Override
    public List<RentHistory> findByCustomerId(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public List<RentHistory> findByCustomerIdAndReturnDateIsNull(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public List<RentHistory> findByCarId(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RentHistory> findByCarIdAndReturnDateIsNull(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<RentHistory> findTopByCarIdOrderByReturnDateDesc(Long id) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends RentHistory> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends RentHistory> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<RentHistory> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public RentHistory getOne(Long aLong) {
        return null;
    }

    @Override
    public RentHistory getById(Long aLong) {
        return null;
    }

    @Override
    public RentHistory getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends RentHistory> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RentHistory> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RentHistory> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RentHistory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RentHistory> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RentHistory> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends RentHistory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends RentHistory> S save(S entity) {
        return null;
    }

    @Override
    public <S extends RentHistory> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RentHistory> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<RentHistory> findAll() {
        return null;
    }

    @Override
    public List<RentHistory> findAllById(Iterable<Long> longs) {
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
    public void delete(RentHistory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RentHistory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<RentHistory> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RentHistory> findAll(Pageable pageable) {
        return null;
    }
}
