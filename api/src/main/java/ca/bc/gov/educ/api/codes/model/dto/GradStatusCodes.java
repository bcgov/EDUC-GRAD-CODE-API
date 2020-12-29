package ca.bc.gov.educ.api.codes.model.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class GradStatusCodes extends BaseModel {

	private String code;	
	private String name;
	private String description;	
	
	@Override
	public String toString() {
		return "GradStatusCodes [code=" + code + ", name=" + name + ", description=" + description + "]";
	}	
	
}
