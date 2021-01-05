package ca.bc.gov.educ.api.codes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.bc.gov.educ.api.codes.model.entity.GradProgramTypesEntity;

@Repository
public interface GradProgramTypesRepository extends JpaRepository<GradProgramTypesEntity, String> {

    List<GradProgramTypesEntity> findAll();

}
