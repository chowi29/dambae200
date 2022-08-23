package dambae200.dambae200.domain.cigarette.service;

import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.cigarette.dto.CigaretteDto;
import dambae200.dambae200.domain.cigarette.repository.CigaretteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CigaretteFindService {

    private final CigaretteRepository cigaretteRepository;

    public CigaretteDto.GetListResponse findByOfficalNameLike(String name) {
        System.out.println(name);
        List<Cigarette> cigarettes = cigaretteRepository.findByOfficialNameLike("%" + name + "%");
        return new CigaretteDto.GetListResponse(cigarettes);
    }

    public CigaretteDto.GetListResponse findByCustomizedNameLike(String name) {
        System.out.println(name);
        List<Cigarette> cigarettes = cigaretteRepository.findByCustomizedNameLike("%" + name + "%");
        return new CigaretteDto.GetListResponse(cigarettes);
    }

    public CigaretteDto.GetListResponse findAll() {
        List<Cigarette> cigarettes = cigaretteRepository.findAll();
        return new CigaretteDto.GetListResponse(cigarettes);
    }

}
