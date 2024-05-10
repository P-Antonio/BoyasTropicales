package com.Boyas.Tropicales.Entity;

import java.util.Set;

import com.Boyas.Tropicales.Entity.Enums.EnumRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name ="Role")
@Table (name ="Roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "Roles")
	private EnumRole roles;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "rol_authorizacion", joinColumns = @JoinColumn (name = "id-rol"), inverseJoinColumns = @JoinColumn(name = "id_Autorizacion"))
	private Set<Authority> authorizations;
 	
}
