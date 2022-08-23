package dambae200.dambae200.domain.store.repository;

import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final EntityManager em;

    @Override
    public Store findOneByIdCustom(Long id) {
        return em.find(Store.class, id);
    }
}
