package drepoba.domain.service;

import drepoba.domain.*;
import drepoba.domain.api.CommandApi;
import ddd.DomainService;
import drepoba.domain.spi.CommandSpi;
import java.util.Date;
import java.util.Objects;

@DomainService
public class CommandService implements CommandApi {
   private final CommandSpi commandSpi;

    public CommandService(CommandSpi commandSpi) {
        this.commandSpi = commandSpi;
    }

    @Override
    public void saveCommand(Command command) {
        Objects.requireNonNull(command, "command cannot be null");
        commandSpi.saveCommand(command);
    }

    @Override
    public void deleteCommand(Long commandId) {
        Objects.requireNonNull(commandId, "commandId cannot be null");
        commandSpi.deleteCommand(commandId);
    }

    @Override
    public Command getCommandById(Long commandId) {
        Objects.requireNonNull(commandId, "commandId cannot be null");
        return commandSpi.getCommandById(commandId);
    }

    @Override
    public PageResult<Command> getAllCommands(int page, int size) {

        return commandSpi.getAllCommands(page,size);
    }

    @Override
    public Command updateCommand(Command command) {
        Objects.requireNonNull(command, "command cannot be null");
        return commandSpi.updateCommand(command);
    }

    @Override
    public PageResult<Command> getAllCommandsByCustomer(Customer customer, int page, int size) {
        Objects.requireNonNull(commandSpi, "customerId cannot be null");
        return commandSpi.getAllCommandsByCustomer(customer, page, size);
    }

    @Override
    public PageResult<Command> getAllCommandsByCustomerAndProduct(CustomerProduct customerProduct, int page, int size) {
        Objects.requireNonNull(customerProduct, "[getAllCommandsByCustomerAndProduct] customerProduct cannot be null");
        return commandSpi.getAllCommandsByCustomerAndProduct(customerProduct, page, size);
    }


    @Override
    public PageResult<Command> getAllCommandsByDate(Date dateDebut, Date dateFin,int page, int size) {
        Objects.requireNonNull(dateDebut, "[getAllCommandsByDate] dateDebut cannot be null");
        Objects.requireNonNull(dateFin, "dateFin cannot be null");
        return commandSpi.getAllCommandsByDate(dateDebut,dateFin,page,size);
    }
}
