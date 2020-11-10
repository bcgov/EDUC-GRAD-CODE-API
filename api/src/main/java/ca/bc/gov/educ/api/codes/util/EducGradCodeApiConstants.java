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
    
    //Default Attribute value constants
    public static final String DEFAULT_CREATED_BY = "CodeAPI";
    public static final Date DEFAULT_CREATED_TIMESTAMP = new Date();
    public static final String DEFAULT_UPDATED_BY = "CodeAPI";
    public static final Date DEFAULT_UPDATED_TIMESTAMP = new Date();

    //Default Date format constants
    public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";
    
    public static final String TRAX_DATE_FORMAT = "yyyyMM";
}
