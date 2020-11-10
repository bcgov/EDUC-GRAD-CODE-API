package ca.bc.gov.educ.api.codes.model.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GradProgram {

	private String programCode; 
	private String programName; 
	private Date programStartDate;	
	private Date programEndDate;
	
	@Override
	public String toString() {
		return "GradProgram [programCode=" + programCode + ", programName=" + programName + ", programStartDate="
				+ programStartDate + ", programEndDate=" + programEndDate + "]";
	}	
}
