package ca.bc.gov.educ.api.codes.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TAB_PRGM")
public class GradCareerProgramEntity  {
   
	@Id
	@Column(name = "PRGM_CODE", nullable = false)
    private String code; 
	
	@Column(name = "PRGM_NAME", nullable = true)
    private String description; 

	@Column(name = "START_DATE", nullable = true)
    private Date startDate; 
	
	@Column(name = "END_DATE", nullable = true)
    private Date endDate;	
}