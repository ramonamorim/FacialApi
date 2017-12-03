package br.com.facial.recognitionconfig;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "RECOGNITIONCONFIG")
public class RecognitionConfig extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4886824247247295362L;

	private String codeStatus;

	private String description;

}
