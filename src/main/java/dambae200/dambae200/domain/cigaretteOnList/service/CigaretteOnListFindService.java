package dambae200.dambae200.domain.cigaretteOnList.service;

import dambae200.dambae200.domain.cigaretteList.repository.CigaretteListRepository;
import dambae200.dambae200.domain.cigaretteOnList.domain.CigaretteOnList;
import dambae200.dambae200.domain.cigaretteOnList.dto.CigaretteOnListDto;
import dambae200.dambae200.domain.cigaretteOnList.repository.CigaretteOnListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CigaretteOnListFindService {

    private final CigaretteListRepository cigaretteListRepository;
    private final CigaretteOnListRepository cigaretteOnListRepository;

    public CigaretteOnListDto.GetListCigaretteResponse findAllByCigaretteListId(Long cigaretteListId) {
        List<CigaretteOnList> cigaretteOnLists = cigaretteOnListRepository.findAllByCigaretteListId(cigaretteListId);
        return new CigaretteOnListDto.GetListCigaretteResponse(cigaretteOnLists);
    }

    public CigaretteOnListDto.GetListCigaretteResponse findAllBySectionId(Long sectionId) {
        List<CigaretteOnList> cigaretteOnLists = cigaretteOnListRepository.findAllBySectionId(sectionId);
        return new CigaretteOnListDto.GetListCigaretteResponse(cigaretteOnLists);
    }


}
