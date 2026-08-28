package com.zweihander.encurta_url.model;

import jakarta.persistence.*;

/*
* Anotações nessa classe
* @Entity: Define a classe como uma entidade no banco de dados
* @Table: Define o nome da tabela, tem que ser igual ao banco de dados
* @Id: Define um atributo como chave primaria/id
* @GeneratedValeue: nesse caso, define a forma de gerar os ids
* @Column: mapeia cada atributo como uma coluna no banco de dados
* Observação: no column, deve ser atribuído igual no banco de dados, principalmente no nome,
* por convenção, é bom definir se é nullable e unique aqui também.
* */

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

    @Column(name = "acessos")
    private int acessos;

    public int getIdLink() {
        return idLink;
    }

    public void setIdLink(int idLink) {
        this.idLink = idLink;
    }

    public int getAcessos() {
        return acessos;
    }

    public void setAcessos(int acessos) {
        this.acessos = acessos;
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
