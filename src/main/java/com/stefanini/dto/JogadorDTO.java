package com.stefanini.dto;

import com.stefanini.entity.Jogador;
import com.stefanini.entity.Stefamon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JogadorDTO {

    private Long id;
    private String nickname;
    private String password;
    private BigDecimal saldo;
    private List<Stefamon> stefamons = new ArrayList<>();

    public JogadorDTO() {
    }

    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.nickname = jogador.getNickname();
        this.password = jogador.getPassword();
        this.saldo = jogador.getSaldo();
        this.stefamons = jogador.getStefamons();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Stefamon> getStefamons() {
        return stefamons;
    }

    public void setStefamons(List<Stefamon> stefamons) {
        this.stefamons = stefamons;
    }
}
