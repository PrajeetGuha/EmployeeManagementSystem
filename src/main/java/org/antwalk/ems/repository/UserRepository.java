package org.antwalk.ems.repository;

import java.util.Optional;

import org.antwalk.ems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);

    @Query("update User u set u.isEnabled = 0 where userId = :userId")
    public void disableUserById(Long userId);

    @Query("update User u set u.isEnabled = 1 where userId = :userId")
    public void enableUserById(Long userId);
}
