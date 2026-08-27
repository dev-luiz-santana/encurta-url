package com.zweihander.encurta_url.model;

import jakarta.persistence.*;

@Entity
@Table(name = "link")
public class LinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_link", nullable = false, unique = true)
    private int idLink;

    @Column(name = "url_original", nullable = false,length = 120)
    private String urlOriginal;

    @Column(name = "codigo_url", nullable = false, unique = true, length = 120)
    private String codigoUrl;

    public int getIdLink() {
        return idLink;
    }

    public void setIdLink(int idLink) {
        this.idLink = idLink;
    }

    public String getCodigoUrl() {
        return codigoUrl;
    }

    public void setCodigoUrl(String codigoUrl) {
        this.codigoUrl = codigoUrl;
    }

    public String getUrlOriginal(){
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }
}
