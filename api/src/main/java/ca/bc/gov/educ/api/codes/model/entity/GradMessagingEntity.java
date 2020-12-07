package ca.bc.gov.educ.api.codes.model.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name = "GRAD_MESSAGING")
public class GradMessagingEntity {
   
	@Id
	@Column(name = "ID", nullable = false)
    private UUID id; 
	
	@Column(name = "PGM_CODE", nullable = false)
    private String programCode; 
	
	@Column(name = "MSG_TYPE", nullable = false)
    private String messageType;
	
	@Column(name = "GRAD_MAIN_MSG", nullable = false)
    private String mainMessage;
	
	@Column(name = "GRAD_DATE", nullable = false)
    private String gradDate;
	
	@Column(name = "HONOURS", nullable = false)
    private String honours;
	
	@Column(name = "AD_IB_PGMS", nullable = false)
    private String adIBPrograms;
	
	@Column(name = "PGM_CADRE", nullable = false)
    private String programCadre;
	
	@Column(name = "CAREER_PGMS", nullable = false)
    private String careerPrograms;		
	
	@Column(name = "CREATED_BY", nullable = true)
    private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP", nullable = true)
    private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY", nullable = true)
    private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP", nullable = true)
    private Date updatedTimestamp;	
}