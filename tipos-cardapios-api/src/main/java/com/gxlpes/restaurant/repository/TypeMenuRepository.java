package com.gxlpes.restaurant.repository;

import com.gxlpes.restaurant.model.TypeMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMenuRepository extends JpaRepository<TypeMenu, String> {
    TypeMenu findByName(String name);

    Page<TypeMenu> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
