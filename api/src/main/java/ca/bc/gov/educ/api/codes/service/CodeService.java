package ca.bc.gov.educ.api.codes.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.model.transformer.GradCertificateTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCountryTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProvinceTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradUngradReasonsTransformer;
import ca.bc.gov.educ.api.codes.repository.GradCertificateTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradCountryRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradProvinceRepository;
import ca.bc.gov.educ.api.codes.repository.GradUngradReasonsRepository;


@Service
public class CodeService {

    @Autowired
    private GradProgramRepository gradProgramRepository;  

    @Autowired
    private GradProgramTransformer gradProgramTransformer;
    
    @Autowired
    private GradCountryTransformer gradCountryTransformer;
    
    @Autowired
    private GradProvinceTransformer gradProvinceTransformer;
    
    @Autowired
    private GradCountryRepository gradCountryRepository; 
    
    @Autowired
    private GradProvinceRepository gradProvinceRepository; 
    
    @Autowired
    private GradUngradReasonsRepository gradUngradReasonsRepository;  

    @Autowired
    private GradUngradReasonsTransformer gradUngradReasonsTransformer;
    
    @Autowired
    private GradCertificateTypesRepository gradCertificateTypesRepository;  

    @Autowired
    private GradCertificateTypesTransformer gradCertificateTypesTransformer;

    private static Logger logger = LoggerFactory.getLogger(CodeService.class);

     /**
     * Get all Programs in Grad Program DTO
     *
     * @return GradProgram 
     * @throws java.lang.Exception
     */
    @Transactional
    public List<GradProgram> getAllProgramList() {
        List<GradProgram> programList  = new ArrayList<GradProgram>();
        try {
        	programList = gradProgramTransformer.transformToDTO(gradProgramRepository.findAll());            
        } catch (Exception e) {
            logger.debug("Exception:" + e);
        }

        return programList;
    }
    
    @Transactional
    public GradProgram getSpecificProgramCode(String pgmCode) {
		return gradProgramTransformer.transformToDTO(gradProgramRepository.findById(StringUtils.toRootUpperCase(pgmCode)));
	}
	
    @Transactional
    public List<GradCountry> getAllCountryCodeList() {
	        List<GradCountry> gradSpecialCaseList  = new ArrayList<GradCountry>();
	        try {
	        	gradSpecialCaseList = gradCountryTransformer.transformToDTO(gradCountryRepository.findAll());            
	        } catch (Exception e) {
	            logger.debug("Exception:" + e);
	        }

	        return gradSpecialCaseList;
	    }

    @Transactional
	public GradCountry getSpecificCountryCode(String countryCode) {
		return gradCountryTransformer.transformToDTO(gradCountryRepository.findById(StringUtils.toRootUpperCase(countryCode)));
	}

    @Transactional
	public List<GradProvince> getAllProvinceCodeList() {
		List<GradProvince> gradLetterGradeList  = new ArrayList<GradProvince>();
        try {
        	gradLetterGradeList = gradProvinceTransformer.transformToDTO(gradProvinceRepository.findAll());            
        } catch (Exception e) {
            logger.debug("Exception:" + e);
        }

        return gradLetterGradeList;
	}

    @Transactional
	public GradProvince getSpecificProvinceCode(String provCode) {
		return gradProvinceTransformer.transformToDTO(gradProvinceRepository.findById(StringUtils.toRootUpperCase(provCode)));
	}
    
    @Transactional
	public List<GradUngradReasons> getAllUngradReasonCodeList() {
		List<GradUngradReasons> gradUngradReasonList  = new ArrayList<GradUngradReasons>();
        try {
        	gradUngradReasonList = gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.findAll());            
        } catch (Exception e) {
            logger.debug("Exception:" + e);
        }

        return gradUngradReasonList;
	}

    @Transactional
	public GradUngradReasons getSpecificUngradReasonCode(String provCode) {
		return gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.findById(StringUtils.toRootUpperCase(provCode)));
	}
    
    @Transactional
  	public List<GradCertificateTypes> getAllCertificateTypeCodeList() {
  		List<GradCertificateTypes> gradCertificateTypeList  = new ArrayList<GradCertificateTypes>();
          try {
          	gradCertificateTypeList = gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.findAll());            
          } catch (Exception e) {
              logger.debug("Exception:" + e);
          }

          return gradCertificateTypeList;
  	}

      @Transactional
  	public GradCertificateTypes getSpecificCertificateTypeCode(String provCode) {
  		return gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.findById(StringUtils.toRootUpperCase(provCode)));
  	}
}
