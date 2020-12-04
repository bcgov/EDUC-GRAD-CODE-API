package ca.bc.gov.educ.api.codes;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramEntity;

@SpringBootApplication
public class EducGradCodeApiApplication {

	private static Logger logger = LoggerFactory.getLogger(EducGradCodeApiApplication.class);

	public static void main(String[] args) {
		logger.debug("########Starting API");
		SpringApplication.run(EducGradCodeApiApplication.class, args);
		logger.debug("########Started API");
	}

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(GradProgramEntity.class, GradProgram.class);
		modelMapper.typeMap(GradProgram.class, GradProgramEntity.class);
		return modelMapper;
	}
}