/**
 * 
 */
package br.com.apc.chef.dtos;

import javax.validation.constraints.NotBlank;

/**
 * @author Archer
 *
 */
public class UserDto {
	
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String celular;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    
    public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	private String rule;
    
    

	/**
	 * @param name
	 * @param celular
	 * @param email
	 * @param senha
	 */
	public UserDto(@NotBlank String name, @NotBlank String celular, @NotBlank String email, @NotBlank String senha) {
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
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
    
    
}
