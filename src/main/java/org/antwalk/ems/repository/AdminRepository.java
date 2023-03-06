package org.antwalk.ems.repository;

import org.antwalk.ems.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    
}
