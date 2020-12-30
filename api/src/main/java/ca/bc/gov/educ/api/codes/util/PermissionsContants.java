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
}
