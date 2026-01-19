package br.com.rinhadeconcurseiro.repository;

import br.com.rinhadeconcurseiro.entity.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestaoRepository
        extends JpaRepository<Questao, Long> {

    Optional<Questao> findByIdTec(String idTec);

    boolean existsByIdTec(String idTec);

    List<Questao> findByMateriaId(Long materiaId);

    List<Questao> findByMateriaIdAndAtivoTrue(Long materiaId);

    List<Questao> findByAssuntoId(Long assuntoId);

    @Query("SELECT COUNT(q) FROM Questao q WHERE q.materia.id = :materiaId AND q.ativo = true")
    Long countByMateriaIdAndAtivoTrue(@Param("materiaId") Long materiaId);

    @Query("SELECT COUNT(q) FROM Questao q WHERE q.assunto.id = :assuntoId AND q.ativo = true")
    Long countByAssuntoIdAndAtivoTrue(@Param("assuntoId") Long assuntoId);

}
