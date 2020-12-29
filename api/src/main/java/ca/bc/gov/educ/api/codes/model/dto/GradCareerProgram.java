package ca.bc.gov.educ.api.codes.model.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class GradCareerProgram extends BaseModel {
	
	private String code; 
	private String description; 
	private String startDate; 
	private String endDate;
	
	@Override
	public String toString() {
		return "GradCareerProgram [code=" + code + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}	
	
	
}
