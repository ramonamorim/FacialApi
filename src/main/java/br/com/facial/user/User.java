package br.com.facial.user;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.facial.persistence.BaseEntity;
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
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(name = "UK_USER", columnNames = { "name", "username" }) })
@JsonIgnoreProperties(ignoreUnknown = true, value = { "password" })
public class User extends BaseEntity {

	private static final long serialVersionUID = -3528892441811886364L;

	@Nullable
	private String name;

	@NotNull
	private String username;

	@Nullable
	private String email;

	@NotNull
	private String password;

}
