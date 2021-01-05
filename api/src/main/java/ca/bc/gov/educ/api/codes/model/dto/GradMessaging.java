package ca.bc.gov.educ.api.codes.model.dto;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class GradMessaging extends BaseModel{

	private UUID id;	
	private String programCode;	
	private String messageType;	
	private String mainMessage;	
	private String gradDate;
	private String honours;	
	private String adIBPrograms;	
	private String programCadre;	
	private String careerPrograms;
	
	@Override
	public String toString() {
		return "GradMessaging [id=" + id + ", programCode=" + programCode + ", messageType=" + messageType
				+ ", mainMessage=" + mainMessage + ", gradDate=" + gradDate + ", honours=" + honours + ", adIBPrograms="
				+ adIBPrograms + ", programCadre=" + programCadre + ", careerPrograms=" + careerPrograms
				+ "]";
	}
	
	
	
}
