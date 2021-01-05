package ca.bc.gov.educ.api.codes.model.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.bc.gov.educ.api.codes.model.dto.GradMessaging;
import ca.bc.gov.educ.api.codes.model.entity.GradMessagingEntity;


@Component
public class GradMessagingTransformer {

    @Autowired
    ModelMapper modelMapper;

    public GradMessaging transformToDTO (GradMessagingEntity gradProgramEntity) {
    	GradMessaging gradMessaging = modelMapper.map(gradProgramEntity, GradMessaging.class);
        return gradMessaging;
    }

    public GradMessaging transformToDTO ( Optional<GradMessagingEntity> gradProgramEntity ) {
    	GradMessagingEntity cae = new GradMessagingEntity();
        if (gradProgramEntity.isPresent())
            cae = gradProgramEntity.get();

        GradMessaging gradMessaging = modelMapper.map(cae, GradMessaging.class);
        return gradMessaging;
    }

	public List<GradMessaging> transformToDTO (Iterable<GradMessagingEntity> gradMessagingEntities ) {
		List<GradMessaging> gradMessagingList = new ArrayList<GradMessaging>();
        for (GradMessagingEntity gradMessagingEntity : gradMessagingEntities) {
        	GradMessaging gradMessaging = new GradMessaging();
        	gradMessaging = modelMapper.map(gradMessagingEntity, GradMessaging.class);            
        	gradMessagingList.add(gradMessaging);
        }
        return gradMessagingList;
    }

    public GradMessagingEntity transformToEntity(GradMessaging gradMessaging) {
        GradMessagingEntity gradMessagingEntity = modelMapper.map(gradMessaging, GradMessagingEntity.class);
        return gradMessagingEntity;
    }
}
