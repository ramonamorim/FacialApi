package br.com.facial.notifications;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "NOTIFICATIONS")
public class Notifications extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Lob
	private byte[] image;

	private Timestamp date;

}
