package com.stefanini.service;

import com.stefanini.dto.JogadorDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.exceptions.RegraDeNegocioException;
import com.stefanini.repository.JogadorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class JogadorService {

    @Inject
    JogadorRepository jogadorRepository;


    //Faz o SHA-256 da senha e passa para Base64
    public String hashSenha(String senha) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = senha.getBytes(StandardCharsets.UTF_8);
        byte[] hash = digest.digest(bytes);
        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        return hashBase64;
    }
    public Jogador salvar(JogadorDTO jogadorDTO) {

        // CRIPTOGRAFA SENHA
//        jogadorDTO.setPassword(hashSenha(jogadorDTO.getPassword()));

        // VERIFICA SE O NICKNAME JÁ EXISTE
        if(Objects.nonNull(jogadorRepository.buscarPorNickname(jogadorDTO.getNickname()))) {
            throw new RegraDeNegocioException("O nickname " + jogadorDTO.getNickname() + " já está em uso", Response.Status.BAD_REQUEST);
        }

        Jogador jogador = new Jogador(jogadorDTO);
        jogadorRepository.save(jogador);
        return jogador;
    }

    public Jogador pegarPorId(Long id) {
        var jogador = jogadorRepository.findById(id);
        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + id, Response.Status.NOT_FOUND);
        }
        return jogador;
    }

    public void alterar(Jogador jogador) {

        jogadorRepository.update(jogador);
    }

    public void deletar(Long id) {
        jogadorRepository.delete(id);
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.listAll();
    }

    public Jogador login(Jogador jogadorDTO) throws Exception {

        Jogador jogador = jogadorRepository.login(jogadorDTO);

        if(Objects.isNull(jogador)) {
            throw new RegraDeNegocioException("Ocorreu um erro ao buscar o Jogador de id " + jogadorDTO.getId(), Response.Status.NOT_FOUND);
        }

        return jogador;
    }
}
