package dambae200.dambae200.repository;

import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.cigarette.repository.CigaretteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CigaretteRepositoryTest {


    @Autowired
    CigaretteRepository cigaretteRepository;

    @Test
    public void testCigarette() {
        Cigarette cigarette = Cigarette.builder().officialName("abcd")
                .customizedName("bcae").build();
        Cigarette savedCigarette = cigaretteRepository.save(cigarette);

        Cigarette findCigarette = cigaretteRepository.findOneByIdCustom(savedCigarette.getId());

        Assertions.assertThat(findCigarette.getId()).isEqualTo(cigarette.getId());
        Assertions.assertThat(findCigarette.getOfficialName()).isEqualTo(cigarette.getOfficialName());
        Assertions.assertThat(findCigarette.getCustomizedName()).isEqualTo(cigarette.getCustomizedName());
        Assertions.assertThat(findCigarette).isEqualTo(cigarette);
    }

    @Test
    public void basicCRUD() {
        Cigarette cigarette1 = new Cigarette("aabb", "qqww");
        Cigarette cigarette2 = new Cigarette("abbc", "qwwq");
        Cigarette cigarette3 = new Cigarette("bbcc", "wwrr");

        cigaretteRepository.save(cigarette1);
        cigaretteRepository.save(cigarette2);
        cigaretteRepository.save(cigarette3);

        //리스트 조회
        List<Cigarette> all = cigaretteRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(3);

        /*
        //이름으로 조회
        List<Cigarette> a = cigaretteRepository.findByOfficialNameLike("a");
        Assertions.assertThat(a.size()).isEqualTo(2);

        List<Cigarette> q = cigaretteRepository.findByCustomizedNameLike("q");
        Assertions.assertThat(q.size()).isEqualTo(2);

        Cigarette findCigarette1 = cigaretteRepository.findOneByOfficialName("bbcc");
        Assertions.assertThat(findCigarette1.getId()).isEqualTo(cigarette3.getId());
        Assertions.assertThat(findCigarette1.getOfficialName()).isEqualTo(cigarette3.getOfficialName());
        Assertions.assertThat(findCigarette1.getCustomizedName()).isEqualTo(cigarette3.getCustomizedName());
        Assertions.assertThat(findCigarette1).isEqualTo(cigarette3);

        Cigarette findCigarette2 = cigaretteRepository.findOneByCustomizedName("wwrr");
        Assertions.assertThat(findCigarette2.getId()).isEqualTo(cigarette3.getId());
        Assertions.assertThat(findCigarette2.getOfficialName()).isEqualTo(cigarette3.getOfficialName());
        Assertions.assertThat(findCigarette2.getCustomizedName()).isEqualTo(cigarette3.getCustomizedName());
        Assertions.assertThat(findCigarette2).isEqualTo(cigarette3);
         */

        //삭제
        cigaretteRepository.delete(cigarette1);
        cigaretteRepository.delete(cigarette2);
        cigaretteRepository.delete(cigarette3);

        long count = cigaretteRepository.count();
        Assertions.assertThat(count).isEqualTo(0);
    }


}
