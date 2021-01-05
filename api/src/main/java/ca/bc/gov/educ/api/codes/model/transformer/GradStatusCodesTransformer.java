package ca.bc.gov.educ.api.codes.model.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.bc.gov.educ.api.codes.model.dto.GradStatusCodes;
import ca.bc.gov.educ.api.codes.model.entity.GradStatusCodesEntity;


@Component
public class GradStatusCodesTransformer {

    @Autowired
    ModelMapper modelMapper;

    public GradStatusCodes transformToDTO (GradStatusCodesEntity gradProgramEntity) {
    	GradStatusCodes gradStatusCodes = modelMapper.map(gradProgramEntity, GradStatusCodes.class);
        return gradStatusCodes;
    }

    public GradStatusCodes transformToDTO ( Optional<GradStatusCodesEntity> gradProgramEntity ) {
    	GradStatusCodesEntity cae = new GradStatusCodesEntity();
        if (gradProgramEntity.isPresent())
            cae = gradProgramEntity.get();

        GradStatusCodes gradStatusCodes = modelMapper.map(cae, GradStatusCodes.class);
        return gradStatusCodes;
    }

	public List<GradStatusCodes> transformToDTO (Iterable<GradStatusCodesEntity> gradStatusCodesEntities ) {
		List<GradStatusCodes> gradStatusCodesList = new ArrayList<GradStatusCodes>();
        for (GradStatusCodesEntity gradStatusCodesEntity : gradStatusCodesEntities) {
        	GradStatusCodes gradStatusCodes = new GradStatusCodes();
        	gradStatusCodes = modelMapper.map(gradStatusCodesEntity, GradStatusCodes.class);            
        	gradStatusCodesList.add(gradStatusCodes);
        }
        return gradStatusCodesList;
    }

    public GradStatusCodesEntity transformToEntity(GradStatusCodes gradStatusCodes) {
        GradStatusCodesEntity gradStatusCodesEntity = modelMapper.map(gradStatusCodes, GradStatusCodesEntity.class);
        return gradStatusCodesEntity;
    }
}
