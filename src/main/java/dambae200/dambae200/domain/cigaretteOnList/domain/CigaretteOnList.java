package dambae200.dambae200.domain.cigaretteOnList.domain;


import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.cigaretteList.domain.CigaretteList;
import dambae200.dambae200.domain.section.domain.Section;
import dambae200.dambae200.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true)
public class CigaretteOnList extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cigaretteOnList_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cigaretteList_id", nullable = false)
    private CigaretteList cigaretteList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cigarette_id",nullable = false)
    private Cigarette cigarette;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;


    private int count;

    private String computerizedName;

    public void changeCigaretteList(CigaretteList cigaretteList) {
        if (this.cigaretteList != null) {
            this.cigaretteList.getCigaretteOnLists().remove(this);
        }
        this.cigaretteList = cigaretteList;
        cigaretteList.getCigaretteOnLists().add(this);
    }


    private void changeCigarette(Cigarette cigarette) {
        this.cigarette = cigarette;
    }

    private void chagneSection(Section section){
        this.section = section;
    }

    public void setSectionWithNull(){
        this.section = null;
    }


    public void changeCount(int count) {
        this.count = count;
    }

    //생성 메서드
    public static CigaretteOnList createCigaretteOnList(Cigarette cigarette, Section section) {
        CigaretteOnList cigaretteOnList = new CigaretteOnList();
        cigaretteOnList.changeCigarette(cigarette);
        cigaretteOnList.chagneSection(section);
        cigaretteOnList.changeCount(0); //등록할때는 0으로

        return cigaretteOnList;
    }
}
