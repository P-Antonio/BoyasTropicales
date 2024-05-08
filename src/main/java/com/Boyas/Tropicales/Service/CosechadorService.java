package com.Boyas.Tropicales.Service;

import java.util.List;
import java.util.Set;

import com.Boyas.Tropicales.Entity.Cosechador;


public interface CosechadorService {

	List<Cosechador> findAll();
	Cosechador findById(Long id);
	void save (Cosechador cosechador);
	void deleteById (Long id);
}
