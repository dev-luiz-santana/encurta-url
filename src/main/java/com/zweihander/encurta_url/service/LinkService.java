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

    public String encurtaLink(String url) {
        Optional<LinkModel> existe = repo.findByUrlOriginal(url);
        LinkModel link = new LinkModel();

        if (existe.isPresent()) {
            return existe.get().getCodigoUrl();
        }

        link.setUrlOriginal(url);

        String codigo = Codigos.geraCodigo();
        link.setCodigoUrl(codigo);
        repo.save(link);

        return codigo;
    }

    public String buscarUrlOriginal(String codigo) {
        LinkModel link = repo.findByCodigoUrl(codigo)
                .orElseThrow(() -> {
                    return new RuntimeException("Link não econtrado");
                });

        repo.atualizarAcessos(codigo);

        return link.getUrlOriginal();
    }

    public LinkModel buscarStatus(String codigo) {
        return repo.findByCodigoUrl(codigo).orElseThrow(()
                -> new RuntimeException("Link não encontrado")
        );
    }

}