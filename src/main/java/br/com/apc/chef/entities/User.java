package br.com.apc.chef.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true, length = 50)
    private String celular;
    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    private String senha; 
    private String rule;
    
    public User() {
		// TODO Auto-generated constructor stub
	}
    
	/**
	 *
	 * @param name
	 * @param celular
	 * @param email
	 * @param senha
	 */
	public User(String name, String celular, String email, String senha) {
		super();
		this.name = name;
		this.celular = celular;
		this.email = email;
		this.senha = senha;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
