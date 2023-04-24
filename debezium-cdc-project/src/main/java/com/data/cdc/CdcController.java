package com.data.cdc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CdcController {

    private final MongoService mongoService;

    @GetMapping("/cdc-result")
    public ResponseEntity getCdcResult(){
        return ResponseEntity.ok(mongoService.getReplicateDate());
    }
}
