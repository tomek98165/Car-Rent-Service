package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.*;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CarRentServiceCrudFacadeTest {
    private final Instant FIXED_INSTANT = Instant.parse("2026-03-19T14:30:00Z");
    private final Clock FIXED_CLOCK = Clock.fixed(FIXED_INSTANT, ZoneId.of("UTC"));

//    private final TimeProvider timeProvider = new TimeProvider(FIXED_CLOCK);
    private CarRentServiceCrudFacade carRentServiceCrudFacade;
    TimeProviderTestImpl timeProvider;
    @BeforeEach
    public void setUp(){
        timeProvider = new TimeProviderTestImpl(FIXED_CLOCK);
        carRentServiceCrudFacade = CarRentServiceCrudFacadeConfiguration.createCarRentServiceCrud(
                new InMemoryCarRepositoryImpl(),
                new InMemoryCustomerRepositoryImpl(),
                new InMemoryEmployeeRepositoryImpl(),
                new InMemoryRentHistoryRepositoryImpl(),
                timeProvider);
    }
    Pageable pageable = PageRequest.of(0,10);
    CarDto fordFiesta = new CarDto(
            1L,
            "Ford",
            "Fiesta",
            "ewq12344",
            "test",
            2000,
            true);
    CarDto fordFiesta2 = new CarDto(
            2L,
            "Ford",
            "Fiesta",
            "abc12345",
            "test",
            2000,
            true);
    CarRequestDto fordFiestaRequest = new CarRequestDto(
            "Ford",
            "Fiesta",
            "ewq12344",
            "test",
            2000);
    CarRequestDto fordFiestaRequest2 = new CarRequestDto(
            "Ford",
            "Fiesta",
            "abc12345",
            "test",
            2000);
    CarDto fordMondeo = new CarDto(
            3L,
            "Ford",
            "Mondeo",
            "ewq12345",
            "test",
            2000,
            true);
    CarRequestDto fordMondeoRequest = new CarRequestDto(
            "Ford",
            "Mondeo",
            "ewq12345",
            "test",
            2000);

    @Test
    @DisplayName("Should return empty list, when is no car in db")
    public void shouldGiveEmptyWhenIsNoCarInDb(){
        //give

        //when
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCars(pageable);

        //then
        assertThat(cars).isEmpty();

    }
    @Test
    @DisplayName("Should add new car to db")
    public void shouldAddNewCar(){
        //give
        //When
        CarDto carDto = carRentServiceCrudFacade.addCar(fordFiestaRequest);

        //Then

        Set<CarDto> cars = carRentServiceCrudFacade.findAllCars(pageable);
        assertThat(cars).hasSize(1);
    }
    @Test
    @DisplayName("Should return Car with id 1")
    public void shouldReturnCarWithId1(){
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        //when
        CarDto carWithId1 = carRentServiceCrudFacade.findCarById(1L);
        //then
        assertThat(carWithId1).isEqualTo(fordFiesta);

    }
    @Test
    @DisplayName("Should return Car with id 123 is not exist")
    public void shouldReturnCarWithIdIsNotExistException() {
        //give
        //when
        Throwable thrown = catchThrowable(() -> carRentServiceCrudFacade.findCarById(123L));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage("Car with id: 123 not found");
    }
    @Test
    @DisplayName("Should find car with license plate")
    public void shouldFindCarWithLicensePlate(){
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        //When
        CarDto result = carRentServiceCrudFacade.findCarByLicensePlate("ewq12344");
        //Then

        assertThat(result).isEqualTo(fordFiesta);
    }
    @Test
    @DisplayName("Should return 2 cars with the same Make")
    public void shouldReturn3CarsWithSameMake(){
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addCar(fordFiestaRequest2);
        carRentServiceCrudFacade.addCar(fordMondeoRequest);

        //When
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCarsByMake("Ford");

        //Then
        assertThat(cars).hasSize(3);
        assertThat(cars).containsExactlyInAnyOrder(fordFiesta,fordFiesta2,fordMondeo);
    }
    @Test
    @DisplayName("Should return 2 cars with the same Make and model,When 3 cars in db")
    public void shouldReturn2CarsWithSameMakeAndModel(){
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addCar(fordFiestaRequest2);
        carRentServiceCrudFacade.addCar(fordMondeoRequest);

        //When
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCarsByMakeAndModel("Ford", "Fiesta");
        Set<CarDto> allCars = carRentServiceCrudFacade.findAllCars(pageable);

        //Then
        assertThat(allCars).hasSize(3);
        assertThat(cars).hasSize(2);
    }
    @Test
    @DisplayName("Should throw exception when license plate already exist")
    public void shouldThrowExceptionWhenLicensePlateAlreadyExist(){
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        //When
        Throwable thrown = catchThrowable(() -> carRentServiceCrudFacade.addCar(fordFiestaRequest));

        //Then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Car with license plate: ewq12344 already exist");
    }

    //Customer Test
    CustomerDto customer1 = new CustomerDto(
            1L,
            "test",
            "test,",
            "test",
            "00-000",
            "test",
            "12345678910",
            new Date(21,12,2000),
            Gender.Male,
            "123456789",
            "test@test.pl");
    CustomerRequestDto customerRequest1 = new CustomerRequestDto(
            "test",
            "test,",
            "test",
            "00-000",
            "test",
            "12345678910",
            new Date(21,12,2000),
            Gender.Male,
            "123456789",
            "test@test.pl"
    );
    CustomerRequestDto customerRequest2 = new CustomerRequestDto(
            "test",
            "test,",
            "test",
            "00-000",
            "test",
            "12345678911",
            new Date(21,12,2000),
            Gender.Male,
            "123456789",
            "test@test.pl"
    );
    CustomerRequestDto customerRequest3 = new CustomerRequestDto(
            "test",
            "test,",
            "test",
            "00-000",
            "test",
            "12345678912",
            new Date(21,12,2000),
            Gender.Male,
            "123456789",
            "test@test.pl"
    );

    @Test
    @DisplayName("Should return empty list, when is no customers in db")
    public void shouldGiveEmptyWhenIsNoCustomersInDb(){
        //give

        //when
        Set<CustomerDto> result = carRentServiceCrudFacade.findAllCustomers(pageable);

        //then
        assertThat(result).isEmpty();

    }
    @Test
    @DisplayName("Should add new customer to db And find by id")
    public void shouldAddNewCustomerAndFindById(){
        //give
        //when
        CustomerDto result = carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        CustomerDto result2 = carRentServiceCrudFacade.findCustomerById(result.id());
        //then
        assertThat(result).isEqualTo(result2);
    }
    @Test
    @DisplayName("Should throw exception when pesel exist in db")
    public void shouldThrowExceptionWhenPeselExistInDb(){
        //give
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.addNewCustomer(customerRequest1));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Customer with pesel " + customerRequest1.pesel() + " already exist");
    }
    @Test
    @DisplayName("Should return 3 customer")
    public void shouldReturn3Customers(){
        //give
        CustomerDto customer1 = carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        CustomerDto customer2 = carRentServiceCrudFacade.addNewCustomer(customerRequest2);
        CustomerDto customer3 = carRentServiceCrudFacade.addNewCustomer(customerRequest3);
        //when
        Set<CustomerDto> result = carRentServiceCrudFacade.findAllCustomers(pageable);
        //then
        assertThat(result).hasSize(3).containsExactlyInAnyOrder(customer1,customer2,customer3);

    }
    @Test
    @DisplayName("Should throw exception when customer with id 123 doesnt exist")
    public void throwExceptionWhenCustomerIdDoesntExist(){
        //give
        //when
        Throwable thrown = catchThrowable(()->carRentServiceCrudFacade.findCustomerById(123L));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with id: 123 not found");

    }
    @Test
    @DisplayName("Should find customer by pesel")
    public void shouldFindCustomerByPesel(){
        //give
        CustomerDto customer = carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        CustomerDto result = carRentServiceCrudFacade.findCustomerByPesel(customerRequest1.pesel());
        //then
        assertThat(result).isEqualTo(customer);
    }
    @Test
    @DisplayName("Should Throw Exception When pesel doesnt exist")
    public void shouldThrowExceptionWhenPeselDoesntExist(){
        //give
        String pesel = "1111111111";
        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.findCustomerByPesel(pesel));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with pesel: " + pesel + " not found");
    }
    @Test
    @DisplayName("Should update customer data")
    public void shouldUpdateCustomerData(){
        //give
        CustomerDto customer = carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        CustomerDto updatedCustomer = new CustomerDto(
                1L,
                "test",
                "tested",
                "test",
                "00-000",
                "test",
                "12345678910",
                new Date(21,12,2000),
                Gender.Male,
                "123456789",
                "test@test.pl");
        //when
        CustomerDto result = carRentServiceCrudFacade.updateCustomer(updatedCustomer);
        //then
        assertThat(result).isEqualTo(updatedCustomer);
    }
    @Test
    @DisplayName("Should throw Exception when pesel i occupied by other customer")
    public void shouldThrowExceptionWhenUpdatedCustomerPeselExistInDb(){
        //give
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.addNewCustomer(customerRequest2);
        CustomerDto updatedCustomer = new CustomerDto(
                1L,
                "test",
                "tested",
                "test",
                "00-000",
                "test",
                "12345678911",
                new Date(21,12,2000),
                Gender.Male,
                "123456789",
                "test@test.pl");
        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.updateCustomer(updatedCustomer));
        //then
        assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Customer with pesel " + updatedCustomer.pesel() + " already exist");
    }

    //Employee Test

    EmployeeDto employee1 = new EmployeeDto(
            1L,
            "David",
            "Test",
            "d_test",
            "123456",
            null
    );
    EmployeeDto employee1WithAuthorities = new EmployeeDto(
            1L,
            "David",
            "Test",
            "d_test",
            "123456",
            EnumSet.of(Authorities.MANAGER,Authorities.EMPLOYEE)
    );
    EmployeeDto employee2 = new EmployeeDto(
            2L,
            "Frank",
            "Test",
            "f_test",
            "123456",
            null
    );
    EmployeeRequestDto employee1Request = new EmployeeRequestDto(
            "David",
            "Test",
            "d_test",
            "123456"
            );
    EmployeeRequestDto employee2Request = new EmployeeRequestDto(
            "Frank",
            "Test",
            "f_test",
            "123456"
    );

    @Test
    @DisplayName("Should return empty list, when is no employees in db")
    public void shouldGiveEmptyWhenIsNoEmployeesInDb(){
        //give

        //when
        Set<EmployeeDto> result = carRentServiceCrudFacade.findAllEmployees(pageable);

        //then
        assertThat(result).isEmpty();

    }

    @Test
    @DisplayName("Should add new employee to DB")
    public void shouldAddNewEmployeeToDb(){
        //give
        //when
        EmployeeDto result = carRentServiceCrudFacade.addNewEmployee(employee1Request);

        //then
        assertThat(result).isEqualTo(employee1);
    }

    @Test
    @DisplayName("Should find all 2 employees")
    public void shouldFindAll2Employees(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewEmployee(employee2Request);

        //when
        Set<EmployeeDto> result = carRentServiceCrudFacade.findAllEmployees(pageable);

        //then
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("Should return employee with id 2")
    public void shouldReturnEmployeeWithId2(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewEmployee(employee2Request);

        //when
        EmployeeDto result = carRentServiceCrudFacade.findEmployeeById(2L);

        //then
        assertThat(result).isEqualTo(employee2);
    }
    @Test
    @DisplayName("Should return employee with username d_test")
    public void shouldReturnEmployeeWithUsername(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);

        //when
        EmployeeDto result = carRentServiceCrudFacade.findEmployeeByUsername("d_test");

        //then
        assertThat(result).isEqualTo(employee1);
    }
    @Test
    @DisplayName("Should throw exception when id is 123")
    public void shouldThrowExceptionWhenIdDoesntExistInDb(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewEmployee(employee2Request);

        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.findEmployeeById(123L));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Employee with id: 123 not found");
    }
    @Test
    @DisplayName("Should throw exception when username is test_f")
    public void shouldThrowExceptionWhenUsernameDoesntExistInDb(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewEmployee(employee2Request);

        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.findEmployeeByUsername("test_f"));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Employee with username: test_f not found");
    }
    @Test
    @DisplayName("Should throw exception when username exist in DB when add new employee")
    public void shouldThrowExceptionWhenUsernameExistInDbWhenAddNewEmployee(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);

        //when
        Throwable thrown = catchThrowable(()-> carRentServiceCrudFacade.addNewEmployee(employee1Request));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Employee with username: " + employee1.username() + " already exist");
    }

    @Test
    @DisplayName("Should should add authorities to employee")
    public void shouldAddAuthoritiesToEmployee(){
        //give
        carRentServiceCrudFacade.addNewEmployee(employee1Request);

        //when
        EmployeeDto result = carRentServiceCrudFacade.setAuthorities(1L, EnumSet.of(Authorities.EMPLOYEE, Authorities.MANAGER));


        assertThat(result).isEqualTo(employee1WithAuthorities);
    }


    //Rent History
    RentHistoryRequestDto rentHistory1Request = new RentHistoryRequestDto(
            1L,
            1L,
            1L
    );
    RentHistoryRequestDto rentHistory2Request = new RentHistoryRequestDto(
            2L,
            1L,
            1L
    );
    RentHistoryRequestDto rentHistory3Request = new RentHistoryRequestDto(
            3L,
            2L,
            1L
    );
    RentHistoryReturnRequestDto returnCarRequest = new RentHistoryReturnRequestDto(
            1L,
            1L
    );
    RentHistoryReturnRequestDto returnCarRequest2 = new RentHistoryReturnRequestDto(
            2L,
            1L
    );

    @Test
    @DisplayName("Should return empty when is no data")
    public void shouldReturnEmptyWhenIsNoData(){
        //give
        //when
        Set<RentHistoryWithDataDto> result = carRentServiceCrudFacade.findAllRentHistory(pageable);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should rent car and add to db")
    public void shouldRentCarAndAddToDb() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        RentHistoryWithRentDataDto result = carRentServiceCrudFacade.rentCar(rentHistory1Request);
        CarDto rentedCar = carRentServiceCrudFacade.findCarById(1L);
        //then
        assertThat(result).isEqualTo(new RentHistoryWithRentDataDto(
                1L,
                fordFiesta,
                customer1,
                employee1,
                timeProvider.now()
        ));
        assertThat(rentedCar.availability()).isFalse();

    }
    @Test
    @DisplayName("Should throw exception when car is arleady rented")
    public void shouldThrowExceptionWhenCarIsRented() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        Throwable thrown = catchThrowable(() -> carRentServiceCrudFacade.rentCar(rentHistory1Request));
        //then

        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(RentHistoryCarAvailableException.class)
                .hasMessage("Car is not available");
    }
    @Test
    @DisplayName("Should return car")
    public void shouldReturnCar() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        CarDto rentedCar = carRentServiceCrudFacade.findCarById(1L);
        timeProvider.advanceDays(2);
        RentHistoryWithDataDto result = carRentServiceCrudFacade.returnCar(returnCarRequest);
        CarDto returnedCar = carRentServiceCrudFacade.findCarById(1L);
        //then

        assertThat(rentedCar.availability()).isFalse();
        assertThat(result).isEqualTo(new RentHistoryWithDataDto(
                        1L,
                        fordFiesta,
                        customer1,
                        employee1,
                        employee1,
                        timeProvider.daysAgo(2),
                        timeProvider.now()
        ));
        assertThat(returnedCar.availability()).isTrue();
    }
    @Test
    @DisplayName("Should return 2 available car When 3 cars in db")
    public void shouldReturn2AvaiableCarWhen3CarsInDb() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addCar(fordFiestaRequest2);
        carRentServiceCrudFacade.addCar(fordMondeoRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        //when
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        Set<CarDto> result = carRentServiceCrudFacade.findAllAvailableCars(pageable);
        //then
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("Should find Rent History By ID")
    public void shouldFindRentHistoryById() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        //when
        RentHistoryWithDataDto result = carRentServiceCrudFacade.findRentHistoryById(1L);
        //then
        assertThat(result)
                .isEqualTo(new RentHistoryWithDataDto(
                        1L,
                        fordFiesta,
                        customer1,
                        employee1,
                        null,
                        timeProvider.now(),
                        null
        ));
    }
    @Test
    @DisplayName("Should return last return in RentHistory")
    public void shouldReturnLastReturnInRentHistory() throws RentHistoryCarAvailableException {
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        timeProvider.advanceDays(5);
        carRentServiceCrudFacade.returnCar(returnCarRequest);
        timeProvider.advanceDays(1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        timeProvider.advanceDays(2);
        carRentServiceCrudFacade.returnCar(returnCarRequest2);
        //when
        RentHistoryWithDataDto result = carRentServiceCrudFacade.findLastReturnByCarId(1L);
        //then
        assertThat(result).isEqualTo(
                new RentHistoryWithDataDto(
                        2L,
                        fordFiesta,
                        customer1,
                        employee1,
                        employee1,
                        timeProvider.daysAgo(2),
                        timeProvider.now()
                ));

    }

    @Test
    @DisplayName("Should return RentHistory with data rented car")
    public void shouldReturnReturnHistoryWithDataRentedCar() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        //when
        RentHistoryWithDataDto result = carRentServiceCrudFacade.findLastRentCarByCarId(1L);
        //then
        assertThat(result).isEqualTo(
                new RentHistoryWithDataDto(
                        1L,
                        fordFiesta,
                        customer1,
                        employee1,
                        null,
                        timeProvider.now(),
                        null
                ));
    }

    @Test
    @DisplayName("Should return whole rent history a Car")
    public void shouldReturnWholeRentHistoryACar() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addCar(fordFiestaRequest2);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        carRentServiceCrudFacade.rentCar(rentHistory2Request);
        timeProvider.advanceDays(2);
        carRentServiceCrudFacade.returnCar(returnCarRequest);
        timeProvider.advanceDays(1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);

        //when
        Set<RentHistoryWithDataDto> result = carRentServiceCrudFacade.findAllRentHistoryByCarId(1L, pageable);

        //then
        assertThat(result).hasSize(2);

    }

    @Test
    @DisplayName("Should return whole rent history by customer id")
    public void shouldReturnWholeRentHistoryByCustomerID() throws RentHistoryCarAvailableException {
        //give
        carRentServiceCrudFacade.addCar(fordFiestaRequest);
        carRentServiceCrudFacade.addCar(fordFiestaRequest2);
        carRentServiceCrudFacade.addCar(fordMondeoRequest);
        carRentServiceCrudFacade.addNewEmployee(employee1Request);
        carRentServiceCrudFacade.addNewCustomer(customerRequest1);
        carRentServiceCrudFacade.addNewCustomer(customerRequest2);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        carRentServiceCrudFacade.rentCar(rentHistory2Request);
        timeProvider.advanceDays(2);
        carRentServiceCrudFacade.returnCar(returnCarRequest);
        timeProvider.advanceDays(1);
        carRentServiceCrudFacade.rentCar(rentHistory1Request);
        carRentServiceCrudFacade.rentCar(rentHistory3Request);
        //when
        Set<RentHistoryWithDataDto> allData = carRentServiceCrudFacade.findAllRentHistory(pageable);
        Set<RentHistoryWithDataDto> result = carRentServiceCrudFacade.findAllRentHistoryByCustomerId(1L,pageable);
        //then
        assertThat(allData).hasSize(4);
        assertThat(result).hasSize(3);
    }
}

