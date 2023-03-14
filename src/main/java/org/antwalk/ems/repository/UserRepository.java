package org.antwalk.ems.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.antwalk.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.isEnabled = 0 where tablePk = :empId and u.role = 'ROLE_EMP'")
    public void disableUserById(Long empId);

    @Transactional
    @Modifying
    @Query("update User u set u.isEnabled = 1 where tablePk = :empId and u.role = 'ROLE_EMP'")
    public void enableUserById(Long empId);

    @Query("select u.username from User u")
    public List<String> listAllUsernames();
}
