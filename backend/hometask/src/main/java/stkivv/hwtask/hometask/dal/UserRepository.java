package stkivv.hwtask.hometask.dal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import stkivv.hwtask.hometask.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingOrderByNameAsc(String name);

    List<User> findByNameContainingOrderByNameDesc(String name);

    List<User> findByNameContainingOrderByIdAsc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);
}
