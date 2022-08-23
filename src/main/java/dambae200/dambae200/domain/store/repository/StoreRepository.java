package dambae200.dambae200.domain.store.repository;


import dambae200.dambae200.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    public List<Store> findByNameLike(String s);
}
