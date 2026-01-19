package br.com.rinhadeconcurseiro.service;

import br.com.rinhadeconcurseiro.dto.response.QuestaoResponse;
import br.com.rinhadeconcurseiro.entity.Questao;
import br.com.rinhadeconcurseiro.exception.ResourceNotFoundException;
import br.com.rinhadeconcurseiro.mapper.QuestaoMapper;
import br.com.rinhadeconcurseiro.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final QuestaoMapper questaoMapper;

    @Transactional(readOnly = true)
    public QuestaoResponse buscarPorId(Long id) {

        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questão não encontrada"));

        return questaoMapper.toResponse(questao);
    }

    @Transactional(readOnly = true)
    public List<QuestaoResponse> listarPorMateria(Long materiaId) {

        return questaoRepository.findByMateriaIdAndAtivoTrue(materiaId).stream()
                .map(questaoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QuestaoResponse> listarPorAssunto(Long assuntoId) {

        return questaoRepository.findByAssuntoId(assuntoId).stream()
                .map(questaoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean existePorIdTec(String idTec) {

        return questaoRepository.existsByIdTec(idTec);
    }
}
