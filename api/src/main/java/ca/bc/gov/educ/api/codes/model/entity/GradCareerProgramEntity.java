package ca.bc.gov.educ.api.codes.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "GRAD_CAREER_PROGRAM")
public class GradCareerProgramEntity {
   
	@Id
	@Column(name = "CODE", nullable = false)
    private String code; 
	
	@Column(name = "DESCRIPTION", nullable = true)
    private String description; 

	@Column(name = "START_DT", nullable = true)
    private Date startDate; 
	
	@Column(name = "END_DT", nullable = true)
    private Date endDate;	
	
	@Column(name = "CREATED_BY", nullable = true)
    private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP", nullable = true)
    private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY", nullable = true)
    private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP", nullable = true)
    private Date updatedTimestamp;	
	
	
}