package com.zweihander.encurta_url.service;

import com.zweihander.encurta_url.model.LinkModel;
import com.zweihander.encurta_url.repository.LinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    private LinkRepo repo;

    public String encurtaLink(String url){
        Optional<LinkModel> existe = repo.findByUrlOriginal(url);

        if(existe.isPresent()){
            return existe.get().getCodigoUrl();
        }

        LinkModel link = new LinkModel();
        link.setUrlOriginal(url);

        String codigo = Codigos.geraCodigo();
        link.setCodigoUrl(codigo);
        repo.save(link);

        return codigo;
    }

    public String buscarUrlOriginal(String codigo) {
        return repo.findByCodigoUrl(codigo)
                .map(LinkModel::getUrlOriginal)
                .orElseThrow(() -> new RuntimeException("Link não encontrado"));
    }

}
