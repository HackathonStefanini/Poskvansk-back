package com.stefanini.entity;

import com.stefanini.dto.JogadorDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "tb_jogador")
public class Jogador {

    @Id
    @Column(name = "id_jogador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "É necessário um nickname!")
    private String nickname;

    @Column
    @NotBlank(message = "É necessária uma senha!")
//    // NÃO IMPLEMENTAR SIZE AQUI PORQUE A SENHA VAI SER CRIPTOGRAFADA
    private String password;

    @Column()
    private BigDecimal saldo;


    @ManyToMany
    @JoinTable(name = "Jogador_Stefamon",
            joinColumns = {@JoinColumn(name = "IdJogador")},
            inverseJoinColumns = {@JoinColumn(name = "IdStefamon")})
    private List<Stefamon> stefamons = new ArrayList<>();

    public Jogador() {
    }

    public Jogador(JogadorDTO jogadorDTO) {
        this.nickname = jogadorDTO.getNickname();
        this.password = jogadorDTO.getPassword();
        this.saldo = jogadorDTO.getSaldo();
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
