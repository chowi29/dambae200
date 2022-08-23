package dambae200.dambae200.domain.cigaretteOnList.controller;

import dambae200.dambae200.domain.cigaretteOnList.dto.CigaretteOnListDto;
import dambae200.dambae200.domain.cigaretteOnList.service.CigaretteOnListFindService;
import dambae200.dambae200.domain.cigaretteOnList.service.CigaretteOnListUpdateService;
import dambae200.dambae200.global.common.DeleteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/cigaretteOnLists")
@RequiredArgsConstructor
public class CigaretteOnListRestController {

    final CigaretteOnListFindService cigaretteOnListFindService;
    final CigaretteOnListUpdateService cigaretteOnListUpdateService;


    @GetMapping("")
    public ResponseEntity<CigaretteOnListDto.GetListCigaretteResponse> findAllByCigaretteListId(@RequestParam @NotBlank String cigaretteListId) {
        CigaretteOnListDto.GetListCigaretteResponse response = cigaretteOnListFindService.findAllByCigaretteListId(Long.valueOf(cigaretteListId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<CigaretteOnListDto.GetCigaretteListResponse> addCigaretteOnListById(@RequestParam @NotBlank String cigaretteListId,
                                                                                              @RequestParam @NotBlank String cigaretteId, @RequestParam @NotBlank String sectionId ) {
        CigaretteOnListDto.GetCigaretteListResponse response = cigaretteOnListUpdateService.addCigaretteOnListById(Long.valueOf(cigaretteListId), Long.valueOf(cigaretteId), Long.valueOf(sectionId));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CigaretteOnListDto.GetCigaretteListResponse> inputCigaretteCount(@PathVariable @NotNull Long id, @RequestBody @Valid CigaretteOnListDto.InputCountRequest request) {
        CigaretteOnListDto.GetCigaretteListResponse response = cigaretteOnListUpdateService.inputCigaretteCount(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteCigaretteOnList(@PathVariable String id) {
        DeleteResponse response = cigaretteOnListUpdateService.deleteCigaretteOnList(Long.valueOf(id));
        return ResponseEntity.ok(response);
    }
}
