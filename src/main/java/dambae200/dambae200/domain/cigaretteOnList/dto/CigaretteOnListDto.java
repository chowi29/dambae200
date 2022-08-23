package dambae200.dambae200.domain.cigaretteOnList.dto;

import dambae200.dambae200.domain.cigarette.domain.Cigarette;
import dambae200.dambae200.domain.cigaretteOnList.domain.CigaretteOnList;
import dambae200.dambae200.domain.section.domain.Section;
import dambae200.dambae200.global.common.BaseDto;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CigaretteOnListDto {

    @Getter
    static public class InputCountRequest {
        @NotBlank(message = "개수를 입력해주세요")
        private int count;
    }

    @Getter
    static public class AddCigaretteOnList{
        @NotBlank(message = "담배 명칭을 입력해 주세요")
        private String name;
    }

    /*
    @Getter
    static public class AddCigaretteOnListById{
        @NotBlank(message = "담배 id를 입려해주세요")
        private Long id;
    }
     */


    @Getter
    static public class GetCigaretteListResponse extends BaseDto {
        private Long id;
        private String computerizedName;
        private String officialName;
        private String customizedName;
        private String sectionName;
        private int count;

        public GetCigaretteListResponse(CigaretteOnList cigaretteOnList) {
            Cigarette cigarette = cigaretteOnList.getCigarette();
            Section section = cigaretteOnList.getSection();

            this.id = cigarette.getId();
            this.computerizedName = cigaretteOnList.getComputerizedName();
            this.officialName = cigarette.getOfficialName();
            this.customizedName = cigarette.getCustomizedName();
            this.count = cigaretteOnList.getCount();
            this.sectionName = section.getName();
            createdAt = cigaretteOnList.getCreatedAt();
            updateAt = cigaretteOnList.getUpdatedAt();
        }
    }

    @Getter
    public static class GetListCigaretteResponse {
        private List<GetCigaretteListResponse> cigaretteOnLists = new ArrayList<>();
        private int total = 0;

        public GetListCigaretteResponse(List<CigaretteOnList> cigaretteOnList) {
            this.cigaretteOnLists = cigaretteOnList.stream()
                    .map(GetCigaretteListResponse::new)
                    .collect(Collectors.toList());
            this.total = cigaretteOnLists.size();
        }
    }




}






