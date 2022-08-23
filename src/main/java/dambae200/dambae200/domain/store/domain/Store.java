package dambae200.dambae200.domain.store.domain;

import dambae200.dambae200.domain.cigaretteList.domain.CigaretteList;
import dambae200.dambae200.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "store",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CigaretteList cigaretteList;

    public void changeCigaretteList(CigaretteList cigaretteList) {
        this.cigaretteList = cigaretteList;
    }

    public void updateStore(String name) {
        this.name = name;
    }

    @Builder
    public Store(String name) {
        this.name = name;
    }
}
