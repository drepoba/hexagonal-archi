package drepoba.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{
    private final String entityName;
    private final String entityId;
    public EntityNotFoundException(String entityName, String entityId) {
        super(entityName + "avec l'ID" + entityId + "non trouvé");
        this.entityName = entityName;
        this.entityId = entityId;
    }
}
