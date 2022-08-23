package dambae200.dambae200.domain.section.repository;

import dambae200.dambae200.domain.section.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long>, SectionRepositoryCustom {
    boolean existsByName(String name);

}
