package ca.bc.gov.educ.api.codes.model.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class GradCertificateTypes extends BaseModel {

	private String code;	
	private String description;
	
	@Override
	public String toString() {
		return "GradCertificateTypes [code=" + code + ", description=" + description + "]";
	}
	
	
}
