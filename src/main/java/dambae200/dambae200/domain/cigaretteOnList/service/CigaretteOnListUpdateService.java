package dambae200.dambae200.domain.cigaretteOnList.service;

import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.cigarette.repository.CigaretteRepository;
import dambae200.dambae200.domain.cigaretteList.domain.CigaretteList;
import dambae200.dambae200.domain.cigaretteList.repository.CigaretteListRepository;
import dambae200.dambae200.domain.cigaretteOnList.domain.CigaretteOnList;
import dambae200.dambae200.domain.cigaretteOnList.dto.CigaretteOnListDto;
import dambae200.dambae200.domain.cigaretteOnList.exception.DuplicateCigaretteOnListException;
import dambae200.dambae200.domain.cigaretteOnList.repository.CigaretteOnListRepository;
import dambae200.dambae200.domain.section.domain.Section;
import dambae200.dambae200.domain.section.repository.SectionRepository;
import dambae200.dambae200.global.common.DeleteResponse;
import dambae200.dambae200.global.common.RepoUtils;
import dambae200.dambae200.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CigaretteOnListUpdateService {

    private final CigaretteOnListRepository cigaretteOnListRepository;
    private final CigaretteListRepository cigaretteListRepository;
    private final CigaretteRepository cigaretteRepository;
    private final SectionRepository sectionRepository;
    private final RepoUtils repoUtils;

    //담배 개수 입력
    public CigaretteOnListDto.GetCigaretteListResponse inputCigaretteCount(Long id, CigaretteOnListDto.InputCountRequest request) throws EntityNotFoundException {
        CigaretteOnList cigaretteOnList = repoUtils.getOneElseThrowException(cigaretteOnListRepository, id);
        cigaretteOnList.changeCount(request.getCount());

        return new CigaretteOnListDto.GetCigaretteListResponse(cigaretteOnList);
    }

    //담배 추가(담배id)
    public CigaretteOnListDto.GetCigaretteListResponse addCigaretteOnListById(Long cigaretteListId, Long cigaretteId, Long sectionId) throws DuplicateCigaretteOnListException {

        checkDuplicate(cigaretteListId, cigaretteId);

        CigaretteList cigaretteList = cigaretteListRepository.findOneByIdCustom(cigaretteListId);

        Cigarette cigarette = cigaretteRepository.findOneByIdCustom(cigaretteId);

        Section section = sectionRepository.findOneByIdCustom(sectionId);

        CigaretteOnList cigaretteOnList = CigaretteOnList.createCigaretteOnList(cigarette,section);

        cigaretteList.addCigaretteOnList(cigaretteOnList);

        return new CigaretteOnListDto.GetCigaretteListResponse(cigaretteOnList);
    }


    /*
     //담배추가(by 공식이름)
     public CigaretteOnListDto.GetCigaretteResponse addCigaretteOnListByOfficialName(Long listId, CigaretteOnListDto.AddCigaretteOnList request) {
        CigaretteList cigaretteList = cigaretteListRepository.findOneByIdCustom(listId);

        Cigarette cigarette = cigaretteRepository.findOneByOfficialName(request.getName());

        CigaretteOnList cigaretteOnList = CigaretteOnList.createCigaretteOnList(cigarette);

        cigaretteList.addCigaretteOnList(cigaretteOnList);

        return new CigaretteOnListDto.GetCigaretteResponse(cigaretteOnList);
    }

    //담배추가(by 간편이름)
    public CigaretteOnListDto.GetCigaretteResponse addCigaretteOnListByOfficialName(Long listId, CigaretteOnListDto.AddCigaretteOnList request) {
        CigaretteList cigaretteList = cigaretteListRepository.findOneByIdCustom(listId);

        Cigarette cigarette = cigaretteRepository.findOneByCustomizedName(request.getName());

        CigaretteOnList cigaretteOnList = CigaretteOnList.createCigaretteOnList(cigarette);

        cigaretteList.addCigaretteOnList(cigaretteOnList);

        return new CigaretteOnListDto.GetCigaretteResponse(cigaretteOnList);
    }
     */

    //삭제
    public DeleteResponse deleteCigaretteOnList(Long id) throws EntityNotFoundException {
        CigaretteOnList cigaretteOnList = repoUtils.getOneElseThrowException(cigaretteOnListRepository, id);
        CigaretteList cigaretteList = cigaretteOnList.getCigaretteList();
        cigaretteList.deleteCigaretteOnList(cigaretteOnList);

        cigaretteOnListRepository.delete(cigaretteOnList);

        return new DeleteResponse("cigaretteOnList", id);
    }

    private void checkDuplicate(Long cigaretteListId, Long cigaretteId) {
        if (cigaretteOnListRepository.existsByCigaretteListIdAndCigaretteId(cigaretteListId, cigaretteId)) {
            throw new DuplicateCigaretteOnListException(cigaretteListId, cigaretteId);
        }
    }
}
