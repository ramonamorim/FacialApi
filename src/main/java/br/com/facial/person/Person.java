package br.com.facial.person;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.facial.persistence.BaseEntity;
import br.com.facial.photo.Photo;
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
@Table(name = "PERSON", uniqueConstraints = { @UniqueConstraint(name = "UK_PERSON", columnNames = { "name" }) })
public class Person extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 9005259093945850324L;
    
    @NotNull
    private String name;
    
    private String lastName;
    
    @Email
    private String email;
    
    private String phone;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Photo> photos;
    
}
