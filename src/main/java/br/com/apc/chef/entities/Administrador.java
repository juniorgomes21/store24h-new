package br.com.apc.chef.entities;

import javax.persistence.Entity;

@Entity
public class Administrador extends User{

	public Administrador() {
	}

	public Administrador(String name, String celular, String email, String senha) {
		super(name, celular, email, senha);
	}
}
