package com.zweihander.encurta_url.controller;

import com.zweihander.encurta_url.model.LinkModel;
import com.zweihander.encurta_url.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

/*
* Anotações nessa classe
* @RestController: define essa classe como um controller rest
* @Autowired: pega uma instancia pronta por outra anotação e implementa para outras classes, como no link service
* @PostMapping: Mapeia requisições POST em um método
* @RequesBody: pega o corpo da requisição e transforma em  objeto Java
* @GetMapping: Mapeia requisições GET em um método
* @PathVariable: Dá um valor a variável de caminho
* */


@RestController
public class ControllerLink {

    @Autowired
    private LinkService linkService;

    //método de encurtar o link
    //quando a requisição é feita, o corpo da requisição vai ser mapeado
    //então o valor que estiver em "url" vai ser pego e colocado na variável urlOriginal
    //depois vai ser gerado um código com o método do linkService, que manda o urlOriginal como parametro
    //então será feito uma variável chamada urlCurta, que é o link da aplicação com o código gerado
    //então ira retornar uma response em formato JSON, por isso o uso do map(que é tipo o dicionary do C#)
    @PostMapping("/url/encurta")
    public ResponseEntity<Map<String, String>> encurtar(@RequestBody Map<String , String> body){
        String urlOriginal = body.get("url");
        String codigo = linkService.encurtaLink(urlOriginal);
        String urlCurta = "http://localhost:8080/" + codigo;

        return ResponseEntity.ok(Map.of("shortUrl", urlCurta));
    }

    //método de redirecionar
    //primeiro, a anotação do spring para dizer que esse método responde a requisições HTTP GET
    //a anotação que está no parâmetro, pega o valor que a requisição trouxe na variável de caminho (no caso, codigo)
    //chama o método do service para buscar a url pelo código enviado
    //então, retorna finalmente o status e redireciona para a url original
    @GetMapping("/url/{codigo}")
    public ResponseEntity<Void> redirecionar(@PathVariable String codigo){
        String urlOriginal = linkService.buscarUrlOriginal(codigo);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlOriginal))
                .build();
    }

    //metodo de ver status do link
    //quando a requisição é feita, seleciona o link pelo codigo
    //e pega todas as informações do link id, urlOriginal, etc.
    @GetMapping("/url/{codigo}/status")
    public ResponseEntity<LinkModel> verStatus(@PathVariable String codigo){
        LinkModel link = linkService.buscarStatus(codigo);
        return ResponseEntity.ok(link);

    }

}
