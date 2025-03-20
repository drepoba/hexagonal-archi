package drepoba.domain.service;

import drepoba.domain.LineCommand;
import drepoba.domain.PageResult;
import drepoba.domain.api.LineCommandApi;
import ddd.DomainService;
import drepoba.domain.spi.LineCommandSpi;

import java.util.Objects;

@DomainService
public class LineCommandService implements LineCommandApi {
    private final LineCommandSpi lineCommandSpi;

    public LineCommandService(LineCommandSpi lineCommandSpi) {
        this.lineCommandSpi = lineCommandSpi;
    }


    @Override
    public void saveLineCommand(LineCommand lineCommand) {
        Objects.requireNonNull(lineCommand, "[saveLineCommand] lineCommand cannot be null");
        lineCommandSpi.saveLineCommand(lineCommand);
    }

    @Override
    public void deleteLineCommand(Long lineCommandId) {
        Objects.requireNonNull(lineCommandId, "[deleteLineCommand] lineCommandId cannot be null");
        lineCommandSpi.deleteLineCommand(lineCommandId);
    }

    @Override
    public LineCommand getLineCommandById(Long lineCommandId) {
        Objects.requireNonNull(lineCommandId, "[getLineCommandById] lineCommandId cannot be null");
        return lineCommandSpi.getLineCommandById(lineCommandId);
    }

    @Override
    public PageResult<LineCommand> findAllByOrderByIdDesc(int page, int size) {
        return lineCommandSpi.findAllByOrderByIdDesc(page, size);
    }

    @Override
    public LineCommand updateLineCommand(LineCommand LineCommand) {
        Objects.requireNonNull(LineCommand, "[updateLineCommand] LineCommand cannot be null");
        return lineCommandSpi.updateLineCommand(LineCommand);
    }
}
