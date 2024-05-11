package com.Boyas.Tropicales.Entity;

import java.util.Date;

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

@Entity (name ="Cosechador")
@Table (name ="Cosechadores")
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
	@Column (unique = true)
	private Integer cedula;
	@Column (name = "Fecha_Nacimeiento")
	private Date fechaNaciemiento;
	@Column (name = "Fecha_Inicio_Contrato")
	private Date fechaInicioContrato;
	@Column (name = "Fecha_Termino_Contrato")
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
}
