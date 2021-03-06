package ca.bc.gov.educ.api.codes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import ca.bc.gov.educ.api.codes.model.dto.GradCareerProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradReportTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradRequirementTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.model.dto.StudentStatus;
import ca.bc.gov.educ.api.codes.model.entity.GradCareerProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCertificateTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCountryEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradMessagingEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProvinceEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradReportTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradRequirementTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradUngradReasonsEntity;
import ca.bc.gov.educ.api.codes.model.entity.StudentStatusEntity;
import ca.bc.gov.educ.api.codes.model.transformer.GradCareerProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCertificateTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCountryTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradMessagingTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProvinceTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradReportTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradRequirementTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradUngradReasonsTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.StudentStatusTransformer;
import ca.bc.gov.educ.api.codes.repository.GradCareerProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradCertificateTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradCountryRepository;
import ca.bc.gov.educ.api.codes.repository.GradMessagingRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradProvinceRepository;
import ca.bc.gov.educ.api.codes.repository.GradReportTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradRequirementTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradUngradReasonsRepository;
import ca.bc.gov.educ.api.codes.repository.StudentStatusRepository;
import ca.bc.gov.educ.api.codes.util.EducGradCodeApiConstants;
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
	private GradRequirementTypesRepository gradRequirementTypesRepository;

	@Autowired
	private GradRequirementTypesTransformer gradRequirementTypesTransformer;
	
	@Autowired
	private GradReportTypesRepository gradReportTypesRepository;

	@Autowired
	private GradReportTypesTransformer gradReportTypesTransformer;
	
	@Autowired
	GradValidation validation;
	
	@Autowired
	private EducGradCodeApiConstants educGradCodeApiConstants;	
	
	@Autowired
	private StudentStatusRepository studentStatusRepository;

	@Autowired
	private StudentStatusTransformer studentStatusTransformer;
	
	@Autowired
    WebClient webClient;
    
    @Autowired
    RestTemplate restTemplate;

	private static Logger logger = LoggerFactory.getLogger(CodeService.class);
	private static final String EXCEPTION_MSG = "Exception: %s";
	private static final String CREATED_BY="createdBy";
	private static final String CREATED_TIMESTAMP="createdTimestamp";

	/**
	 * Get all Programs in Grad Program DTO
	 *
	 * @return GradProgram
	 * @throws java.lang.Exception
	 */
	@Transactional
	public List<GradProgram> getAllProgramList() {
		return gradProgramTransformer.transformToDTO(gradProgramRepository.findAll());
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
		return gradCountryTransformer.transformToDTO(gradCountryRepository.findAll());
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
		return gradProvinceTransformer.transformToDTO(gradProvinceRepository.findAll());
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
		return gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.findAll());
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
		return gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.findAll());
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
		return gradMessagingTransformer.transformToDTO(gradMessagingRepository.findAll());
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
		List<GradCareerProgram> gradCareerProgramList = new ArrayList<>();
		try {
			gradCareerProgramList = gradCareerProgramTransformer.transformToDTO(gradCareerProgramRepository.findAll());
		} catch (Exception e) {
			logger.debug(String.format(EXCEPTION_MSG,e));
		}
		Collections.sort(gradCareerProgramList, Comparator.comparing(GradCareerProgram::getCode));
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
	public List<GradRequirementTypes> getAllRequirementTypeCodeList() {
		return gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.findAll());
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
			BeanUtils.copyProperties(sourceObject,gradEnity,CREATED_BY,CREATED_TIMESTAMP);
    		return gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Reason Code [%s] does not exists",gradUngradReasons.getCode()));
			return gradUngradReasons;
		}
	}

	public int deleteGradUngradReasons(@Valid String reasonCode,String accessToken) {
		Boolean isPresent = webClient.get()
				.uri(String.format(educGradCodeApiConstants.getGradStudentUngradReasonByUngradReasonCode(),reasonCode))
				.headers(h -> h.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(boolean.class)
				.block();
		if(isPresent) {
			validation.addErrorAndStop(
					String.format("This Ungrad Reason [%s] cannot be deleted as some students have this reason associated with them.",reasonCode));
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
			BeanUtils.copyProperties(sourceObject,gradEnity,CREATED_BY,CREATED_TIMESTAMP);
    		return gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Certificate Type [%s] does not exists",gradCertificateTypes.getCode()));
			return gradCertificateTypes;
		}
	}

	public int deleteGradCertificateTypes(@Valid String certificateType,String accessToken) {
		Boolean isPresent = webClient.get()
				.uri(String.format(educGradCodeApiConstants.getGradStudentCertificateByCertificateTypeCode(),certificateType))
				.headers(h -> h.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(boolean.class)
				.block();
		if(isPresent) {
			validation.addErrorAndStop(
					String.format("This Certificate Type [%s] cannot be deleted as some students have this type associated with them.",certificateType));
			return 0;
		}else {
			gradCertificateTypesRepository.deleteById(certificateType);
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
			BeanUtils.copyProperties(sourceObject,gradEnity,CREATED_BY,CREATED_TIMESTAMP);
    		return gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Requirement Type [%s] does not exists",gradRequirementTypes.getCode()));
			return gradRequirementTypes;
		}
	}

	public int deleteGradRequirementTypes(@Valid String programType, String accessToken) {
		Boolean isPresent = webClient.get()
				.uri(String.format(educGradCodeApiConstants.getGradRequirementTypeByRequirementTypeCode(),programType))
				.headers(h -> h.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(boolean.class)
				.block();
		if(isPresent) {
			validation.addErrorAndStop(
					String.format("This Requirement Type [%s] cannot be deleted as some rules are of this type.",programType));
			return 0;
		}else {
			gradRequirementTypesRepository.deleteById(programType);
			return 1;
		}
	}
	
	@Transactional
	public List<GradReportTypes> getAllReportTypeCodeList() {
		return gradReportTypesTransformer.transformToDTO(gradReportTypesRepository.findAll());
	}

	@Transactional
	public GradReportTypes getSpecificReportTypeCode(String provCode) {
		Optional<GradReportTypesEntity> entity = gradReportTypesRepository.findById(StringUtils.toRootUpperCase(provCode));
		if (entity.isPresent()) {
			return gradReportTypesTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}
	
	public GradReportTypes createGradReportTypes(@Valid GradReportTypes gradReportTypes) {
		GradReportTypesEntity toBeSavedObject = gradReportTypesTransformer.transformToEntity(gradReportTypes);
		Optional<GradReportTypesEntity> existingObjectCheck = gradReportTypesRepository.findById(gradReportTypes.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Report Type [%s] already exists",gradReportTypes.getCode()));
			return gradReportTypes;			
		}else {
			return gradReportTypesTransformer.transformToDTO(gradReportTypesRepository.save(toBeSavedObject));
		}	
	}

	public GradReportTypes updateGradReportTypes(@Valid GradReportTypes gradReportTypes) {
		Optional<GradReportTypesEntity> gradReportTypesOptional = gradReportTypesRepository.findById(gradReportTypes.getCode());
		GradReportTypesEntity sourceObject = gradReportTypesTransformer.transformToEntity(gradReportTypes);
		if(gradReportTypesOptional.isPresent()) {
			GradReportTypesEntity gradEnity = gradReportTypesOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,CREATED_BY,CREATED_TIMESTAMP);
    		return gradReportTypesTransformer.transformToDTO(gradReportTypesRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Report Type [%s] does not exists",gradReportTypes.getCode()));
			return gradReportTypes;
		}
	}

	public int deleteGradReportTypes(@Valid String reportType,String accessToken) {
		Boolean isPresent = webClient.get()
				.uri(String.format(educGradCodeApiConstants.getGradReportTypeByReportTypeCode(),reportType))
				.headers(h -> h.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(boolean.class)
				.block();
		if(isPresent) {
			validation.addErrorAndStop(
					String.format("This Report Type [%s] cannot be deleted as some students have this type associated with them.",reportType));
			return 0;
		}else {
			gradReportTypesRepository.deleteById(reportType);
			return 1;
		}
	}
	
	@Transactional
	public List<StudentStatus> getAllStudentStatusCodeList() {
		return studentStatusTransformer.transformToDTO(studentStatusRepository.findAll());
	}

	@Transactional
	public StudentStatus getSpecificStudentStatusCode(String statusCode) {
		Optional<StudentStatusEntity> entity = studentStatusRepository.findById(StringUtils.toRootUpperCase(statusCode));
		if (entity.isPresent()) {
			return studentStatusTransformer.transformToDTO(entity);
		} else {
			return null;
		}
	}

	public StudentStatus createStudentStatus(@Valid StudentStatus studentStatus) {
		StudentStatusEntity toBeSavedObject = studentStatusTransformer.transformToEntity(studentStatus);
		Optional<StudentStatusEntity> existingObjectCheck = studentStatusRepository.findById(studentStatus.getCode());
		if(existingObjectCheck.isPresent()) {
			validation.addErrorAndStop(String.format("Student Status Code [%s] already exists",studentStatus.getCode()));
			return studentStatus;			
		}else {
			return studentStatusTransformer.transformToDTO(studentStatusRepository.save(toBeSavedObject));
		}	
	}

	public StudentStatus updateStudentStatus(@Valid StudentStatus studentStatus) {
		Optional<StudentStatusEntity> studentStatusOptional = studentStatusRepository.findById(studentStatus.getCode());
		StudentStatusEntity sourceObject = studentStatusTransformer.transformToEntity(studentStatus);
		if(studentStatusOptional.isPresent()) {
			StudentStatusEntity gradEnity = studentStatusOptional.get();			
			BeanUtils.copyProperties(sourceObject,gradEnity,CREATED_BY,CREATED_TIMESTAMP);
    		return studentStatusTransformer.transformToDTO(studentStatusRepository.save(gradEnity));
		}else {
			validation.addErrorAndStop(String.format("Student Status Code [%s] does not exists",studentStatus.getCode()));
			return studentStatus;
		}
	}

	public int deleteStudentStatus(@Valid String statusCode,String accessToken) {
		Boolean isPresent = webClient.get()
				.uri(String.format(educGradCodeApiConstants.getGradStudentStatusByStatusCode(),statusCode))
				.headers(h -> h.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(boolean.class)
				.block();
		if(isPresent) {
			validation.addErrorAndStop(
					String.format("This Student Status [%s] cannot be deleted as some students have this status associated with them.",statusCode));
			return 0;
		}else {
			studentStatusRepository.deleteById(statusCode);
			return 1;
		}
		
	}
}
