package ca.bc.gov.educ.api.codes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Immutable
@Entity
@Table(name = "GRAD_PROGRAM_TYPE")
public class GradProgramTypesEntity extends BaseEntity {
    
	@Id
	@Column(name = "CODE", nullable = false)
    private String code; 
	
	@Column(name = "DESCRIPTION", nullable = true)
    private String description;	
}