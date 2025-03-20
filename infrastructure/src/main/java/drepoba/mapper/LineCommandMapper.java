package drepoba.mapper;

import drepoba.domain.LineCommand;
import drepoba.model.LineCommandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineCommandMapper {
    LineCommand toDTO(LineCommandEntity lineCommandEntity);
    LineCommandEntity toEntity(LineCommand lineCommand);
    List<LineCommandEntity> toEntityList(List<LineCommand> lineCommandList);
    List<LineCommand> toDTOList(List<LineCommandEntity> lineCommandEntityList);
}
