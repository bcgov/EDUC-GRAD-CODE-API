package ca.bc.gov.educ.api.codes.util;

public interface PermissionsContants {
	String _PREFIX = "#oauth2.hasAnyScope('";
	String _SUFFIX = "')";

	String READ_GRAD_PROGRAM = _PREFIX + "READ_GRAD_PROGRAM_CODE_DATA" + _SUFFIX;
	String READ_GRAD_COUNTRY = _PREFIX + "READ_GRAD_COUNTRY_CODE_DATA" + _SUFFIX;
	String READ_GRAD_PROVINCE = _PREFIX + "READ_GRAD_PROVINCE_CODE_DATA" + _SUFFIX;
	String READ_GRAD_UNGRAD = _PREFIX + "READ_GRAD_UNGRAD_CODE_DATA" + _SUFFIX;
	String READ_GRAD_CERTIFICATE = _PREFIX + "READ_GRAD_CERTIFICATE_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_MESSAGING = _PREFIX + "READ_GRAD_MESSAGING_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_CAREER_PROGRAM = _PREFIX + "READ_GRAD_CAREER_PROGRAM_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_STATUS_CODE = _PREFIX + "READ_GRAD_STATUS_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_PROGRAM_TYPE_CODE = _PREFIX + "READ_GRAD_PROGRAM_TYPE_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_REQUIREMENT_TYPE_CODE = _PREFIX + "READ_GRAD_REQUIREMENT_TYPE_CODE_DATA"+ _SUFFIX;
	String DELETE_UNGRAD_REASON = _PREFIX + "DELETE_GRAD_UNGRAD_CODE_DATA"+ _SUFFIX;
	String UPDATE_UNGRAD_REASON = _PREFIX + "UPDATE_GRAD_UNGRAD_CODE_DATA"+ _SUFFIX;
	String CREATE_UNGRAD_REASON = _PREFIX + "CREATE_GRAD_UNGRAD_CODE_DATA"+ _SUFFIX;
	String DELETE_CERTIFICATE_TYPE = _PREFIX + "DELETE_GRAD_CERTIFICATE_CODE_DATA"+ _SUFFIX;
	String UPDATE_CERTIFICATE_TYPE = _PREFIX + "UPDATE_GRAD_CERTIFICATE_CODE_DATA"+ _SUFFIX;
	String CREATE_CERTIFICATE_TYPE = _PREFIX + "CREATE_GRAD_CERTIFICATE_CODE_DATA"+ _SUFFIX;
	String CREATE_CAREER_PROGRAM = _PREFIX + "CREATE_GRAD_CAREER_PROGRAM_CODE_DATA"+ _SUFFIX;
	String UPDATE_CAREER_PROGRAM = _PREFIX + "UPDATE_GRAD_CAREER_PROGRAM_CODE_DATA"+ _SUFFIX;
	String DELETE_CAREER_PROGRAM = _PREFIX + "DELETE_GRAD_CAREER_PROGRAM_CODE_DATA"+ _SUFFIX;
	String CREATE_PROGRAM_TYPE = _PREFIX + "CREATE_GRAD_PROGRAM_TYPE_CODE_DATA"+ _SUFFIX;
	String UPDATE_PROGRAM_TYPE = _PREFIX + "UPDATE_GRAD_PROGRAM_TYPE_CODE_DATA"+ _SUFFIX;
	String DELETE_PROGRAM_TYPE = _PREFIX + "DELETE_GRAD_PROGRAM_TYPE_CODE_DATA"+ _SUFFIX;
	String CREATE_REQUIREMENT_TYPE = _PREFIX + "CREATE_GRAD_REQUIREMENT_TYPE_CODE_DATA"+ _SUFFIX;
	String UPDATE_REQUIREMENT_TYPE = _PREFIX + "UPDATE_GRAD_REQUIREMENT_TYPE_CODE_DATA"+ _SUFFIX;
	String DELETE_REQUIREMENT_TYPE = _PREFIX + "DELETE_GRAD_REQUIREMENT_TYPE_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_REPORT = _PREFIX + "READ_GRAD_REPORT_CODE_DATA"+ _SUFFIX;
	String DELETE_REPORT_TYPE = _PREFIX + "DELETE_GRAD_REPORT_CODE_DATA"+ _SUFFIX;
	String UPDATE_REPORT_TYPE = _PREFIX + "UPDATE_GRAD_REPORT_CODE_DATA"+ _SUFFIX;
	String CREATE_REPORT_TYPE = _PREFIX + "CREATE_GRAD_REPORT_CODE_DATA"+ _SUFFIX;
	String CREATE_STUDENT_STATUS = _PREFIX + "CREATE_GRAD_STUDENT_STATUS_CODE_DATA"+ _SUFFIX;
	String READ_GRAD_STUDENT_STATUS = _PREFIX + "READ_GRAD_STUDENT_STATUS_CODE_DATA" + _SUFFIX;
	String DELETE_STUDENT_STATUS = _PREFIX + "DELETE_GRAD_STUDENT_STATUS_CODE_DATA"+ _SUFFIX;
	String UPDATE_STUDENT_STATUS = _PREFIX + "UPDATE_GRAD_STUDENT_STATUS_CODE_DATA"+ _SUFFIX;
}