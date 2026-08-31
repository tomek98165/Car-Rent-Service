package com.carrentservice.domain.crud.util;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryCarRepositoryImpl implements CarRepository{
    private final Map<Long, Car> db = new HashMap<>();
    private long idCounter = 1;

    public InMemoryCarRepositoryImpl() {
    }
    @Override
    public Optional<Car> findById(Long aLong) {
        return Optional.ofNullable(db.get(aLong));
    }
    @Override
    public Page<Car> findAll(Pageable pageable) {
        List<Car> cars = new ArrayList<>(db.values());
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), cars.size());

        List<Car> pagedCars = start < cars.size() ? cars.subList(start, end): List.of();
        return new PageImpl<>(pagedCars, pageable, cars.size());
    }
    @Override
    public <S extends Car> S save(S entity) {
        if(findById(entity.getId()).isPresent()){
            Car car = new Car(
                    entity.getId(),
                    entity.getMake(),
                    entity.getModel(),
                    entity.getLicensePlate(),
                    entity.getDescription(),
                    entity.getYear(),
                    entity.isAvailability());
            db.put(entity.getId(), car);
            return (S) car;
        }
        else if(findByLicensePlate(entity.getLicensePlate()).isEmpty()) {
            Car car = new Car(
                    idCounter,
                    entity.getMake(),
                    entity.getModel(),
                    entity.getLicensePlate(),
                    entity.getDescription(),
                    entity.getYear(),
                    entity.isAvailability());
            db.put(idCounter, car);
            idCounter++;
            return (S) car;
        }else{
            throw new DuplicateKeyException("Car with license plate: " + entity.getLicensePlate() + " already exist");
        }
    }
    @Override
    public Page<Car> findAllByAvailabilityIsTrue(Pageable pageable) {
        List<Car> filtered =  db.values().stream()
                .filter(car -> car.isAvailability()==true)
                .toList();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<Car> pagedCars = start < filtered.size() ? filtered.subList(start, end): List.of();
        return new PageImpl<>(pagedCars, pageable, filtered.size());

    }

    @Override
    public Optional<Car> findByLicensePlate(String licensePlate) {
        return db.values().stream()
                .filter(car -> Objects.equals(car.getLicensePlate(), licensePlate))
                .findFirst();
    }

    @Override
    public Set<Car> findAllByMakeAndModel(String make, String model) {
        return db.values().stream()
                .filter(car -> Objects.equals(car.getMake(), make))
                .filter(car -> Objects.equals(car.getModel(),model))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Car> findAllByMake(String make) {
        return db.values().stream()
                .filter(car -> Objects.equals(car.getMake(), make))
                .collect(Collectors.toSet());
    }

    @Override
    public <S extends Car> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public Car getById(Long aLong) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Car> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Car> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Car> entities) {

    }

    @Override
    public void deleteAll(Iterable<? extends Car> entities) {
        db.clear();
        idCounter = 1;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Car getOne(Long aLong) {
        return null;
    }

    @Override
    public Car getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Car> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Car> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Car> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Car> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Car, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }



    @Override
    public <S extends Car> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Car> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public List<Car> findAllById(Iterable<Long> longs) {
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
    public void delete(Car entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Car> findAll(Sort sort) {
        return null;
    }


}
