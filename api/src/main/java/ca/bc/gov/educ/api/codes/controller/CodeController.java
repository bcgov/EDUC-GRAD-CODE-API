package ca.bc.gov.educ.api.codes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.service.CodeService;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;

@CrossOrigin
@RestController
@RequestMapping(EducGradCodeApiConstants.GRAD_CODE_API_ROOT_MAPPING)
public class CodeController {

    private static Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeService codeService;

    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_MAPPING)
    public List<GradProgram> getAllPrograms() { 
    	logger.debug("getAllPrograms : ");
        return codeService.getAllProgramList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROGRAM_BY_CODE_MAPPING)
    public GradProgram getSpecificProgramCode(@PathVariable String programCode) { 
    	logger.debug("getSpecificProgramCode : ");
        return codeService.getSpecificProgramCode(programCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_MAPPING)
    public List<GradCountry> getAllCountryCodeList() { 
    	logger.debug("getAllCountryCodeList : ");
        return codeService.getAllCountryCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_COUNTRY_BY_CODE_MAPPING)
    public GradCountry getSpecificCountryCode(@PathVariable String countryCode) { 
    	logger.debug("getSpecificCountryCode : ");
        return codeService.getSpecificCountryCode(countryCode);
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_MAPPING)
    public List<GradProvince> getAllProvinceCodeList() { 
    	logger.debug("getAllProvinceCodeList : ");
        return codeService.getAllProvinceCodeList();
    }
    
    @GetMapping(EducGradCodeApiConstants.GET_ALL_PROVINCE_BY_CODE_MAPPING)
    public GradProvince getSpecificProvinceCode(@PathVariable String provinceCode) { 
    	logger.debug("getSpecificProvinceCode : ");
        return codeService.getSpecificProvinceCode(provinceCode);
    }
}
