package com.Boyas.Tropicales.Imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Boyas.Tropicales.Entity.RegistroCosecha;
import com.Boyas.Tropicales.Reposiory.RegistroCRepository;
import com.Boyas.Tropicales.Service.RegistroService;

@Service
public class RegistroCosechaImp implements RegistroService{

	@Autowired
	private RegistroCRepository registroCRepository;
	
	@Override
	public List<RegistroCosecha> findAll() {
		return registroCRepository.findAll();
	}

	@Override
	public RegistroCosecha findById(Long id) {
		return registroCRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(RegistroCosecha registroCosecha) {
		registroCRepository.save(registroCosecha);
	}

	@Override
	public void deleteById(Long id) {
		registroCRepository.deleteById(id);
	}

	@Override
	public RegistroCosecha findByFecha(Date fecha) {
		return registroCRepository.findByFechaCosecha(fecha);
	}

}
