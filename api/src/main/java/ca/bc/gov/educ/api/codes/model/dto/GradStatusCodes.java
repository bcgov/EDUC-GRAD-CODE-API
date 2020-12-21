package ca.bc.gov.educ.api.codes.model.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GradStatusCodes {

	private String code;	
	private String name;
	private String description;	
	private String createdBy;	
	private Date createdTimestamp;	
	private String updatedBy;	
	private Date updatedTimestamp;	
	
	@Override
	public String toString() {
		return "GradStatusCodes [code=" + code + ", name=" + name + ", description=" + description + ", createdBy="
				+ createdBy + ", createdTimestamp=" + createdTimestamp + ", updatedBy=" + updatedBy
				+ ", updatedTimestamp=" + updatedTimestamp + "]";
	}	
	
}
