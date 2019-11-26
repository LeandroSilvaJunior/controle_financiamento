package org.senac.controle_financiamento.repository;

import org.senac.controle_financiamento.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    public Projeto findOneByNome(String nome);
}
