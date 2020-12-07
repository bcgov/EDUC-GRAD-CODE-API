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
    
    
    //Default Attribute value constants
    public static final String DEFAULT_CREATED_BY = "CodeAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "CodeAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    //Default Date format constants
    public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";
    
    public static final String TRAX_DATE_FORMAT = "yyyyMM";
}
