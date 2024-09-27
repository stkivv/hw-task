package stkivv.hwtask.hometask.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stkivv.hwtask.hometask.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
