package dambae200.dambae200.domain.cigarette.controller;

import dambae200.dambae200.domain.cigarette.dto.CigaretteDto;
import dambae200.dambae200.domain.cigarette.service.CigaretteFindService;
import dambae200.dambae200.domain.cigarette.service.CigaretteUpdateService;
import dambae200.dambae200.global.common.DeleteResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/cigarettes")
@RequiredArgsConstructor
public class CigaretteRestController {

    final CigaretteFindService cigaretteFindService;
    final CigaretteUpdateService cigaretteUpdateService;

    @GetMapping("/v1")
    public ResponseEntity<CigaretteDto.GetListResponse> findAll() {
        CigaretteDto.GetListResponse response = cigaretteFindService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v2")
    public ResponseEntity<CigaretteDto.GetListResponse> findAllByOfficialName(@RequestParam @NotNull String name) {
        String decodedName = URLDecoder.decode(name, Charset.forName("UTF-8"));
        CigaretteDto.GetListResponse response = cigaretteFindService.findByOfficalNameLike(decodedName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v3")
    public ResponseEntity<CigaretteDto.GetListResponse> findAllByCustomizedName(@RequestParam @NotNull String name) {
        String decodedName = URLDecoder.decode(name, Charset.forName("UTF-8"));
        CigaretteDto.GetListResponse response = cigaretteFindService.findByCustomizedNameLike(decodedName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<CigaretteDto.GetResponse> addCigarette(@RequestBody @Valid CigaretteDto.CigaretteRequest request) {
        CigaretteDto.GetResponse response = cigaretteUpdateService.addCigarette(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CigaretteDto.GetResponse> updateCigarette(@PathVariable @NotNull Long id, @RequestBody @Valid CigaretteDto.CigaretteRequest request) {
        CigaretteDto.GetResponse response = cigaretteUpdateService.updateCigarette(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteCigarette(@PathVariable String id) {
        DeleteResponse response = cigaretteUpdateService.deleteCigarette(Long.valueOf(id));
        return ResponseEntity.ok(response);
    }


}
