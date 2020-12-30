package ca.bc.gov.educ.api.codes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.educ.api.codes.model.dto.GradCareerProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProgramTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradRequirementTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradStatusCodes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.service.CodeService;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;
import ca.bc.gov.educ.api.codes.util.GradValidation;
import ca.bc.gov.educ.api.codes.util.PermissionsContants;
import ca.bc.gov.educ.api.codes.util.ResponseHelper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping(EducGradCodeApiConstants.GRAD_CODE_API_ROOT_MAPPING)
@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "API for Code Data.", description = "This Read API is for Reading Code data.", version = "1"), security = {@SecurityRequirement(name = "OAUTH2", scopes = {"READ_GRAD_COUNTRY_CODE_DATA","READ_GRAD_PROVINCE_CODE_DATA","READ_GRAD_PROGRAM_CODE_DATA","READ_GRAD_UNGRAD_CODE_DATA","READ_GRAD_CERTIFICATE_CODE_DATA","READ_GRAD_MESSEGING_CODE_DATA","READ_GRAD_CAREER_PROGRAM_CODE_DATA"})})
public class CodeController {

    private static Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeService codeService;
    
    @Autowired
	GradValidation validation;
    
    @Autowired
	ResponseHelper response;

    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM)
    public ResponseEntity<List<GradProgram>> getAllPrograms() { 
    	logger.debug("getAllPrograms : ");
        return response.GET(codeService.getAllProgramList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM)
    public ResponseEntity<GradProgram> getSpecificProgramCode(@PathVariable String programCode) { 
    	logger.debug("getSpecificProgramCode : ");
    	GradProgram gradResponse = codeService.getSpecificProgramCode(programCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_COUNTRY)
    public ResponseEntity<List<GradCountry>> getAllCountryCodeList() { 
    	logger.debug("getAllCountryCodeList : ");
        return response.GET(codeService.getAllCountryCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_COUNTRY)
    public ResponseEntity<GradCountry> getSpecificCountryCode(@PathVariable String countryCode) { 
    	logger.debug("getSpecificCountryCode : ");
    	GradCountry gradResponse = codeService.getSpecificCountryCode(countryCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROVINCE)
    public ResponseEntity<List<GradProvince>> getAllProvinceCodeList() { 
    	logger.debug("getAllProvinceCodeList : ");
        return response.GET(codeService.getAllProvinceCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROVINCE)
    public ResponseEntity<GradProvince> getSpecificProvinceCode(@PathVariable String provinceCode) { 
    	logger.debug("getSpecificProvinceCode : ");
    	GradProvince gradResponse = codeService.getSpecificProvinceCode(provinceCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_UNGRAD)
    public ResponseEntity<List<GradUngradReasons>> getAllUngradReasonCodeList() { 
    	logger.debug("getAllUngradReasonCodeList : ");
        return response.GET(codeService.getAllUngradReasonCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_UNGRAD)
    public ResponseEntity<GradUngradReasons> getSpecificUngradReasonCode(@PathVariable String reasonCode) { 
    	logger.debug("getSpecificUngradReasonCode : ");
    	GradUngradReasons gradResponse = codeService.getSpecificUngradReasonCode(reasonCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
        
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CERTIFICATE)
    public ResponseEntity<List<GradCertificateTypes>> getAllCertificateTypeCodeList() { 
    	logger.debug("getAllCertificateTypeCodeList : ");
        return response.GET(codeService.getAllCertificateTypeCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CERTIFICATE)
    public ResponseEntity<GradCertificateTypes> getSpecificCertificateTypeCode(@PathVariable String certTypeCode) { 
    	logger.debug("getSpecificCertificateTypeCode : ");
    	GradCertificateTypes gradResponse = codeService.getSpecificCertificateTypeCode(certTypeCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_MESSAGING_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_MESSAGING)
    public ResponseEntity<List<GradMessaging>> getAllGradMessagingList() { 
    	logger.debug("getAllGradMessagingList : ");
        return response.GET(codeService.getAllGradMessagingList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_MESSAGING_BY_PRG_CODE_AND_MESSAGE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_MESSAGING)
    public ResponseEntity<GradMessaging> getSpecificGradMessagingCode(@PathVariable String pgmCode,@PathVariable String msgType) { 
    	logger.debug("getSpecificGradMessagingCode : ");
    	GradMessaging gradResponse = codeService.getSpecificGradMessagingCode(pgmCode,msgType);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	} 
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CAREER_PROGRAM)
    public ResponseEntity<List<GradCareerProgram>> getAllCareerPrograms() { 
    	logger.debug("getAllPrograms : ");
        return response.GET(codeService.getAllCareerProgramCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CAREER_PROGRAM)
    public ResponseEntity<GradCareerProgram> getSpecificCareerProgramCode(@PathVariable String cpCode) { 
    	logger.debug("getSpecificCareerProgramCode : ");
    	GradCareerProgram gradResponse = codeService.getSpecificCareerProgramCode(cpCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_STATUS_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_STATUS_CODE)
    public ResponseEntity<List<GradStatusCodes>> getAllStatusCodeList() { 
    	logger.debug("getAllStatusCodeList : ");
        return response.GET(codeService.getAllGradStatusCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_STATUS_CODE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_STATUS_CODE)
    public ResponseEntity<GradStatusCodes> getSpecificStatusCode(@PathVariable String statusCode) { 
    	logger.debug("getSpecificStatusCode : ");
    	GradStatusCodes gradResponse = codeService.getSpecificGradStatusCode(statusCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_PROGRAM_TYPE_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM_TYPE_CODE)
    public ResponseEntity<List<GradProgramTypes>> getAllProgramTypeCodeList() { 
    	logger.debug("getAllProgramTypeCodeList : ");
        return response.GET(codeService.getAllProgramTypeCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_PROGRAM_TYPE_CODE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM_TYPE_CODE)
    public ResponseEntity<GradProgramTypes> getSpecificProgramTypeCode(@PathVariable String typeCode) { 
    	logger.debug("getSpecificProgramTypeCode : ");
    	GradProgramTypes gradResponse = codeService.getSpecificProgramTypeCode(typeCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_REQUIREMENT_TYPE_CODE)
    public ResponseEntity<List<GradRequirementTypes>> getAllRequirementTypeCodeList() { 
    	logger.debug("getAllRequirementTypeCodeList : ");
        return response.GET(codeService.getAllRequirementTypeCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_REQUIREMENT_TYPE_CODE)
    public ResponseEntity<GradRequirementTypes> getSpecificRequirementTypeCode(@PathVariable String typeCode) { 
    	logger.debug("getSpecificRequirementTypeCode : ");
    	GradRequirementTypes gradResponse = codeService.getSpecificRequirementTypeCode(typeCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
}
