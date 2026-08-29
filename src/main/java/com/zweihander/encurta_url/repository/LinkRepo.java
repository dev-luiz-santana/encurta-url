package com.zweihander.encurta_url.repository;

import com.zweihander.encurta_url.model.LinkModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/*
* Interfaces no Java
* A interface é como uma classe, porem os métodos dela não tem corpo, ou seja, são métodos abstratos
* então quando outra classe implementa uma interface, ela é obrigada agora a escrever o método e dar um corpo a ele
* funcionando como um tipo de contrato com o Java.
*
* Como funciona no Spring Boot
* No Spring Boot, não é necessário dizer explicitamente que x classe implementa de x interface,
* em tempo de execução, o spring faz a implementação enquanto o código roda,
* por isso que não implementamos a classe automaticamente.
* Mas para isso funcionar, os métodos da interface tem que ter um nome que segue a convenção do SpringData,
* então ele entende o que eles fazem, tipo findByCodigoUrl, o spring entende o que esse método faz executa na hora.
* */

public interface LinkRepo extends JpaRepository<LinkModel, Integer> {
    Optional<LinkModel>findByCodigoUrl(String codigo);
    Optional<LinkModel>findByUrlOriginal(String original);

    @Modifying
    @Transactional
    @Query("UPDATE LinkModel link SET link.acessos = link.acessos + 1 WHERE link.codigoUrl = :codigoUrl")
    int atualizarAcessos(@Param("codigoUrl") String codigo);
}
