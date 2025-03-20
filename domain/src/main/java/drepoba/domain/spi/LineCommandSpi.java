package drepoba.domain.spi;


import drepoba.domain.LineCommand;
import drepoba.domain.PageResult;

public interface LineCommandSpi {
    void saveLineCommand(LineCommand LineCommand);

    void deleteLineCommand(Long LineCommand);

    LineCommand getLineCommandById(Long LineCommandId);

    PageResult<LineCommand> findAllByOrderByIdDesc(int page, int size);

    LineCommand updateLineCommand(LineCommand LineCommand);
}
