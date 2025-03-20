package drepoba.adapter;
import drepoba.domain.LineCommand;
import drepoba.domain.PageResult;
import drepoba.domain.spi.LineCommandSpi;
import drepoba.exception.EntityNotFoundException;
import drepoba.mapper.LineCommandMapper;
import drepoba.model.LineCommandEntity;
import drepoba.repository.LineCommandJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class LineCommandRepositoryAdapter implements LineCommandSpi {

    private final LineCommandJpaRepository lineCommandJpaRepository;
    private final LineCommandMapper lineCommandMapper;

    public LineCommandRepositoryAdapter(LineCommandJpaRepository lineCommandJpaRepository, LineCommandMapper lineCommandMapper) {
        this.lineCommandJpaRepository = lineCommandJpaRepository;
        this.lineCommandMapper = lineCommandMapper;
    }

    @Transactional
    @Override
    public void saveLineCommand(LineCommand lineCommand) {
        log.info("[saveLineCommand] Saving line command: {}", lineCommand);
        Objects.requireNonNull(lineCommand,"Le lineCommand ne peut pas être null");
        LineCommandEntity lineCommandEntity = lineCommandMapper.toEntity(lineCommand);
        lineCommandJpaRepository.save(lineCommandEntity);
        log.info("[saveLineCommand] Saved line command: {}", lineCommand);
    }

    @Override
    public void deleteLineCommand(Long lineCommandId) {
        log.info("[deleteLineCommand] Deleting line command: {}", lineCommandId);
        LineCommandEntity  lineCommandEntity = lineCommandJpaRepository.findById(lineCommandId)
                .orElseThrow(()-> new EntityNotFoundException("lineCommand", lineCommandId.toString()));
        lineCommandJpaRepository.delete(lineCommandEntity);
        // TODO verify if foreignKEy is null
        log.info("[deleteLineCommand] Deleted line command: {}", lineCommandId);
    }

    @Override
    public LineCommand getLineCommandById(Long lineCommandId) {
        log.info("[getLineCommandById] Deleting lineCommand: {}", lineCommandId);
        LineCommandEntity lineCommandEntity=lineCommandJpaRepository.findById(lineCommandId)
                .orElseThrow(()->  new EntityNotFoundException("lineCommand",lineCommandId.toString())
                );
        return lineCommandMapper.toDTO(lineCommandEntity);
    }


    @Transactional(readOnly =true)
    @Override
    public PageResult<LineCommand> findAllByOrderByIdDesc(int page, int size) {
        log.info("[findAllByOrderByIdDesc] Getting lineCommand list");
        Page<LineCommandEntity> lineCommandPage=lineCommandJpaRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));
        List<LineCommand> lineCommands=lineCommandMapper.toDTOList(lineCommandPage.getContent());
        return new PageResult<>(
                lineCommands,
                (int)lineCommandPage.getTotalElements(),
                lineCommandPage.getTotalPages(),
                lineCommandPage.getNumber(),
                lineCommandPage.getSize()
        );
    }

    @Transactional
    @Override
    public LineCommand updateLineCommand(LineCommand lineCommand) {
        log.info("[updateLineCommand] Updating LineCommand: {}", lineCommand);
        Objects.requireNonNull(lineCommand,"Le lineCommand ne peut pas être null");
        LineCommandEntity lineCommandEntity=lineCommandJpaRepository.findById(lineCommand.id())
                .orElseThrow(()->new EntityNotFoundException("LineCommand",lineCommand.id().toString()));

        lineCommandEntity.setCommand(lineCommandEntity.getCommand());
        lineCommandEntity.setUpdated(new Date());
        lineCommandJpaRepository.save(lineCommandEntity);
        log.info("[updateLineCommand] Updated lineCommand: {}", lineCommand);
        return lineCommandMapper.toDTO(lineCommandEntity);
    }
}
