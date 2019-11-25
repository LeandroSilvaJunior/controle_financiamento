package org.senac.controle_financiamento.repository;

import org.senac.controle_financiamento.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    public Area findByNome(String nome);
}
