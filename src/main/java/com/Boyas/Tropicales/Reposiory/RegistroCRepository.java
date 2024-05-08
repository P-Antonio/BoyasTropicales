package com.Boyas.Tropicales.Reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boyas.Tropicales.Entity.RegistroCosecha;

@Repository
public interface RegistroCRepository extends JpaRepository<RegistroCosecha, Long> {

}
