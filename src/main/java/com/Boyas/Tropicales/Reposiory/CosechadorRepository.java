package com.Boyas.Tropicales.Reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Boyas.Tropicales.Entity.Cosechador;

@Repository
public interface CosechadorRepository extends JpaRepository<Cosechador, Long>{

}
