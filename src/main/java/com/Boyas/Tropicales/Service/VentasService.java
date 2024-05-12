package com.Boyas.Tropicales.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Boyas.Tropicales.Entity.Ventas;

public interface VentasService {

	List<Ventas> findAll ();
	Ventas findById (Long id);
	void save (Ventas ventas);
	void deleteById (Long id);
	Page<Ventas> findByEntregadoTrue(Pageable paginacion);
}
