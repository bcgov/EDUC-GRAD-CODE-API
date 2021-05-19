package ca.bc.gov.educ.api.codes.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
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


@ExtendWith(MockitoExtension.class)
@SuppressWarnings("rawtypes")
public class CodeServiceTest {

	@InjectMocks
	private CodeService codeService;
	
	@Mock
	private GradProgramTransformer gradProgramTransformer;
	
	@Mock
	private GradProgramRepository gradProgramRepository;
	
	@Mock
	private GradCountryTransformer gradCountryTransformer;

	@Mock
	private GradProvinceTransformer gradProvinceTransformer;

	@Mock
	private GradCountryRepository gradCountryRepository;

	@Mock
	private GradProvinceRepository gradProvinceRepository;
	
	@Mock
	private GradUngradReasonsRepository gradUngradReasonsRepository;

	@Mock
	private GradUngradReasonsTransformer gradUngradReasonsTransformer;
	
	@Mock
	private GradCertificateTypesRepository gradCertificateTypesRepository;

	@Mock
	private GradCertificateTypesTransformer gradCertificateTypesTransformer;
	
	@Mock
	private GradReportTypesRepository gradReportTypesRepository;

	@Mock
	private GradReportTypesTransformer gradReportTypesTransformer;
	
	@Mock
	private GradMessagingRepository gradMessagingRepository;

	@Mock
	private GradMessagingTransformer gradMessagingTransformer;
	
	@Mock
	private GradCareerProgramRepository gradCareerProgramRepository;

	@Mock
	private GradCareerProgramTransformer gradCareerProgramTransformer;
	

	@Mock
	private GradRequirementTypesRepository gradRequirementTypesRepository;

	@Mock
	private GradRequirementTypesTransformer gradRequirementTypesTransformer;
	
	@Mock
	private StudentStatusRepository studentStatusRepository;

	@Mock
	private StudentStatusTransformer studentStatusTransformer;
	
	@Mock
	GradValidation validation;
	
	@Mock
	WebClient webClient;
	
	
	@Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
	
	@Value(EducGradCodeApiConstants.ENDPOINT_STUDENT_UNGRAD_REASON_BY_UNGRAD_REASON_CODE_URL)
    private String getStudentUngradReasonByUngradReasonCodeURL;
	
	@Test
	public void testGetAllProgramList() {
		List<GradProgram> gradProgramList = new ArrayList<>();
		GradProgram obj = new GradProgram();
		obj.setProgramCode("AB");
		obj.setProgramName("Autobody");
		obj.setProgramStartDate(new Date(System.currentTimeMillis()));
		gradProgramList.add(obj);
		obj = new GradProgram();
		obj.setProgramCode("AC");
		obj.setProgramName("Autobody");
		obj.setProgramStartDate(new Date(System.currentTimeMillis()));
		gradProgramList.add(obj);
		Mockito.when(gradProgramTransformer.transformToDTO(gradProgramRepository.findAll())).thenReturn(gradProgramList);
		codeService.getAllProgramList();
		Mockito.verify(gradProgramTransformer).transformToDTO(gradProgramRepository.findAll());
	}
	
	@Test
	public void testGetSpecificProgramCode() {
		String programCode = "AB";
		GradProgram obj = new GradProgram();
		obj.setProgramCode("AB");
		obj.setProgramName("Autobody");
		obj.setProgramStartDate(new Date(System.currentTimeMillis()));
		GradProgramEntity objEntity = new GradProgramEntity();
		objEntity.setProgramCode("AB");
		objEntity.setProgramName("Autobody");
		objEntity.setProgramStartDate(new Date(System.currentTimeMillis()));
		Optional<GradProgramEntity> ent = Optional.of(objEntity);
		Mockito.when(gradProgramRepository.findById(programCode)).thenReturn(ent);
		codeService.getSpecificProgramCode(programCode);
		Mockito.verify(gradProgramRepository).findById(programCode);
	}
	
	@Test
	public void testGetSpecificProgramCodeReturnsNull() {
		String programCode = "AB";
		Mockito.when(gradProgramRepository.findById(programCode)).thenReturn(Optional.empty());
		codeService.getSpecificProgramCode(programCode);
		Mockito.verify(gradProgramRepository).findById(programCode);
	}
	
	@Test
	public void testGetAllProvinceList() {
		List<GradProvince> gradProvinceList = new ArrayList<>();
		GradProvince obj = new GradProvince();
		obj.setProvCode("BC");
		obj.setProvName("British Columbia");
		gradProvinceList.add(obj);
		obj = new GradProvince();
		obj.setProvCode("AB");
		obj.setProvName("Alberta");
		gradProvinceList.add(obj);
		Mockito.when(gradProvinceTransformer.transformToDTO(gradProvinceRepository.findAll())).thenReturn(gradProvinceList);
		codeService.getAllProvinceCodeList();
		Mockito.verify(gradProvinceTransformer).transformToDTO(gradProvinceRepository.findAll());
	}
	
	@Test
	public void testGetSpecificProvinceCode() {
		String provCode = "BC";
		GradProvince obj = new GradProvince();
		obj.setProvCode("BC");
		obj.setProvName("British Columbia");
		GradProvinceEntity objEntity = new GradProvinceEntity();
		objEntity.setProvCode("BC");
		objEntity.setProvName("British Columbia");
		Optional<GradProvinceEntity> ent = Optional.of(objEntity);
		Mockito.when(gradProvinceRepository.findById(provCode)).thenReturn(ent);
		codeService.getSpecificProvinceCode(provCode);
		Mockito.verify(gradProvinceRepository).findById(provCode);
	}
	
	@Test
	public void testGetSpecificProvinceCodeReturnsNull() {
		String provCode = "BC";
		Mockito.when(gradProvinceRepository.findById(provCode)).thenReturn(Optional.empty());
		codeService.getSpecificProvinceCode(provCode);
		Mockito.verify(gradProvinceRepository).findById(provCode);
	}
	
	@Test
	public void testGetAllCountryList() {
		List<GradCountry> gradCountryList = new ArrayList<>();
		GradCountry obj = new GradCountry();
		obj.setCountryCode("CA");
		obj.setCountryName("Canada");
		gradCountryList.add(obj);
		obj = new GradCountry();
		obj.setCountryCode("USA");
		obj.setCountryName("America");
		gradCountryList.add(obj);
		Mockito.when(gradCountryTransformer.transformToDTO(gradCountryRepository.findAll())).thenReturn(gradCountryList);
		codeService.getAllCountryCodeList();
		Mockito.verify(gradCountryTransformer).transformToDTO(gradCountryRepository.findAll());
	}
	
	@Test
	public void testGetSpecificCountryCode() {
		String countryCode = "AB";
		GradCountry obj = new GradCountry();
		obj.setCountryCode("CA");
		obj.setCountryName("Canada");
		GradCountryEntity objEntity = new GradCountryEntity();
		objEntity.setCountryCode("CA");
		objEntity.setCountryName("Canada");
		Optional<GradCountryEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCountryRepository.findById(countryCode)).thenReturn(ent);
		codeService.getSpecificCountryCode(countryCode);
		Mockito.verify(gradCountryRepository).findById(countryCode);
	}
	
	@Test
	public void testGetSpecificCountryCodeReturnsNull() {
		String countryCode = "CA";
		Mockito.when(gradCountryRepository.findById(countryCode)).thenReturn(Optional.empty());
		codeService.getSpecificCountryCode(countryCode);
		Mockito.verify(gradCountryRepository).findById(countryCode);
	}
	
	@Test
	public void testGetAllUngradReasonCodeList() {
		List<GradUngradReasons> gradUngradReasonList = new ArrayList<>();
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradUngradReasonList.add(obj);
		obj = new GradUngradReasons();
		obj.setCode("CC");
		obj.setDescription("Courses not complete");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradUngradReasonList.add(obj);
		Mockito.when(gradUngradReasonsTransformer.transformToDTO(gradUngradReasonsRepository.findAll())).thenReturn(gradUngradReasonList);
		codeService.getAllUngradReasonCodeList();
		Mockito.verify(gradUngradReasonsTransformer).transformToDTO(gradUngradReasonsRepository.findAll());
	}
	
	@Test
	public void testGetSpecificUngradReasonCode() {
		String reasonCode = "DC";
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradUngradReasonsEntity objEntity = new GradUngradReasonsEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradUngradReasonsEntity> ent = Optional.of(objEntity);
		Mockito.when(gradUngradReasonsRepository.findById(reasonCode)).thenReturn(ent);
		codeService.getSpecificUngradReasonCode(reasonCode);
		Mockito.verify(gradUngradReasonsRepository).findById(reasonCode);
	}
	
	@Test
	public void testGetSpecificUngradReasonCodeReturnsNull() {
		String reasonCode = "DC";
		Mockito.when(gradUngradReasonsRepository.findById(reasonCode)).thenReturn(Optional.empty());
		codeService.getSpecificUngradReasonCode(reasonCode);
		Mockito.verify(gradUngradReasonsRepository).findById(reasonCode);
	}
	
	@Test
	public void testGetAllCertificateTypesCodeList() {
		List<GradCertificateTypes> gradCertificateTypeList = new ArrayList<>();
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("E");
		obj.setDescription("English Dogwood");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradCertificateTypeList.add(obj);
		obj = new GradCertificateTypes();
		obj.setCode("F");
		obj.setDescription("French Dogwood");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradCertificateTypeList.add(obj);
		Mockito.when(gradCertificateTypesTransformer.transformToDTO(gradCertificateTypesRepository.findAll())).thenReturn(gradCertificateTypeList);
		codeService.getAllCertificateTypeCodeList();
		Mockito.verify(gradCertificateTypesTransformer).transformToDTO(gradCertificateTypesRepository.findAll());
	}
	
	@Test
	public void testGetSpecificCertificateTypeCode() {
		String certCode = "E";
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("E");
		obj.setDescription("English Dogwood");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCertificateTypesEntity objEntity = new GradCertificateTypesEntity();
		objEntity.setCode("E");
		objEntity.setDescription("English Dogwood");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCertificateTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCertificateTypesRepository.findById(certCode)).thenReturn(ent);
		codeService.getSpecificCertificateTypeCode(certCode);
		Mockito.verify(gradCertificateTypesRepository).findById(certCode);
	}
	
	@Test
	public void testGetSpecificCertificateTypeCodeReturnsNull() {
		String certCode = "E";
		Mockito.when(gradCertificateTypesRepository.findById(certCode)).thenReturn(Optional.empty());
		codeService.getSpecificCertificateTypeCode(certCode);
		Mockito.verify(gradCertificateTypesRepository).findById(certCode);
	}
	
	@Test
	public void testGetAllReportTypesCodeList() {
		List<GradReportTypes> gradReportTypeList = new ArrayList<>();
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("TRAN");
		obj.setDescription("Transcript");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradReportTypeList.add(obj);
		obj = new GradReportTypes();
		obj.setCode("ACHV");
		obj.setDescription("Achievement");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradReportTypeList.add(obj);
		Mockito.when(gradReportTypesTransformer.transformToDTO(gradReportTypesRepository.findAll())).thenReturn(gradReportTypeList);
		codeService.getAllReportTypeCodeList();
		Mockito.verify(gradReportTypesTransformer).transformToDTO(gradReportTypesRepository.findAll());
	}
	
	@Test
	public void testGetSpecificReportTypeCode() {
		String reportCode = "TRAN";
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("TRAN");
		obj.setDescription("Transcript");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradReportTypesEntity objEntity = new GradReportTypesEntity();
		objEntity.setCode("TRAN");
		objEntity.setDescription("Transcript");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradReportTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradReportTypesRepository.findById(reportCode)).thenReturn(ent);
		codeService.getSpecificReportTypeCode(reportCode);
		Mockito.verify(gradReportTypesRepository).findById(reportCode);
	}
	
	@Test
	public void testGetSpecificReportTypeCodeReturnsNull() {
		String reportCode = "TRAN";
		Mockito.when(gradReportTypesRepository.findById(reportCode)).thenReturn(Optional.empty());
		codeService.getSpecificReportTypeCode(reportCode);
		Mockito.verify(gradReportTypesRepository).findById(reportCode);
	}
	
	@Test
	public void testGetAllMessagingCodeList() {
		List<GradMessaging> gradMessageList = new ArrayList<>();
		GradMessaging obj = new GradMessaging();
		obj.setProgramCode("2018-EN");
		obj.setMessageType("GRADUATED");
		obj.setAdIBPrograms("A");
		obj.setCareerPrograms("CP");
		obj.setGradDate("GD");
		obj.setHonours("Y");
		obj.setMainMessage("abcd");
		obj.setProgramCadre("PR");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradMessageList.add(obj);
		obj = new GradMessaging();
		obj.setProgramCode("2018-PF");
		obj.setMessageType("GRADUATED");
		obj.setAdIBPrograms("A");
		obj.setCareerPrograms("CP");
		obj.setGradDate("GD");
		obj.setHonours("Y");
		obj.setMainMessage("abcd");
		obj.setProgramCadre("PR");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradMessageList.add(obj);
		Mockito.when(gradMessagingTransformer.transformToDTO(gradMessagingRepository.findAll())).thenReturn(gradMessageList);
		codeService.getAllGradMessagingList();
		Mockito.verify(gradMessagingTransformer).transformToDTO(gradMessagingRepository.findAll());
	}
	
	@Test
	public void testGetSpecificGradMessagingCode() {
		String programCode = "2018-EN";
		String msgType = "GRADUATED";
		GradMessaging obj = new GradMessaging();
		obj.setProgramCode("2018-EN");
		obj.setMessageType("GRADUATED");
		obj.setAdIBPrograms("A");
		obj.setCareerPrograms("CP");
		obj.setGradDate("GD");
		obj.setHonours("Y");
		obj.setMainMessage("abcd");
		obj.setProgramCadre("PR");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradMessagingEntity objEntity = new GradMessagingEntity();
		objEntity.setProgramCode("2018-EN");
		objEntity.setMessageType("GRADUATED");
		objEntity.setAdIBPrograms("A");
		objEntity.setCareerPrograms("CP");
		objEntity.setGradDate("GD");
		objEntity.setHonours("Y");
		objEntity.setMainMessage("abcd");
		objEntity.setProgramCadre("PR");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradMessagingEntity> ent = Optional.of(objEntity);
		Mockito.when(gradMessagingRepository.findByProgramCodeAndMessageType(programCode,msgType)).thenReturn(ent);
		codeService.getSpecificGradMessagingCode(programCode,msgType);
		Mockito.verify(gradMessagingRepository).findByProgramCodeAndMessageType(programCode,msgType);
	}
	
	@Test
	public void testGetSpecificMessageCodeReturnsNull() {
		String programCode = "2018-FN";
		String msgType = "GRADUATED";
		Mockito.when(gradMessagingRepository.findByProgramCodeAndMessageType(programCode,msgType)).thenReturn(Optional.empty());
		codeService.getSpecificGradMessagingCode(programCode,msgType);
		Mockito.verify(gradMessagingRepository).findByProgramCodeAndMessageType(programCode,msgType);
	}
	
	@Test
	public void testGetAllCareerProgramsCodeList() {
		List<GradCareerProgram> gradCareerProgramList = new ArrayList<>();
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("AY");
		obj.setDescription("Archaeology");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradCareerProgramList.add(obj);
		obj = new GradCareerProgram();
		obj.setCode("BE");
		obj.setDescription("Business Education");
		gradCareerProgramList.add(obj);
		Mockito.when(gradCareerProgramTransformer.transformToDTO(gradCareerProgramRepository.findAll())).thenReturn(gradCareerProgramList);
		codeService.getAllCareerProgramCodeList();
		Mockito.verify(gradCareerProgramTransformer).transformToDTO(gradCareerProgramRepository.findAll());
	}
	
	@Test
	public void testGetSpecificCareerProgramCode() {
		String cpcCode = "AY";
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("AY");
		obj.setDescription("Archaeology");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCareerProgramEntity objEntity = new GradCareerProgramEntity();
		objEntity.setCode("AY");
		objEntity.setDescription("Archaeology");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCareerProgramEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCareerProgramRepository.findById(cpcCode)).thenReturn(ent);
		codeService.getSpecificCareerProgramCode(cpcCode);
		Mockito.verify(gradCareerProgramRepository).findById(cpcCode);
	}
	
	@Test
	public void testGetSpecificCareerProgramCodeReturnsNull() {
		String cpcCode = "AZ";
		Mockito.when(gradCareerProgramRepository.findById(cpcCode)).thenReturn(Optional.empty());
		codeService.getSpecificCareerProgramCode(cpcCode);
		Mockito.verify(gradCareerProgramRepository).findById(cpcCode);
	}
	
	@Test
	public void testGetAllRequirementTypesCodeList() {
		List<GradRequirementTypes> gradRequirementTypesList = new ArrayList<>();
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("M");
		obj.setDescription("MATCH");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradRequirementTypesList.add(obj);
		obj = new GradRequirementTypes();
		obj.setCode("MC");
		obj.setDescription("MINCREDITS");
		gradRequirementTypesList.add(obj);
		Mockito.when(gradRequirementTypesTransformer.transformToDTO(gradRequirementTypesRepository.findAll())).thenReturn(gradRequirementTypesList);
		codeService.getAllRequirementTypeCodeList();
		Mockito.verify(gradRequirementTypesTransformer).transformToDTO(gradRequirementTypesRepository.findAll());
	}
	
	@Test
	public void testGetSpecificRequirementTypesCode() {
		String reqType = "M";
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("M");
		obj.setDescription("MATCH");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradRequirementTypesEntity objEntity = new GradRequirementTypesEntity();
		objEntity.setCode("M");
		objEntity.setDescription("MATCH");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradRequirementTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradRequirementTypesRepository.findById(reqType)).thenReturn(ent);
		codeService.getSpecificRequirementTypeCode(reqType);
		Mockito.verify(gradRequirementTypesRepository).findById(reqType);
	}
	
	@Test
	public void testGetSpecificRequirementTypesCodeReturnsNull() {
		String reqType = "E";
		Mockito.when(gradRequirementTypesRepository.findById(reqType)).thenReturn(Optional.empty());
		codeService.getSpecificRequirementTypeCode(reqType);
		Mockito.verify(gradRequirementTypesRepository).findById(reqType);
	}
	
	@Test
	public void testCreateGradUngradReasons() {
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradUngradReasonsEntity objEntity = new GradUngradReasonsEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradUngradReasonsTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradUngradReasonsRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(gradUngradReasonsRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createGradUngradReasons(obj);
		Mockito.verify(gradUngradReasonsRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateGradUngradReasons_codeAlreadyExists() {
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradUngradReasonsEntity objEntity = new GradUngradReasonsEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradUngradReasonsEntity> ent = Optional.of(objEntity);
		Mockito.when(gradUngradReasonsTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradUngradReasonsRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createGradUngradReasons(obj);
		Mockito.verify(gradUngradReasonsRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateGradUngradReasons() {
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradUngradReasonsEntity objEntity = new GradUngradReasonsEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradUngradReasonsEntity> ent = Optional.of(objEntity);
		Mockito.when(gradUngradReasonsTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradUngradReasonsRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(gradUngradReasonsRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateGradUngradReasons(obj);
		Mockito.verify(gradUngradReasonsRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateGradUngradReasons_codeAlreadyExists() {
		GradUngradReasons obj = new GradUngradReasons();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradUngradReasonsEntity objEntity = new GradUngradReasonsEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradUngradReasonsTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradUngradReasonsRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateGradUngradReasons(obj);
		
	}
	
	
	@Test
	public void testCreateGradCertificateTypes() {
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCertificateTypesEntity objEntity = new GradCertificateTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradCertificateTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCertificateTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(gradCertificateTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createGradCertificateTypes(obj);
		Mockito.verify(gradCertificateTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateGradCertificateTypes_codeAlreadyExists() {
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCertificateTypesEntity objEntity = new GradCertificateTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCertificateTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCertificateTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCertificateTypesRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createGradCertificateTypes(obj);
		Mockito.verify(gradCertificateTypesRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateGradCertificateTypes() {
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCertificateTypesEntity objEntity = new GradCertificateTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCertificateTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCertificateTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCertificateTypesRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(gradCertificateTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateGradCertificateTypes(obj);
		Mockito.verify(gradCertificateTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateGradCertificateTypes_codeAlreadyExists() {
		GradCertificateTypes obj = new GradCertificateTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCertificateTypesEntity objEntity = new GradCertificateTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradCertificateTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCertificateTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateGradCertificateTypes(obj);
		
	}
	
	
	@Test
	public void testCreateGradCareerProgram() {
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCareerProgramEntity objEntity = new GradCareerProgramEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradCareerProgramTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCareerProgramRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(gradCareerProgramRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createGradCareerProgram(obj);
		Mockito.verify(gradCareerProgramRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateGradCareerProgram_codeAlreadyExists() {
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCareerProgramEntity objEntity = new GradCareerProgramEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCareerProgramEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCareerProgramTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCareerProgramRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createGradCareerProgram(obj);
		Mockito.verify(gradCareerProgramRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateGradCareerProgram() {
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCareerProgramEntity objEntity = new GradCareerProgramEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradCareerProgramEntity> ent = Optional.of(objEntity);
		Mockito.when(gradCareerProgramTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCareerProgramRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(gradCareerProgramRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateGradCareerProgram(obj);
		Mockito.verify(gradCareerProgramRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateGradCareerProgram_codeAlreadyExists() {
		GradCareerProgram obj = new GradCareerProgram();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradCareerProgramEntity objEntity = new GradCareerProgramEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradCareerProgramTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradCareerProgramRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateGradCareerProgram(obj);
		
	}
	
	@Test
	public void testCreateGradRequirementTypes() {
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradRequirementTypesEntity objEntity = new GradRequirementTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradRequirementTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradRequirementTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(gradRequirementTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createGradRequirementTypes(obj);
		Mockito.verify(gradRequirementTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateGradRequirementTypes_codeAlreadyExists() {
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradRequirementTypesEntity objEntity = new GradRequirementTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradRequirementTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradRequirementTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradRequirementTypesRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createGradRequirementTypes(obj);
		Mockito.verify(gradRequirementTypesRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateGradRequirementTypes() {
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradRequirementTypesEntity objEntity = new GradRequirementTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradRequirementTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradRequirementTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradRequirementTypesRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(gradRequirementTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateGradRequirementTypes(obj);
		Mockito.verify(gradRequirementTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateGradRequirementTypes_codeAlreadyExists() {
		GradRequirementTypes obj = new GradRequirementTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradRequirementTypesEntity objEntity = new GradRequirementTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradRequirementTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradRequirementTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateGradRequirementTypes(obj);
		
	}
	
	@Test
	public void testCreateGradReportTypes() {
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradReportTypesEntity objEntity = new GradReportTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradReportTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradReportTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(gradReportTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createGradReportTypes(obj);
		Mockito.verify(gradReportTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateGradReportTypes_codeAlreadyExists() {
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradReportTypesEntity objEntity = new GradReportTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradReportTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradReportTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradReportTypesRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createGradReportTypes(obj);
		Mockito.verify(gradReportTypesRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateGradReportTypes() {
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradReportTypesEntity objEntity = new GradReportTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<GradReportTypesEntity> ent = Optional.of(objEntity);
		Mockito.when(gradReportTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradReportTypesRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(gradReportTypesRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateGradReportTypes(obj);
		Mockito.verify(gradReportTypesRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateGradReportTypes_codeAlreadyExists() {
		GradReportTypes obj = new GradReportTypes();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		GradReportTypesEntity objEntity = new GradReportTypesEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(gradReportTypesTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(gradReportTypesRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateGradReportTypes(obj);
		
	}
	
	@Test
	public void testGetAllStudentStatusCodeList() {
		List<StudentStatus> gradStudentStatusList = new ArrayList<>();
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradStudentStatusList.add(obj);
		obj = new StudentStatus();
		obj.setCode("CC");
		obj.setDescription("Courses not complete");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		gradStudentStatusList.add(obj);
		Mockito.when(studentStatusTransformer.transformToDTO(studentStatusRepository.findAll())).thenReturn(gradStudentStatusList);
		codeService.getAllStudentStatusCodeList();
		Mockito.verify(studentStatusTransformer).transformToDTO(studentStatusRepository.findAll());
	}
	
	@Test
	public void testGetSpecificStudentStatusCode() {
		String reasonCode = "DC";
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<StudentStatusEntity> ent = Optional.of(objEntity);
		Mockito.when(studentStatusRepository.findById(reasonCode)).thenReturn(ent);
		codeService.getSpecificStudentStatusCode(reasonCode);
		Mockito.verify(studentStatusRepository).findById(reasonCode);
	}
	
	@Test
	public void testGetSpecificStudentStatusCodeReturnsNull() {
		String reasonCode = "DC";
		Mockito.when(studentStatusRepository.findById(reasonCode)).thenReturn(Optional.empty());
		codeService.getSpecificStudentStatusCode(reasonCode);
		Mockito.verify(studentStatusRepository).findById(reasonCode);
	}
	
	@Test
	public void testCreateStudentStatus() {
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(studentStatusTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(studentStatusRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		Mockito.when(studentStatusRepository.save(objEntity)).thenReturn(objEntity);
		codeService.createStudentStatus(obj);
		Mockito.verify(studentStatusRepository).save(objEntity);
		
	}
	
	@Test
	public void testCreateStudentStatus_codeAlreadyExists() {
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by School");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<StudentStatusEntity> ent = Optional.of(objEntity);
		Mockito.when(studentStatusTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(studentStatusRepository.findById(obj.getCode())).thenReturn(ent);
		codeService.createStudentStatus(obj);
		Mockito.verify(studentStatusRepository).findById(obj.getCode());
		
	}
	
	@Test
	public void testUpdateStudentStatus() {
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<StudentStatusEntity> ent = Optional.of(objEntity);
		Mockito.when(studentStatusTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(studentStatusRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(studentStatusRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateStudentStatus(obj);
		Mockito.verify(studentStatusRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateStudentStatus_noCreatedUpdatedByData() {
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		Optional<StudentStatusEntity> ent = Optional.of(objEntity);
		Mockito.when(studentStatusTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(studentStatusRepository.findById(obj.getCode())).thenReturn(ent);
		Mockito.when(studentStatusRepository.save(objEntity)).thenReturn(objEntity);
		codeService.updateStudentStatus(obj);
		Mockito.verify(studentStatusRepository).save(objEntity);
		
	}
	
	@Test
	public void testUpdateStudentStatus_codeAlreadyExists() {
		StudentStatus obj = new StudentStatus();
		obj.setCode("DC");
		obj.setDescription("Data Correction by Schools");
		obj.setCreatedBy("GRADUATION");
		obj.setUpdatedBy("GRADUATION");
		obj.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		obj.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		StudentStatusEntity objEntity = new StudentStatusEntity();
		objEntity.setCode("DC");
		objEntity.setDescription("Data Correction by School");
		objEntity.setCreatedBy("GRADUATION");
		objEntity.setUpdatedBy("GRADUATION");
		objEntity.setCreatedTimestamp(new Date(System.currentTimeMillis()));
		objEntity.setUpdatedTimestamp(new Date(System.currentTimeMillis()));
		Mockito.when(studentStatusTransformer.transformToEntity(obj)).thenReturn(objEntity);
		Mockito.when(studentStatusRepository.findById(obj.getCode())).thenReturn(Optional.empty());
		codeService.updateStudentStatus(obj);
		
	}
	
}
