package ca.bc.gov.educ.api.codes.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.educ.api.codes.model.dto.GradCareerProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradReportTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradRequirementTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.service.CodeService;
import ca.bc.gov.educ.api.codes.util.ApiResponseModel;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;
import ca.bc.gov.educ.api.codes.util.GradValidation;
import ca.bc.gov.educ.api.codes.util.PermissionsContants;
import ca.bc.gov.educ.api.codes.util.ResponseHelper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(EducGradCodeApiConstants.GRAD_CODE_API_ROOT_MAPPING)
@EnableResourceServer
@CrossOrigin
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
    
    @PostMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_MAPPING)
    @PreAuthorize(PermissionsContants.CREATE_UNGRAD_REASON)
    public ResponseEntity<ApiResponseModel<GradUngradReasons>> createGradUngradReasons(@Valid @RequestBody GradUngradReasons gradUngradReasons) { 
    	logger.debug("createGradUngradReasons : ");
    	validation.requiredField(gradUngradReasons.getCode(), "Reason Code");
    	validation.requiredField(gradUngradReasons.getDescription(), "Reason Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return response.CREATED(codeService.createGradUngradReasons(gradUngradReasons));
    }
    
    @PutMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_MAPPING)
    @PreAuthorize(PermissionsContants.UPDATE_UNGRAD_REASON)
    public ResponseEntity<ApiResponseModel<GradUngradReasons>> updateGradUngradReasons(@Valid @RequestBody GradUngradReasons gradUngradReasons) { 
    	logger.info("updateGradUngradReasons : ");
    	validation.requiredField(gradUngradReasons.getCode(), "Reason Code");
    	validation.requiredField(gradUngradReasons.getDescription(), "Reason Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return response.UPDATED(codeService.updateGradUngradReasons(gradUngradReasons));
    }
    
    @DeleteMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.DELETE_UNGRAD_REASON)
    public ResponseEntity<Void> deleteGradUngradReasons(@Valid @PathVariable String reasonCode) { 
    	logger.debug("deleteGradUngradReasons : ");
    	validation.requiredField(reasonCode, "Reason Code");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails(); 
    	String accessToken = auth.getTokenValue();
        return response.DELETE(codeService.deleteGradUngradReasons(reasonCode,accessToken));
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
    
    @PostMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.CREATE_CERTIFICATE_TYPE)
    public ResponseEntity<ApiResponseModel<GradCertificateTypes>> createGradCertificateTypes(@Valid @RequestBody GradCertificateTypes gradCertificateTypes) { 
    	logger.debug("createGradCertificateTypes : ");
    	validation.requiredField(gradCertificateTypes.getCode(), "Certificate Type Code");
    	validation.requiredField(gradCertificateTypes.getDescription(), "Certificate Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return response.CREATED(codeService.createGradCertificateTypes(gradCertificateTypes));
    }
    
    @PutMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.UPDATE_CERTIFICATE_TYPE)
    public ResponseEntity<ApiResponseModel<GradCertificateTypes>> updateGradCertificateTypes(@Valid @RequestBody GradCertificateTypes gradCertificateTypes) { 
    	logger.info("updateGradCertificateTypes : ");
    	validation.requiredField(gradCertificateTypes.getCode(), "Certificate Type Code");
    	validation.requiredField(gradCertificateTypes.getDescription(), "Certificate Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return response.UPDATED(codeService.updateGradCertificateTypes(gradCertificateTypes));
    }
    
    @DeleteMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.DELETE_CERTIFICATE_TYPE)
    public ResponseEntity<Void> deleteGradCertificateTypes(@Valid @PathVariable String certTypeCode) { 
    	logger.debug("deleteGradCertificateTypes : ");
    	validation.requiredField(certTypeCode, "Certificate Type Code");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails(); 
    	String accessToken = auth.getTokenValue();
        return response.DELETE(codeService.deleteGradCertificateTypes(certTypeCode,accessToken));
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
    
    @PostMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.CREATE_CAREER_PROGRAM)
    public ResponseEntity<ApiResponseModel<GradCareerProgram>> createGradCareerProgram(@Valid @RequestBody GradCareerProgram gradCareerProgram) { 
    	logger.debug("createGradCareerProgram : ");
    	validation.requiredField(gradCareerProgram.getCode(), "Career Program Code");
    	validation.requiredField(gradCareerProgram.getDescription(), "Career Program Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return response.CREATED(codeService.createGradCareerProgram(gradCareerProgram));
    }
    
    @PutMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.UPDATE_CAREER_PROGRAM)
    public ResponseEntity<ApiResponseModel<GradCareerProgram>> updateGradCareerProgram(@Valid @RequestBody GradCareerProgram gradCareerProgram) { 
    	logger.info("updateGradCareerProgram : ");
    	validation.requiredField(gradCareerProgram.getCode(), "Career Program Code");
    	validation.requiredField(gradCareerProgram.getDescription(), "Career Program Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return response.UPDATED(codeService.updateGradCareerProgram(gradCareerProgram));
    }
    
    @DeleteMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.DELETE_CAREER_PROGRAM)
    public ResponseEntity<Void> deleteGradCareerProgram(@Valid @PathVariable String cpCode) { 
    	logger.debug("deleteGradCareerProgram : ");
    	validation.requiredField(cpCode, "Career Program Code");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails(); 
    	String accessToken = auth.getTokenValue();
        return response.DELETE(codeService.deleteGradCareerProgram(cpCode,accessToken));
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
    
    @PostMapping(EducGradCodeApiConstants.GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.CREATE_REQUIREMENT_TYPE)
    public ResponseEntity<ApiResponseModel<GradRequirementTypes>> createGradRequirementTypes(@Valid @RequestBody GradRequirementTypes gradRequirementTypes) { 
    	logger.debug("creatGradRequirementTypess : ");
    	validation.requiredField(gradRequirementTypes.getCode(), "Requirement Type Code");
    	validation.requiredField(gradRequirementTypes.getDescription(), "Requirement Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return response.CREATED(codeService.createGradRequirementTypes(gradRequirementTypes));
    }
    
    @PutMapping(EducGradCodeApiConstants.GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.UPDATE_REQUIREMENT_TYPE)
    public ResponseEntity<ApiResponseModel<GradRequirementTypes>> updateGradRequirementTypes(@Valid @RequestBody GradRequirementTypes gradRequirementTypes) { 
    	logger.info("updateGradRequirementTypes : ");
    	validation.requiredField(gradRequirementTypes.getCode(), "Requirement Type Code");
    	validation.requiredField(gradRequirementTypes.getDescription(), "Requirement Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return response.UPDATED(codeService.updateGradRequirementTypes(gradRequirementTypes));
    }
    
    @DeleteMapping(EducGradCodeApiConstants.GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.DELETE_REQUIREMENT_TYPE)
    public ResponseEntity<Void> deleteGradRequirementTypes(@Valid @PathVariable String typeCode) { 
    	logger.debug("deleteGradRequirementTypes : ");
    	validation.requiredField(typeCode, "Requirement Type Code");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails(); 
    	String accessToken = auth.getTokenValue();
        return response.DELETE(codeService.deleteGradRequirementTypes(typeCode,accessToken));
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_REPORT_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_REPORT)
    public ResponseEntity<List<GradReportTypes>> getAllReportTypeCodeList() { 
    	logger.debug("getAllReportTypeCodeList : ");
        return response.GET(codeService.getAllReportTypeCodeList());
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_REPORT_TYPE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_REPORT)
    public ResponseEntity<GradReportTypes> getSpecificReportTypeCode(@PathVariable String reportTypeCode) { 
    	logger.debug("getSpecificReportTypeCode : ");
    	GradReportTypes gradResponse = codeService.getSpecificReportTypeCode(reportTypeCode);
    	if(gradResponse != null) {
    		return response.GET(gradResponse);
    	}else {
    		return response.NOT_FOUND();
    	}
    }
    
    @PostMapping(EducGradCodeApiConstants.GET_ALL_REPORT_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.CREATE_REPORT_TYPE)
    public ResponseEntity<ApiResponseModel<GradReportTypes>> createGradReportTypes(@Valid @RequestBody GradReportTypes gradReportTypes) { 
    	logger.debug("createGradReportTypes : ");
    	validation.requiredField(gradReportTypes.getCode(), "Report Type Code");
    	validation.requiredField(gradReportTypes.getDescription(), "Report Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        return response.CREATED(codeService.createGradReportTypes(gradReportTypes));
    }
    
    @PutMapping(EducGradCodeApiConstants.GET_ALL_REPORT_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.UPDATE_REPORT_TYPE)
    public ResponseEntity<ApiResponseModel<GradReportTypes>> updateGradReportTypes(@Valid @RequestBody GradReportTypes gradReportTypes) { 
    	logger.info("updateGradReportTypes : ");
    	validation.requiredField(gradReportTypes.getCode(), "Report Type Code");
    	validation.requiredField(gradReportTypes.getDescription(), "Report Type Description");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return response.UPDATED(codeService.updateGradReportTypes(gradReportTypes));
    }
    
    @DeleteMapping(EducGradCodeApiConstants.GET_ALL_REPORT_TYPE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.DELETE_REPORT_TYPE)
    public ResponseEntity<Void> deleteGradReportTypes(@Valid @PathVariable String reportTypeCode) { 
    	logger.debug("deleteGradReportTypes : ");
    	validation.requiredField(reportTypeCode, "Report Type Code");
    	if(validation.hasErrors()) {
    		validation.stopOnErrors();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	OAuth2AuthenticationDetails auth = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails(); 
    	String accessToken = auth.getTokenValue();
        return response.DELETE(codeService.deleteGradReportTypes(reportTypeCode,accessToken));
    }
}
