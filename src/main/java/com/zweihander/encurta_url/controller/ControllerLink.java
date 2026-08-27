package com.zweihander.encurta_url.controller;

import com.zweihander.encurta_url.service.LinkService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
public class ControllerLink {

    @Autowired
    private LinkService linkService;

    @PostMapping("/url/encurta")
    public ResponseEntity<Map<String, String>> encurtar(@RequestBody Map<String , String> body){
        String urlOriginal = body.get("url");
        String codigo = linkService.encurtaLink(urlOriginal);
        String urlCurta = "http://localhost:8080/" + codigo;

        return ResponseEntity.ok(Map.of("shortUrl", urlCurta));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Void> redirecionar(@PathVariable String codigo){
        String urlOriginal = linkService.buscarUrlOriginal(codigo);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlOriginal))
                .build();
    }

}
