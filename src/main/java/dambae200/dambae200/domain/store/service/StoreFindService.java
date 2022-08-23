package dambae200.dambae200.domain.store.service;

import dambae200.dambae200.domain.store.domain.Store;
import dambae200.dambae200.domain.store.dto.StoreDto;
import dambae200.dambae200.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFindService {

    private final StoreRepository storeRepository;

    public StoreDto.GetListResponse findByNmaeLike(String name) {
        List<Store> stores = storeRepository.findByNameLike("%" + name + "%");
        return new StoreDto.GetListResponse(stores);
    }

    public StoreDto.GetListResponse findAll() {
        List<Store> stores = storeRepository.findAll();
        return new StoreDto.GetListResponse(stores);
    }
}
