package com.Boyas.Tropicales.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Boyas.Tropicales.Entity.Ventas;
import com.Boyas.Tropicales.Reposiory.VentasRepository;
import com.Boyas.Tropicales.Service.VentasService;

@Service
public class VentasImp implements VentasService{

	@Autowired
	private VentasRepository ventasRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ventas> findAll() {
		return ventasRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Ventas findById(Long id) {
		return ventasRepository.findById(id).orElseThrow();
	}

	@Override
	@Transactional
	public void save(Ventas ventas) {
		ventasRepository.save(ventas);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		ventasRepository.deleteById(id);	
	}

	@Override
	public Page<Ventas> findByEntregadoTrue(Pageable paginacion) {
		return ventasRepository.findByEntregadoTrue(paginacion);
	}

}
