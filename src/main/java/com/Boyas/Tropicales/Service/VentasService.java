package com.Boyas.Tropicales.Service;

import java.util.List;

import com.Boyas.Tropicales.Entity.Ventas;

public interface VentasService {

	List<Ventas> findAll ();
	Ventas findById (Long id);
	void save (Ventas ventas);
	void deleteById (Long id);
}
