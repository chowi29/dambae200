package dambae200.dambae200.domain.store.service;

import dambae200.dambae200.domain.cigaretteList.domain.CigaretteList;
import dambae200.dambae200.domain.cigaretteList.repository.CigaretteListRepository;
import dambae200.dambae200.domain.cigaretteOnList.domain.CigaretteOnList;
import dambae200.dambae200.domain.cigaretteOnList.repository.CigaretteOnListRepository;
import dambae200.dambae200.domain.store.domain.Store;
import dambae200.dambae200.domain.store.dto.StoreDto;
import dambae200.dambae200.domain.store.repository.StoreRepository;
import dambae200.dambae200.global.common.DeleteResponse;
import dambae200.dambae200.global.common.RepoUtils;
import dambae200.dambae200.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreUpdateService {

    private final StoreRepository storeRepository;
    private final CigaretteListRepository cigaretteListRepository;
    private final CigaretteOnListRepository cigaretteOnListRepository;
    private final RepoUtils repoUtils;

    //등록
    public StoreDto.GetResponse addStore(StoreDto.StoreRequest request) {
        Store store = Store.builder().name(request.getName()).build();
        Store savedStore = storeRepository.save(store);

        //리스트 생성
        CigaretteList cigaretteList = CigaretteList.createCigaretteList(store);

        cigaretteListRepository.save(cigaretteList);

        return new StoreDto.GetResponse(savedStore);
    }

    //update
    public StoreDto.GetResponse updateStore(Long id, StoreDto.StoreRequest request) throws EntityNotFoundException {
        Store store = repoUtils.getOneElseThrowException(storeRepository, id);
        store.updateStore(request.getName());

        CigaretteList cigaretteList = cigaretteListRepository.findByStoreId(id);
        cigaretteList.changeName(request.getName());

        return new StoreDto.GetResponse(store);
    }


    //삭제
    public DeleteResponse deleteStore(Long id)  throws EntityNotFoundException {
        Store store = repoUtils.getOneElseThrowException(storeRepository, id);

        CigaretteList cigaretteList = cigaretteListRepository.findByStoreId(id);

        List<CigaretteOnList> cigaretteOnLists = cigaretteOnListRepository.findAllByCigaretteListId(cigaretteList.getId());

        cigaretteOnListRepository.deleteAll(cigaretteOnLists);

        cigaretteListRepository.delete(cigaretteList);

        storeRepository.delete(store);

        storeRepository.delete(store);
        return new DeleteResponse("store", id);
    }
}

