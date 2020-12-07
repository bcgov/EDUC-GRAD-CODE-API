package ca.bc.gov.educ.api.codes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bc.gov.educ.api.codes.model.entity.GradMessagingEntity;

@Repository
public interface GradMessagingRepository extends JpaRepository<GradMessagingEntity, String> {

    List<GradMessagingEntity> findAll();

	Optional<GradMessagingEntity> findByProgramCodeAndMessageType(String pgmCode, String msgType);

}
