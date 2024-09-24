package com.solidos.caia.api.papers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.api.common.models.CommonResponse;
import com.solidos.caia.api.papers.dto.CreatePaperDto;
import com.solidos.caia.api.papers.entities.PaperEntity;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/papers")
@PreAuthorize("authenticated")
public class PaperController {
  private PaperService paperService;

  public PaperController(PaperService paperService) {
    this.paperService = paperService;
  }

  @PostMapping
  public ResponseEntity<CommonResponse<PaperEntity>> createPaper(
      @ModelAttribute @Valid CreatePaperDto createPaperDto, MultipartFile file) throws Exception {

    PaperEntity newPaper = paperService.createpaper(createPaperDto, file);

    return ResponseEntity.ok(CommonResponse.success(newPaper, "Paper created"));
  }
}
