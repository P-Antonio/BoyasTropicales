package com.Boyas.Tropicales.Entity;

import java.util.Date;

import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarCosechador;
import com.Boyas.Tropicales.controller.DTO.cosecha.CrearCosechador;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Cosechador")
@Table(name = "Cosechadores")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cosechador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private Integer cedula;
	@Column(name = "Fecha_Nacimeiento")
	private Date fechaNaciemiento;
	@Column(name = "Fecha_Inicio_Contrato")
	private Date fechaInicioContrato;
	@Column(name = "Fecha_Termino_Contrato")
	private Date fechaTerminoContrato;
	private boolean activo = true;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Registro_Cosecha")
	private RegistroCosecha rCosecha;

	public Cosechador(@Valid CrearCosechador cosechador) {
		this.nombre = cosechador.nombre();
		this.apellido = cosechador.apellido();
		this.cedula = cosechador.cedula();
		this.fechaNaciemiento = cosechador.fechaNacimiento();
		this.fechaInicioContrato = cosechador.fechaInicioContrato();
	}

	public void actualizarDatos(ActualizarCosechador actualizar) {
		if(actualizar.nombre() != null) {
			this.nombre = actualizar.nombre();
		}
		if(actualizar.apellido() != null) {
			this.apellido = actualizar.apellido();
		}
		if(actualizar.cedula() != actualizar.cedula()){
			this.cedula = actualizar.cedula();
		}
		if(actualizar.fechaNacimiento() != null) {
			this.fechaNaciemiento = actualizar.fechaNacimiento();
		}
	}

	public void inhabilitar() {
		this.activo = false;	
	}
}
