package ca.bc.gov.educ.api.codes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.educ.api.codes.model.dto.GradCareerProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProgramTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradRequirementTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.model.entity.GradCareerProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCertificateTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCountryEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradMessagingEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProvinceEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradRequirementTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradUngradReasonsEntity;
import ca.bc.gov.educ.api.codes.model.transformer.GradCareerProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCertificateTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCountryTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradMessagingTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProvinceTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradRequirementTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradUngradReasonsTransformer;
import ca.bc.gov.educ.api.codes.repository.GradCareerProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradCertificateTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradCountryRepository;
import ca.bc.gov.educ.api.codes.repository.GradMessagingRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradProvinceRepository;
import ca.bc.gov.educ.api.codes.repository.GradRequirementTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradUngradReasonsRepository;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiUtils;
import ca.bc.gov.educ.api.codes.util.GradValidation;

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

	@Autowired
	private GradMessagingRepository gradMessagingRepository;

	@Autowired
	private GradMessagingTransformer gradMessagingTransformer;

	@Autowired
	private GradCareerProgramRepository gradCareerProgramRepository;

	@Autowired
	private GradCareerProgramTransformer gradCareerProgramTransformer;

	@Autowired
	private GradProgramTypesRepository gradProgramTypesRepository;

	@Autowired
	private GradProgramTypesTransformer gradProgramTypesTransformer;
	
	@Autowired
	private GradRequirementTypesRepository gradRequirementTypesRepository;

	@Autowired
	private GradRequirementTypesTransformer gradRequirementTypesTransformer;
	
	@Autowired
	GradValidation validation;
	
	@Value(EducGradCodeApiConstants.ENDPOINT_GRAD_STATUS_BY_CERTIFICATE_TYPE_CODE_URL)
    private String getGradStatusByCertificateTypeCodeURL;  
	
	@Value(EducGradCodeApiConstants.ENDPOINT_STUDENT_UNGRAD_REASON_BY_UNGRAD_REASON_CODE_URL)
    private String getStudentUngradReasonByUngradReasonCodeURL;
	
	@Value(EducGradCodeApiConstants.ENDPOINT_STUDENT_CAREER_PROGRAM_BY_CAREER_PROGRAM_CODE_URL)
    private String getStudentCareerProgramByCareerProgramCodeURL; 
	
	@Value(EducGradCodeApiConstants.ENDPOINT_PROGRAM_TYPE_BY_PROGRAM_TYPE_CODE_URL)
    private String getProgramTypeByProgramTypeCodeURL; 
	
	@Value(EducGradCodeApiConstants.ENDPOINT_REQUIREMENT_TYPE_BY_REQUIREMENT_TYPE_CODE_URL)
    private String getRequirementTypeByRequirementTypeCodeURL; 
	
	
    
    @Autowired
    RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(CodeService.class);

	/**
	 * Get all Programs in Grad Program DTO
	 *
	 * @return GradProgram
	 * @throws java.lang.Exception
	 */
	@Transactional
	public List<GradProgram> getAllProgramList() {
		List<GradProgram> programList = new ArrayList<GradProgram>();
		try {
			programList = gradProgramTransformer.transformToDTO(gradProgramRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return programList;
	}

	@Transactional
	public GradProgram getSpecificProgramCode(String pgmCode) {
		Optional<GradProgramEntity> entity = gradProgramRepository.findById(StringUtils.toRootUpperCase(pgmCode));
		if (entity.isPresent()) {
			return gradProgramTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradCountry> getAllCountryCodeList() {
		List<GradCountry> gradSpecialCaseList = new ArrayList<GradCountry>();
		try {
			gradSpecialCaseList = gradCountryTransformer.transformToDTO(gradCountryRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradSpecialCaseList;
	}

	@Transactional
	public GradCountry getSpecificCountryCode(String countryCode) {
		Optional<GradCountryEntity> entity = gradCountryRepository.findById(StringUtils.toRootUpperCase(countryCode));
		if (entity.isPresent()) {
			return gradCountryTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradProvince> getAllProvinceCodeList() {
		List<GradProvince> gradLetterGradeList = new ArrayList<GradProvince>();
		try {
			gradLetterGradeList = gradProvinceTransformer.transformToDTO(gradProvinceRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradLetterGradeList;
	}

	@Transactional
	public GradProvince getSpecificProvinceCode(String provCode) {
		Optional<GradProvinceEntity> entity = gradProvinceRepository.findById(StringUtils.toRootUpperCase(provCode));
		if (entity.isPresent()) {
			return gradProvinceTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradUngradReasons> getAllUngradReasonCodeList() {
		List<GradUngradReasons> gradUngradReasonList = new ArrayList<GradUngradReasons>();
		try {
			gradUngradReasonList = gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradUngradReasonList;
	}

	@Transactional
	public GradUngradReasons getSpecificUngradReasonCode(String provCode) {
		Optional<GradUngradReasonsEntity> entity = gradUngradReasonsRepository.findById(StringUtils.toRootUpperCase(provCode));
		if (entity.isPresent()) {
			return gradUngradReasonsTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradCertificateTypes> getAllCertificateTypeCodeList() {
		List<GradCertificateTypes> gradCertificateTypeList = new ArrayList<GradCertificateTypes>();
		try {
			gradCertificateTypeList = gradCertificateTypesTransformer
					.transformToDTO(gradCertificateTypesRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradCertificateTypeList;
	}

	@Transactional
	public GradCertificateTypes getSpecificCertificateTypeCode(String provCode) {
		Optional<GradCertificateTypesEntity> entity = gradCertificateTypesRepository.findById(StringUtils.toRootUpperCase(provCode));
		if (entity.isPresent()) {
			return gradCertificateTypesTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	public List<GradMessaging> getAllGradMessagingList() {
		List<GradMessaging> gradMessagingList = new ArrayList<GradMessaging>();
		try {
			gradMessagingList = gradMessagingTransformer.transformToDTO(gradMessagingRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradMessagingList;
	}

	public GradMessaging getSpecificGradMessagingCode(String pgmCode, String msgType) {
		Optional<GradMessagingEntity> entity = gradMessagingRepository.findByProgramCodeAndMessageType(pgmCode,
				msgType);
		if (entity.isPresent()) {
			return gradMessagingTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradCareerProgram> getAllCareerProgramCodeList() {
		List<GradCareerProgram> gradCareerProgramList = new ArrayList<GradCareerProgram>();
		try {
			gradCareerProgramList = gradCareerProgramTransformer.transformToDTO(gradCareerProgramRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradCareerProgramList;
	}

	@Transactional
	public GradCareerProgram getSpecificCareerProgramCode(String cpc) {
		Optional<GradCareerProgramEntity> entity = gradCareerProgramRepository
				.findById(StringUtils.toRootUpperCase(cpc));
		if (entity.isPresent()) {
			return gradCareerProgramTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	@Transactional
	public List<GradProgramTypes> getAllProgramTypeCodeList() {
		List<GradProgramTypes> gradProgramTypesList = new ArrayList<GradProgramTypes>();
		try {
			gradProgramTypesList = gradProgramTypesTransformer.transformToDTO(gradProgramTypesRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradProgramTypesList;
	}

	@Transactional
	public GradProgramTypes getSpecificProgramTypeCode(String typeCode) {
		Optional<GradProgramTypesEntity> entity = gradProgramTypesRepository
				.findById(StringUtils.toRootUpperCase(typeCode));
		if (entity.isPresent()) {
			return gradProgramTypesTransformer.transformToDTO(entity.get());
		} else {
			return null;
		}
	}
	
	@Transactional
	public List<GradRequirementTypes> getAllRequirementTypeCodeList() {
		List<GradRequirementTypes> gradRequirementTypesList = new ArrayList<GradRequirementTypes>();
		try {
			gradRequirementTypesList = gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradRequirementTypesList;
	}

	@Transactional
	public GradRequirementTypes getSpecificRequirementTypeCode(String typeCode) {
		Optional<GradRequirementTypesEntity> entity = gradRequirementTypesRepository
				.findById(StringUtils.toRootUpperCase(typeCode));
		if (entity.isPresent()) {
			return gradRequirementTypesTransformer.transformToDTO(entity.get());
		} else {
			return null;
		}
	}

	public GradUngradReasons createGradUngradReasons(@Valid GradUngradReasons gradUngradReasons) {
		GradUngradReasonsEntity toBeSavedObject = gradUngradReasonsTransformer.transformToEntity(gradUngradReasons);
		Optional<GradUngradReasonsEntity> existingObjectCheck = gradUngradReasonsRepository.findById(gradUngradReasons.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Reason Code [%s] already exists",gradUngradReasons.getCode()));
			return gradUngradReasons;			
		}else {
			return gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.save(toBeSavedObject));
		}	
	}

	public GradUngradReasons updateGradUngradReasons(@Valid GradUngradReasons gradUngradReasons) {
		Optional<GradUngradReasonsEntity> gradUngradReasonOptional = gradUngradReasonsRepository.findById(gradUngradReasons.getCode());
		GradUngradReasonsEntity sourceObject = gradUngradReasonsTransformer.transformToEntity(gradUngradReasons);
		if(gradUngradReasonOptional.isPresent()) {
			GradUngradReasonsEntity gradEnity = gradUngradReasonOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,"createdBy","createdTimestamp");
    		return gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Reason Code [%s] does not exists",gradUngradReasons.getCode()));
			return gradUngradReasons;
		}
	}

	public int deleteGradUngradReasons(@Valid String reasonCode,String accessToken) {
		HttpHeaders httpHeaders = EducGradCodeApiUtils.getHeaders(accessToken);
		Boolean isPresent = restTemplate.exchange(String.format(getStudentUngradReasonByUngradReasonCodeURL,reasonCode), HttpMethod.GET,
				new HttpEntity<>(httpHeaders), boolean.class).getBody();
		if(isPresent) {
			validation.addErrorAndStop(String.format("This Ungrad Reason [%s] cannot be deleted as some students have this reason associated with them.",reasonCode));
			return 0;
		}else {
			gradUngradReasonsRepository.deleteById(reasonCode);
			return 1;
		}
		
	}
	
	public GradCertificateTypes createGradCertificateTypes(@Valid GradCertificateTypes gradCertificateTypes) {
		GradCertificateTypesEntity toBeSavedObject = gradCertificateTypesTransformer.transformToEntity(gradCertificateTypes);
		Optional<GradCertificateTypesEntity> existingObjectCheck = gradCertificateTypesRepository.findById(gradCertificateTypes.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Certificate Type [%s] already exists",gradCertificateTypes.getCode()));
			return gradCertificateTypes;			
		}else {
			return gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.save(toBeSavedObject));
		}	
	}

	public GradCertificateTypes updateGradCertificateTypes(@Valid GradCertificateTypes gradCertificateTypes) {
		Optional<GradCertificateTypesEntity> gradCertificateTypesOptional = gradCertificateTypesRepository.findById(gradCertificateTypes.getCode());
		GradCertificateTypesEntity sourceObject = gradCertificateTypesTransformer.transformToEntity(gradCertificateTypes);
		if(gradCertificateTypesOptional.isPresent()) {
			GradCertificateTypesEntity gradEnity = gradCertificateTypesOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,"createdBy","createdTimestamp");
    		return gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Certificate Type [%s] does not exists",gradCertificateTypes.getCode()));
			return gradCertificateTypes;
		}
	}

	public int deleteGradCertificateTypes(@Valid String certificateType,String accessToken) {
		HttpHeaders httpHeaders = EducGradCodeApiUtils.getHeaders(accessToken);
		Boolean isPresent = restTemplate.exchange(String.format(getGradStatusByCertificateTypeCodeURL,certificateType), HttpMethod.GET,
				new HttpEntity<>(httpHeaders), boolean.class).getBody();
		if(isPresent) {
			validation.addErrorAndStop(String.format("This Certificate Type [%s] cannot be deleted as some students have this type associated with them.",certificateType));
			return 0;
		}else {
			gradCertificateTypesRepository.deleteById(certificateType);
			return 1;
		}
	}

	public GradCareerProgram createGradCareerProgram(@Valid GradCareerProgram gradCareerProgram) {
		GradCareerProgramEntity toBeSavedObject = gradCareerProgramTransformer.transformToEntity(gradCareerProgram);
		Optional<GradCareerProgramEntity> existingObjectCheck = gradCareerProgramRepository.findById(gradCareerProgram.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Career Program [%s] already exists",gradCareerProgram.getCode()));
			return gradCareerProgram;			
		}else {
			return gradCareerProgramTransformer.transformToDTO(gradCareerProgramRepository.save(toBeSavedObject));
		}
	}

	public GradCareerProgram updateGradCareerProgram(@Valid GradCareerProgram gradCareerProgram) {
		Optional<GradCareerProgramEntity> gradCareerProgramOptional = gradCareerProgramRepository.findById(gradCareerProgram.getCode());
		GradCareerProgramEntity sourceObject = gradCareerProgramTransformer.transformToEntity(gradCareerProgram);
		if(gradCareerProgramOptional.isPresent()) {
			GradCareerProgramEntity gradEnity = gradCareerProgramOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,"createdBy","createdTimestamp");
    		return gradCareerProgramTransformer.transformToDTO(gradCareerProgramRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Career Program [%s] does not exists",gradCareerProgram.getCode()));
			return gradCareerProgram;
		}
	}

	public int deleteGradCareerProgram(@Valid String cpCode, String accessToken) {
		HttpHeaders httpHeaders = EducGradCodeApiUtils.getHeaders(accessToken);
		Boolean isPresent = restTemplate.exchange(String.format(getStudentCareerProgramByCareerProgramCodeURL,cpCode), HttpMethod.GET,
				new HttpEntity<>(httpHeaders), boolean.class).getBody();
		if(isPresent) {
			validation.addErrorAndStop(String.format("This Career Program [%s] cannot be deleted as some students have this code associated with them.",cpCode));
			return 0;
		}else {
			gradCareerProgramRepository.deleteById(cpCode);
			return 1;
		}
	}
	
	public GradProgramTypes createGradProgramTypes(@Valid GradProgramTypes gradProgramTypes) {
		GradProgramTypesEntity toBeSavedObject = gradProgramTypesTransformer.transformToEntity(gradProgramTypes);
		Optional<GradProgramTypesEntity> existingObjectCheck = gradProgramTypesRepository.findById(gradProgramTypes.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Program Type [%s] already exists",gradProgramTypes.getCode()));
			return gradProgramTypes;			
		}else {
			return gradProgramTypesTransformer.transformToDTO(gradProgramTypesRepository.save(toBeSavedObject));
		}
	}

	public GradProgramTypes updateGradProgramTypes(@Valid GradProgramTypes gradProgramTypes) {
		Optional<GradProgramTypesEntity> gradProgramTypesOptional = gradProgramTypesRepository.findById(gradProgramTypes.getCode());
		GradProgramTypesEntity sourceObject = gradProgramTypesTransformer.transformToEntity(gradProgramTypes);
		if(gradProgramTypesOptional.isPresent()) {
			GradProgramTypesEntity gradEnity = gradProgramTypesOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,"createdBy","createdTimestamp");
    		return gradProgramTypesTransformer.transformToDTO(gradProgramTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Program Type [%s] does not exists",gradProgramTypes.getCode()));
			return gradProgramTypes;
		}
	}

	public int deleteGradProgramTypes(@Valid String programType, String accessToken) {
		HttpHeaders httpHeaders = EducGradCodeApiUtils.getHeaders(accessToken);
		Boolean isPresent = restTemplate.exchange(String.format(getProgramTypeByProgramTypeCodeURL,programType), HttpMethod.GET,
				new HttpEntity<>(httpHeaders), boolean.class).getBody();
		if(isPresent) {
			validation.addErrorAndStop(String.format("This Program Type [%s] cannot be deleted as some programs are of this type.",programType));
			return 0;
		}else {
			gradProgramTypesRepository.deleteById(programType);
			return 1;
		}
	}
	
	public GradRequirementTypes createGradRequirementTypes(@Valid GradRequirementTypes gradRequirementTypes) {
		GradRequirementTypesEntity toBeSavedObject = gradRequirementTypesTransformer.transformToEntity(gradRequirementTypes);
		Optional<GradRequirementTypesEntity> existingObjectCheck = gradRequirementTypesRepository.findById(gradRequirementTypes.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Requirement Type [%s] already exists",gradRequirementTypes.getCode()));
			return gradRequirementTypes;			
		}else {
			return gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.save(toBeSavedObject));
		}
	}

	public GradRequirementTypes updateGradRequirementTypes(@Valid GradRequirementTypes gradRequirementTypes) {
		Optional<GradRequirementTypesEntity> gradRequirementTypesOptional = gradRequirementTypesRepository.findById(gradRequirementTypes.getCode());
		GradRequirementTypesEntity sourceObject = gradRequirementTypesTransformer.transformToEntity(gradRequirementTypes);
		if(gradRequirementTypesOptional.isPresent()) {
			GradRequirementTypesEntity gradEnity = gradRequirementTypesOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,"createdBy","createdTimestamp");
    		return gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Requirement Type [%s] does not exists",gradRequirementTypes.getCode()));
			return gradRequirementTypes;
		}
	}

	public int deleteGradRequirementTypes(@Valid String programType, String accessToken) {
		HttpHeaders httpHeaders = EducGradCodeApiUtils.getHeaders(accessToken);
		Boolean isPresent = restTemplate.exchange(String.format(getRequirementTypeByRequirementTypeCodeURL,programType), HttpMethod.GET,
				new HttpEntity<>(httpHeaders), boolean.class).getBody();
		if(isPresent) {
			validation.addErrorAndStop(String.format("This Requirement Type [%s] cannot be deleted as some rules are of this type.",programType));
			return 0;
		}else {
			gradRequirementTypesRepository.deleteById(programType);
			return 1;
		}
	}
}
