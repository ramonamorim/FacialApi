package br.com.facial.photo;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.facial.persistence.BaseEntity;
import br.com.facial.person.Person;
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
@Table(name = "PHOTO")
public class Photo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7373247078638272941L;

	private byte[] image;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSON")
	private Person person;

	@Nullable
	private String description;

}
