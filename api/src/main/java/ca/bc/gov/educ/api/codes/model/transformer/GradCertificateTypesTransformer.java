package ca.bc.gov.educ.api.codes.model.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.bc.gov.educ.api.codes.model.dto.GradCertificateTypes;
import ca.bc.gov.educ.api.codes.model.entity.GradCertificateTypesEntity;


@Component
public class GradCertificateTypesTransformer {

    @Autowired
    ModelMapper modelMapper;

    public GradCertificateTypes transformToDTO (GradCertificateTypesEntity gradProgramEntity) {
    	GradCertificateTypes gradCertificateTypes = modelMapper.map(gradProgramEntity, GradCertificateTypes.class);
        return gradCertificateTypes;
    }

    public GradCertificateTypes transformToDTO ( Optional<GradCertificateTypesEntity> gradProgramEntity ) {
    	GradCertificateTypesEntity cae = new GradCertificateTypesEntity();
        if (gradProgramEntity.isPresent())
            cae = gradProgramEntity.get();

        GradCertificateTypes gradCertificateTypes = modelMapper.map(cae, GradCertificateTypes.class);
        return gradCertificateTypes;
    }

	public List<GradCertificateTypes> transformToDTO (Iterable<GradCertificateTypesEntity> gradCertificateTypesEntities ) {
		List<GradCertificateTypes> gradCertificateTypesList = new ArrayList<GradCertificateTypes>();
        for (GradCertificateTypesEntity gradCertificateTypesEntity : gradCertificateTypesEntities) {
        	GradCertificateTypes gradCertificateTypes = new GradCertificateTypes();
        	gradCertificateTypes = modelMapper.map(gradCertificateTypesEntity, GradCertificateTypes.class);            
        	gradCertificateTypesList.add(gradCertificateTypes);
        }
        return gradCertificateTypesList;
    }

    public GradCertificateTypesEntity transformToEntity(GradCertificateTypes gradCertificateTypes) {
        GradCertificateTypesEntity gradCertificateTypesEntity = modelMapper.map(gradCertificateTypes, GradCertificateTypesEntity.class);
        return gradCertificateTypesEntity;
    }
}
