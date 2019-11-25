package org.senac.controle_financiamento.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(length = 100, unique = true)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal orcamentoMinimo;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal orcamentoMaximo;

    public Area() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getOrcamentoMinimo() {
        return orcamentoMinimo;
    }

    public void setOrcamentoMinimo(BigDecimal orcamentoMinimo) {
        this.orcamentoMinimo = orcamentoMinimo;
    }

    public BigDecimal getOrcamentoMaximo() {
        return orcamentoMaximo;
    }

    public void setOrcamentoMaximo(BigDecimal orcamentoMaximo) {
        this.orcamentoMaximo = orcamentoMaximo;
    }
}
