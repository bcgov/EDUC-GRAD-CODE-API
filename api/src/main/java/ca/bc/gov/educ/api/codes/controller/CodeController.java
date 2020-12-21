package ca.bc.gov.educ.api.codes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradStatusCodes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.service.CodeService;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;
import ca.bc.gov.educ.api.codes.util.PermissionsContants;
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

    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM)
    public List<GradProgram> getAllPrograms() { 
    	logger.debug("getAllPrograms : ");
        return codeService.getAllProgramList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROGRAM)
    public GradProgram getSpecificProgramCode(@PathVariable String programCode) { 
    	logger.debug("getSpecificProgramCode : ");
        return codeService.getSpecificProgramCode(programCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_COUNTRY)
    public List<GradCountry> getAllCountryCodeList() { 
    	logger.debug("getAllCountryCodeList : ");
        return codeService.getAllCountryCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_COUNTRY)
    public GradCountry getSpecificCountryCode(@PathVariable String countryCode) { 
    	logger.debug("getSpecificCountryCode : ");
        return codeService.getSpecificCountryCode(countryCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROVINCE)
    public List<GradProvince> getAllProvinceCodeList() { 
    	logger.debug("getAllProvinceCodeList : ");
        return codeService.getAllProvinceCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_PROVINCE)
    public GradProvince getSpecificProvinceCode(@PathVariable String provinceCode) { 
    	logger.debug("getSpecificProvinceCode : ");
        return codeService.getSpecificProvinceCode(provinceCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_UNGRAD)
    public List<GradUngradReasons> getAllUngradReasonCodeList() { 
    	logger.debug("getAllUngradReasonCodeList : ");
        return codeService.getAllUngradReasonCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_UNGRAD_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_UNGRAD)
    public GradUngradReasons getSpecificUngradReasonCode(@PathVariable String reasonCode) { 
    	logger.debug("getSpecificUngradReasonCode : ");
        return codeService.getSpecificUngradReasonCode(reasonCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CERTIFICATE)
    public List<GradCertificateTypes> getAllCertificateTypeCodeList() { 
    	logger.debug("getAllCertificateTypeCodeList : ");
        return codeService.getAllCertificateTypeCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_CERTIFICATE_TYPE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CERTIFICATE)
    public GradCertificateTypes getSpecificCertificateTypeCode(@PathVariable String certTypeCode) { 
    	logger.debug("getSpecificCertificateTypeCode : ");
        return codeService.getSpecificCertificateTypeCode(certTypeCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_MESSAGING_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_MESSAGING)
    public List<GradMessaging> getAllGradMessagingList() { 
    	logger.debug("getAllGradMessagingList : ");
        return codeService.getAllGradMessagingList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_MESSAGING_BY_PRG_CODE_AND_MESSAGE_TYPE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_MESSAGING)
    public GradMessaging getSpecificGradMessagingCode(@PathVariable String pgmCode,@PathVariable String msgType) { 
    	logger.debug("getSpecificGradMessagingCode : ");
        return codeService.getSpecificGradMessagingCode(pgmCode,msgType);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CAREER_PROGRAM)
    public List<GradCareerProgram> getAllCareerPrograms() { 
    	logger.debug("getAllPrograms : ");
        return codeService.getAllCareerProgramCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_CAREER_PROGRAM_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_CAREER_PROGRAM)
    public GradCareerProgram getSpecificCareerProgramCode(@PathVariable String cpCode) { 
    	logger.debug("getSpecificCareerProgramCode : ");
        return codeService.getSpecificCareerProgramCode(cpCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_STATUS_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_STATUS_CODE)
    public List<GradStatusCodes> getAllStatusCodeList() { 
    	logger.debug("getAllStatusCodeList : ");
        return codeService.getAllGradStatusCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_GRAD_STATUS_CODE_BY_CODE_MAPPING)
    @PreAuthorize(PermissionsContants.READ_GRAD_STATUS_CODE)
    public GradStatusCodes getSpecificStatusCode(@PathVariable String statusCode) { 
    	logger.debug("getSpecificStatusCode : ");
        return codeService.getSpecificGradStatusCode(statusCode);
    }
}
