package com.Boyas.Tropicales.Entity;

import com.Boyas.Tropicales.Entity.Enums.EnumArandanos;
import com.Boyas.Tropicales.Entity.Enums.EnumModulos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name ="RegistroCosecha")
@Table (name ="Registro Cosecha")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistroCosecha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int kilogramosCosechados;
	private EnumModulos modulos;
	private EnumArandanos tipoArandano;
}
