package ca.bc.gov.educ.api.codes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.educ.api.codes.model.dto.GradCareerProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradCountry;
import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.dto.GradProgram;
import ca.bc.gov.educ.api.codes.model.dto.GradProgramTypes;
import ca.bc.gov.educ.api.codes.model.dto.GradProvince;
import ca.bc.gov.educ.api.codes.model.dto.GradStatusCodes;
import ca.bc.gov.educ.api.codes.model.dto.GradUngradReasons;
import ca.bc.gov.educ.api.codes.model.entity.GradCareerProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCertificateTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradCountryEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradMessagingEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramTypesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradProvinceEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradStatusCodesEntity;
import ca.bc.gov.educ.api.codes.model.entity.GradUngradReasonsEntity;
import ca.bc.gov.educ.api.codes.model.transformer.GradCareerProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCertificateTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradCountryTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradMessagingTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProgramTypesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradProvinceTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradStatusCodesTransformer;
import ca.bc.gov.educ.api.codes.model.transformer.GradUngradReasonsTransformer;
import ca.bc.gov.educ.api.codes.repository.GradCareerProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradCertificateTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradCountryRepository;
import ca.bc.gov.educ.api.codes.repository.GradMessagingRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramRepository;
import ca.bc.gov.educ.api.codes.repository.GradProgramTypesRepository;
import ca.bc.gov.educ.api.codes.repository.GradProvinceRepository;
import ca.bc.gov.educ.api.codes.repository.GradStatusCodesRepository;
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

	@Autowired
	private GradMessagingRepository gradMessagingRepository;

	@Autowired
	private GradMessagingTransformer gradMessagingTransformer;

	@Autowired
	private GradCareerProgramRepository gradCareerProgramRepository;

	@Autowired
	private GradCareerProgramTransformer gradCareerProgramTransformer;

	@Autowired
	private GradStatusCodesRepository gradStatusCodesRepository;

	@Autowired
	private GradStatusCodesTransformer gradStatusCodesTransformer;

	@Autowired
	private GradProgramTypesRepository gradProgramTypesRepository;

	@Autowired
	private GradProgramTypesTransformer gradProgramTypesTransformer;

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
	public List<GradStatusCodes> getAllGradStatusCodeList() {
		List<GradStatusCodes> gradStatusCodesList = new ArrayList<GradStatusCodes>();
		try {
			gradStatusCodesList = gradStatusCodesTransformer.transformToDTO(gradStatusCodesRepository.findAll());
		} catch (Exception e) {
			logger.debug("Exception:" + e);
		}

		return gradStatusCodesList;
	}

	@Transactional
	public GradStatusCodes getSpecificGradStatusCode(String statusCode) {
		Optional<GradStatusCodesEntity> entity = gradStatusCodesRepository
				.findById(StringUtils.toRootUpperCase(statusCode));
		if (entity.isPresent()) {
			return gradStatusCodesTransformer.transformToDTO(entity.get());
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
}
