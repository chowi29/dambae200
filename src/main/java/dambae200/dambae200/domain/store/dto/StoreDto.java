package dambae200.dambae200.domain.store.dto;

import dambae200.dambae200.domain.store.domain.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreDto {

    @Getter
    static public class StoreRequest{

        @NotNull(message = "매장 이름을 입력해주세요")
        private String name;

    }



    @Getter
    @NoArgsConstructor
    static public class GetResponse{

        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String name;

        @Builder
        public GetResponse(Store store){
            id = store.getId();
            createdAt = store.getCreatedAt();
            updatedAt = store.getUpdatedAt();
            name = store.getName();
        }

    }

    @Getter
    static public class GetListResponse {

        private List<GetResponse> stores;
        private int total;

        public GetListResponse(List<Store> stores) {
            this.stores = stores.stream().map(GetResponse::new)
                    .collect(Collectors.toList());
            this.total = stores.size();
        }

    }
}
