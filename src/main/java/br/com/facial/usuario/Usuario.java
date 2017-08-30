package br.com.facial.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.facial.persistencia.BaseEntidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "USUARIO", uniqueConstraints = {
		@UniqueConstraint(name = "UK_USUARIO", columnNames = { "nome" }) })
@JsonIgnoreProperties(ignoreUnknown = true, value = { "senha" })
public class Usuario extends BaseEntidade {
	
	private static final long serialVersionUID = -3528892441811886364L;

	@NotNull
	private String nome;

	@NotNull
	private String nomeusuario;

	@NotNull
	private String email;

	@NotNull
	private String senha;

}
