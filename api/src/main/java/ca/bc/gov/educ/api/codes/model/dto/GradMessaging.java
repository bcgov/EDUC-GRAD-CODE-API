package ca.bc.gov.educ.api.codes.model.dto;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GradMessaging {

	private UUID id;	
	private String programCode;	
	private String messageType;	
	private String mainMessage;	
	private String gradDate;
	private String honours;	
	private String adIBPrograms;	
	private String programCadre;	
	private String careerPrograms;	
	private String createdBy;	
	private Date createdTimestamp;	
	private String updatedBy;	
	private Date updatedTimestamp;
	
	@Override
	public String toString() {
		return "GradMessaging [id=" + id + ", programCode=" + programCode + ", messageType=" + messageType
				+ ", mainMessage=" + mainMessage + ", gradDate=" + gradDate + ", honours=" + honours + ", adIBPrograms="
				+ adIBPrograms + ", programCadre=" + programCadre + ", careerPrograms=" + careerPrograms
				+ ", createdBy=" + createdBy + ", createdTimestamp=" + createdTimestamp + ", updatedBy=" + updatedBy
				+ ", updatedTimestamp=" + updatedTimestamp + "]";
	}
	
	
	
}
