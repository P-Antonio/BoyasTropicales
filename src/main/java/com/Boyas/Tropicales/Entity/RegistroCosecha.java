package com.Boyas.Tropicales.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.Boyas.Tropicales.Entity.Enums.EnumArandanos;
import com.Boyas.Tropicales.Entity.Enums.EnumModulos;
import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarDatosCosecha;
import com.Boyas.Tropicales.controller.DTO.cosecha.DatosCosecha;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name ="RegistroCosecha")
@Table (name ="Registro_Cosecha")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistroCosecha {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Kilogramos_Cosechados")
	private float kilogramosCosechados;
	@Enumerated(EnumType.STRING)
	@Column(name = "Enum_Modulos")
	private EnumModulos modulos;
	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo_de_Aramdano")
	private EnumArandanos tipoArandano;
	@Column(name = "Fecha_Cosecha")
	private  LocalDate fechaCosecha = LocalDate.now();
	
	public RegistroCosecha(@Valid DatosCosecha datosCosecha) {
		this.kilogramosCosechados = datosCosecha.KilogramosCosechados();
		this.modulos = datosCosecha.modulos();
		this.tipoArandano = datosCosecha.tipoArandano();
	}

	public void actualizarRegistro (@Valid ActualizarDatosCosecha actualizar) {
		if(actualizar.modulos() != null) {
			this.modulos = actualizar.modulos();
		}
		if(actualizar.tipoArandano() != null) {
			this.tipoArandano = actualizar.tipoArandano();
		}
	}
}
