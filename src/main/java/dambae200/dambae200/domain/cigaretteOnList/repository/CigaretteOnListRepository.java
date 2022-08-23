package dambae200.dambae200.domain.cigaretteOnList.repository;

import dambae200.dambae200.domain.cigaretteOnList.domain.CigaretteOnList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CigaretteOnListRepository extends JpaRepository<CigaretteOnList, Long>, CigaretteOnListRepositoryCustom {
    List<CigaretteOnList> findAllByCigaretteId(Long cigaretteId);

    List<CigaretteOnList> findAllByCigaretteListId(Long cigaretteListId);

    List<CigaretteOnList> findAllBySectionId(Long sectionID);

    boolean existsByCigaretteListIdAndCigaretteId(Long cigaretteListId, Long cigaretteId);
}
