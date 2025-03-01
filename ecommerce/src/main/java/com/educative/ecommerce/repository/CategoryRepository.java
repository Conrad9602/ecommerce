package com.educative.ecommerce.repository;

import com.educative.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Marks this interface as a Spring Data Repository, so Spring can detect
 * and manage it as a database access component.
 * When CategoryRepository is autowired to a service, the resulting object
 * will be capable of directly communicating with the database using the
 * methods in JpaRepository.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName);
}
