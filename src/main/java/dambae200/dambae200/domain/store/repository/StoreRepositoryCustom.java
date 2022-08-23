package dambae200.dambae200.domain.store.repository;


import dambae200.dambae200.domain.store.domain.Store;

public interface StoreRepositoryCustom {
    Store findOneByIdCustom(Long id);
}
