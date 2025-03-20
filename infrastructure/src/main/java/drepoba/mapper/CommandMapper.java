package drepoba.mapper;

import drepoba.domain.Command;
import drepoba.model.CommandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandMapper {

    Command toDTO(CommandEntity commandEntity);
    CommandEntity toEntity(Command command);
    List<CommandEntity> toEntityList(List<Command> commandList);
    List<Command> toDTOList(List<CommandEntity> commandEntityList);
}
