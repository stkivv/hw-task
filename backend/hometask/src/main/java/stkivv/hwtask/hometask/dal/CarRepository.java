package stkivv.hwtask.hometask.dal;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stkivv.hwtask.hometask.domain.Car;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c from Car c where c.make LIKE %:keyword% OR c.model LIKE %:keyword% OR c.numberPlate LIKE %:keyword% ")
    List<Car> findByKeyword(String keyword, Sort sort);
}
