package com.carrentservice.domain.crud.util;


import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;


import java.util.*;
import java.util.function.Function;

public class InMemoryRentHistoryRepositoryImpl implements RentHistoryRepository{
    private final Map<Long, RentHistory> db = new HashMap<>();
    private long idCounter = 1;
    @Override
    public Optional<RentHistory> findById(Long aLong) {
        return Optional.ofNullable(db.get(aLong));
    }

    @Override
    public Page<RentHistory> findByReturnDateIsNull(Pageable pageable) {
        List<RentHistory> filtered = db.values().stream()
                .filter(rentHistory -> rentHistory.getReturnDate().equals(null))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<RentHistory> pagedRentHistory = start < filtered.size() ? filtered.subList(start, end): List.of();
        return new PageImpl<>(pagedRentHistory, pageable, filtered.size());
    }

    @Override
    public Page<RentHistory> findByCustomerId(Long id, Pageable pageable) {
        List<RentHistory> filtered =  db.values().stream()
                .filter(rentHistory -> rentHistory.getCustomer().getId().equals(id))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<RentHistory> pagedRentHistory = start < filtered.size() ? filtered.subList(start, end): List.of();
        return new PageImpl<>(pagedRentHistory, pageable, filtered.size());
    }

    @Override
    public Page<RentHistory> findByCustomerIdAndReturnDateIsNull(Long id, Pageable pageable) {
        List<RentHistory> filtered = db.values().stream()
                .filter(rentHistory -> rentHistory.getReturnDate().equals(null))
                .filter(rentHistory -> rentHistory.getCustomer().getId().equals(id))
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<RentHistory> pagedRentHistory = start < filtered.size() ? filtered.subList(start, end): List.of();
        return new PageImpl<>(pagedRentHistory, pageable, filtered.size());
    }

    @Override
    public Page<RentHistory> findByCarId(Long id, Pageable pageable) {
        List<RentHistory> filtered = db.values().stream()
                .filter(rentHistory -> rentHistory.getCar().getId().equals(id))
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<RentHistory> pagedRentHistory = start < filtered.size() ? filtered.subList(start, end): List.of();
        return new PageImpl<>(pagedRentHistory, pageable, filtered.size());
    }

    @Override
    public Optional<RentHistory> findByCarIdAndReturnDateIsNull(Long id) {
        return db.values().stream()
                .filter(rentHistory -> rentHistory.getCar().getId().equals(id))
                .findAny();
    }

    @Override
    public Optional<RentHistory> findTopByCarIdOrderByReturnDateDesc(Long id) {
        return db.values().stream()
                .filter(
                        rentHistory -> rentHistory.getCar().getId().equals(id)
                                && rentHistory.getReturnDate() != null
                )
                .max(Comparator.comparing(RentHistory::getReturnDate));
    }

    @Override
    public <S extends RentHistory> S save(S entity) {
        if(!findById(entity.getId()).isEmpty()){
            RentHistory rentHistory = new RentHistory(
                    entity.getId(),
                    entity.getCar(),
                    entity.getCustomer(),
                    entity.getEmployeeRent(),
                    entity.getRentDate()

            );
            rentHistory.setReturnDate(entity.getReturnDate());
            rentHistory.setEmployeeReturn(entity.getEmployeeReturn());
            db.put(entity.getId(), rentHistory);
            return (S)rentHistory;
        }
        else{
            RentHistory rentHistory = new RentHistory(
                    idCounter,
                    entity.getCar(),
                    entity.getCustomer(),
                    entity.getEmployeeRent(),
                    entity.getRentDate()
            );
            db.put(idCounter, rentHistory);
            idCounter++;
            return (S)rentHistory;
        }
    }

    @Override
    public Page<RentHistory> findAll(Pageable pageable) {
        List<RentHistory> rentHistories = new ArrayList<>(db.values());
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), rentHistories.size());

        List<RentHistory> pagedRentHistories= start < rentHistories.size() ? rentHistories.subList(start, end): List.of();
        return new PageImpl<>(pagedRentHistories, pageable, rentHistories.size());
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
    public <S extends RentHistory> List<S> saveAll(Iterable<S> entities) {
        return null;
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
}
