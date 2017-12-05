package br.com.index;

import javax.persistence.Entity;

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
public class Index extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -846910470649617846L;

	private String nome;

	private String codigo;

}
