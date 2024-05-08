package com.Boyas.Tropicales.Reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boyas.Tropicales.Entity.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long> {

}
