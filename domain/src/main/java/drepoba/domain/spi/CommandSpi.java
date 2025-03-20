package drepoba.domain.spi;
import drepoba.domain.*;

import java.util.Date;

public interface CommandSpi {
    void saveCommand(Command command);
    void deleteCommand(Long commandId);
    Command getCommandById(Long commandId);
    PageResult<Command> getAllCommands(int page, int size);
    Command updateCommand(Command command);
    PageResult<Command> getAllCommandsByCustomer(Customer customer, int page, int size);
    PageResult<Command> getAllCommandsByCustomerAndProduct(CustomerProduct commandProduct, int page, int size);
    PageResult<Command> getAllCommandsByDate(Date dateDebut, Date dateFin, int page, int size);
}
