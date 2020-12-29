package ca.bc.gov.educ.api.codes.model.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.bc.gov.educ.api.codes.model.dto.GradProgramTypes;
import ca.bc.gov.educ.api.codes.model.entity.GradProgramTypesEntity;


@Component
public class GradProgramTypesTransformer {

    @Autowired
    ModelMapper modelMapper;

    public GradProgramTypes transformToDTO (GradProgramTypesEntity gradProgramEntity) {
    	GradProgramTypes gradProgramTypes = modelMapper.map(gradProgramEntity, GradProgramTypes.class);
        return gradProgramTypes;
    }

    public GradProgramTypes transformToDTO ( Optional<GradProgramTypesEntity> gradProgramEntity ) {
    	GradProgramTypesEntity cae = new GradProgramTypesEntity();
        if (gradProgramEntity.isPresent())
            cae = gradProgramEntity.get();

        GradProgramTypes gradProgramTypes = modelMapper.map(cae, GradProgramTypes.class);
        return gradProgramTypes;
    }

	public List<GradProgramTypes> transformToDTO (Iterable<GradProgramTypesEntity> gradProgramTypesEntities ) {
		List<GradProgramTypes> gradProgramTypesList = new ArrayList<GradProgramTypes>();
        for (GradProgramTypesEntity gradProgramTypesEntity : gradProgramTypesEntities) {
        	GradProgramTypes gradProgramTypes = new GradProgramTypes();
        	gradProgramTypes = modelMapper.map(gradProgramTypesEntity, GradProgramTypes.class);            
        	gradProgramTypesList.add(gradProgramTypes);
        }
        return gradProgramTypesList;
    }

    public GradProgramTypesEntity transformToEntity(GradProgramTypes gradProgramTypes) {
        GradProgramTypesEntity gradProgramTypesEntity = modelMapper.map(gradProgramTypes, GradProgramTypesEntity.class);
        return gradProgramTypesEntity;
    }
}
