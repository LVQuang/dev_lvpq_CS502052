package dev.lvpq.CS502052.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.lvpq.CS502052.Entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> { }