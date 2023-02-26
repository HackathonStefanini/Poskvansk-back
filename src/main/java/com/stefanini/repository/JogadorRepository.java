package com.stefanini.repository;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Jogador;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

@ApplicationScoped
public class JogadorRepository extends GenericDAO<Jogador, Long> {


    public Jogador login(Jogador jogador) {

        return createQuery("SELECT j FROM Jogador j WHERE j.nickname = :nickname AND j.password = :senha")
                .setParameter("nickname", jogador.getNickname())
                .setParameter("senha", jogador.getPassword())
                .getSingleResult();
    }

    public Jogador buscarPorNickname(String nickname) {
        try {
            return createQuery("SELECT j FROM Jogador j WHERE j.nickname = :nickname")
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
