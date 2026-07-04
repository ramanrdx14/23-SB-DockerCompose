package org.example.repo;

import org.example.entity.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepo extends JpaRepository<Drinks,Integer> {
}
