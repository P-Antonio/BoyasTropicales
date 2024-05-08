package com.Boyas.Tropicales.Imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.Boyas.Tropicales.Entity.Cosechador;
import com.Boyas.Tropicales.Reposiory.CosechadorRepository;
import com.Boyas.Tropicales.Service.CosechadorService;

public class CosechadorImp implements CosechadorService {

	
	@Autowired
	private CosechadorRepository cosechadorRepository;
	
	@Override
	@Transactional (readOnly = true)
	public List<Cosechador> findAll() {
		return cosechadorRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Cosechador findById(Long id) {
		return cosechadorRepository.findById(id).orElseThrow();
	}

	@Override
	@Transactional
	public void save(Cosechador cosechador) {
		cosechadorRepository.save(cosechador);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		cosechadorRepository.deleteById(id);
		
	}

}
