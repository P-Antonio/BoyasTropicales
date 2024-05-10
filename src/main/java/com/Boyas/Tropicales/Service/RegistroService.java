package com.Boyas.Tropicales.Service;

import java.util.Date;
import java.util.List;

import com.Boyas.Tropicales.Entity.RegistroCosecha;

public interface RegistroService {

	List<RegistroCosecha> findAll();
	RegistroCosecha findById (Long id);
	void save (RegistroCosecha registroCosecha);
	void deleteById (Long id);
	RegistroCosecha findByFecha(Date fecha);
}
