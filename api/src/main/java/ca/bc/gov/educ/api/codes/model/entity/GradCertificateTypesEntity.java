package ca.bc.gov.educ.api.codes.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "GRAD_CERTIFICATE_TYPES")
public class GradCertificateTypesEntity extends BaseEntity {
   
	@Id
	@Column(name = "CERTIFICATE_CODE", nullable = false)
    private String code; 
	
	@Column(name = "CERTIFICATE_DESCRIPTION", nullable = true)
    private String description;	
	
}