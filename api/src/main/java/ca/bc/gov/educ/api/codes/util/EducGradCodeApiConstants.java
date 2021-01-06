package ca.bc.gov.educ.api.codes.util;

import java.util.Date;

public class EducGradCodeApiConstants {

    //API end-point Mapping constants
    public static final String API_ROOT_MAPPING = "";
    public static final String API_VERSION = "v1";
    public static final String GRAD_CODE_API_ROOT_MAPPING = "/api/" + API_VERSION + "/code";
    public static final String GET_ALL_COUNTRY_MAPPING = "/country";
    public static final String GET_ALL_COUNTRY_BY_CODE_MAPPING = "/country/{countryCode}";
    
    public static final String GET_ALL_PROVINCE_MAPPING = "/province";
    public static final String GET_ALL_PROVINCE_BY_CODE_MAPPING = "/province/{provinceCode}";
    
    public static final String GET_ALL_PROGRAM_MAPPING = "/program";
    public static final String GET_ALL_PROGRAM_BY_CODE_MAPPING = "/program/{programCode}";
    
    public static final String GET_ALL_UNGRAD_MAPPING = "/ungradreason";
    public static final String GET_ALL_UNGRAD_BY_CODE_MAPPING = "/ungradreason/{reasonCode}";
    
    public static final String GET_ALL_CERTIFICATE_TYPE_MAPPING = "/certificatetype";
    public static final String GET_ALL_CERTIFICATE_TYPE_BY_CODE_MAPPING = "/certificatetype/{certTypeCode}";
    
    public static final String GET_ALL_GRAD_MESSAGING_MAPPING = "/gradmessages";
    public static final String GET_ALL_GRAD_MESSAGING_BY_PRG_CODE_AND_MESSAGE_TYPE_MAPPING = "/gradmessages/pgmCode/{pgmCode}/msgType/{msgType}";
    
    public static final String GET_ALL_GRAD_CAREER_PROGRAM_MAPPING = "/careerprogram";
    public static final String GET_ALL_GRAD_CAREER_PROGRAM_BY_CODE_MAPPING = "/careerprogram/{cpCode}";
    
    public static final String GET_ALL_GRAD_STATUS_CODE_MAPPING = "/gradstatus";
    public static final String GET_ALL_GRAD_STATUS_CODE_BY_CODE_MAPPING = "/gradstatus/{statusCode}";
    
    public static final String GET_ALL_GRAD_PROGRAM_TYPE_CODE_MAPPING = "/gradprogramtype";
    public static final String GET_ALL_GRAD_PROGRAM_TYPE_CODE_BY_CODE_MAPPING = "/gradprogramtype/{typeCode}";
    
    public static final String GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_MAPPING = "/gradrequirementtype";
    public static final String GET_ALL_GRAD_REQUIREMENT_TYPE_CODE_BY_CODE_MAPPING = "/gradrequirementtype/{typeCode}";
    
    
    //Default Attribute value constants
    public static final String DEFAULT_CREATED_BY = "CodeAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "CodeAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    //Default Date format constants
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String TRAX_DATE_FORMAT = "yyyyMM";
	public static final String ENDPOINT_GRAD_STATUS_BY_CERTIFICATE_TYPE_CODE_URL = "${endpoint.grad-graduation-status-api.grad_list_by_certificate_code.url}";
	public static final String ENDPOINT_STUDENT_UNGRAD_REASON_BY_UNGRAD_REASON_CODE_URL = "${endpoint.grad-common-api.student_ungrad_list_by_ungrad_reason_code.url}";
}
