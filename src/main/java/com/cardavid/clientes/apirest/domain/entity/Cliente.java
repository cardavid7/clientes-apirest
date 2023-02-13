package com.cardavid.clientes.apirest.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "seq_id", sequenceName = "cliente_seq", allocationSize = 1)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="n_id", nullable = false)
	private Long id;
	
	@Column(name="s_nombres", nullable = false)
	private String nombres;
	
	@Column(name="s_apellidos", nullable = false)
	private String apellidos;
	
	@Column(name="s_email", nullable = false, unique = true)
	private String email;
	
	@Column (name="d_create_at", nullable = false)
	private LocalDate createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = LocalDate.now();
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", email=" + email
				+ ", createAt=" + createAt + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	
	
}
