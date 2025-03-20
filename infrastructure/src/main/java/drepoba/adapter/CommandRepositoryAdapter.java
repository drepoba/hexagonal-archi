package drepoba.adapter;

import drepoba.domain.*;
import drepoba.domain.spi.CommandSpi;
import drepoba.exception.EntityNotFoundException;
import drepoba.mapper.CommandMapper;
import drepoba.model.CommandEntity;
import drepoba.model.CustomerEntity;
import drepoba.model.ProductEntity;
import drepoba.repository.CommandJpaRepository;
import drepoba.repository.CustomerJpaRepository;
import drepoba.repository.ProductJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommandRepositoryAdapter implements CommandSpi {

    private final CommandJpaRepository commandJpaRepository;
    private final CommandMapper commandMapper;
    private final CustomerJpaRepository customerJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public CommandRepositoryAdapter(CommandJpaRepository commandJpaRepository, CommandMapper commandMapper, CustomerJpaRepository customerJpaRepository, ProductJpaRepository productJpaRepository) {
        this.commandJpaRepository = commandJpaRepository;
        this.commandMapper = commandMapper;
        this.customerJpaRepository = customerJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Transactional
    @Override
    public void saveCommand(Command command) {
        log.info("[saveCommand] Saving command: {}", command);
        CommandEntity commandEntity = commandMapper.toEntity(command);
        commandJpaRepository.save(commandEntity);
        log.info("Saved command: {}", command);
    }

    @Override
    public void deleteCommand(Long commandId) {
        log.info("[deleteCommand] Deleting command: {}", commandId);
        commandJpaRepository.findById(commandId)
                .orElseThrow(()-> new EntityNotFoundException("Command", commandId.toString()));
        commandJpaRepository.deleteById(commandId);
    }

    @Override
    public Command getCommandById(Long commandId) {
        log.info("[getCommandById] getting command: {}", commandId);
        CommandEntity commandEntity=commandJpaRepository.findById(commandId)
                .orElseThrow(()-> new EntityNotFoundException("Command", commandId.toString()));
        return commandMapper.toDTO(commandEntity);
    }

    @Override
    public PageResult<Command> getAllCommands(int page, int size) {
        log.info("[getAllCommands] Getting commands");
        Page<CommandEntity> commandPage=commandJpaRepository.findAllByOrderByIdDesc(PageRequest.of(page,size));
        List<Command> customerMapper=commandMapper.toDTOList(commandPage.getContent());
        return new PageResult<>(
                customerMapper,
                (int)commandPage.getTotalElements(),
                commandPage.getTotalPages(),
                commandPage.getNumber(),
                commandPage.getSize()
        );
    }

    @Override
    public Command updateCommand(Command command) {
        log.info("[updateCommand] Updating command: {}", command);
        CommandEntity commandEntity=commandJpaRepository.findById(command.id())
                .orElseThrow(()->new EntityNotFoundException("Command",command.id().toString()));
        double totalPrice=commandJpaRepository.sumPrixUnitaire(command.id());
        CommandEntity commandUpdated=commandMapper.toEntity(command);
        commandEntity.setUpdated(new Date());
        commandEntity.setCustomer(commandUpdated.getCustomer());
        commandEntity.setTotalCost(totalPrice);
        commandJpaRepository.saveAndFlush(commandEntity);
        log.info("Updated command: {}", command);
        return commandMapper.toDTO(commandEntity);
    }

    @Override
    public PageResult<Command> getAllCommandsByCustomer(Customer customer, int page, int size) {
        log.info("[getAllCommandsByCustomerId] Getting commands by customer id: {}", customer);
        CustomerEntity customerEntity=customerJpaRepository.findById(customer.id())
                .orElseThrow(()-> new EntityNotFoundException("Customer", customer.id().toString()));
        Page<CommandEntity> commandPage=commandJpaRepository.findAllByCustomer(customerEntity,PageRequest.of(page,size));
        List<Command> customerMapper=commandMapper.toDTOList(commandPage.getContent());
        return new PageResult<>(
                customerMapper,
                (int)commandPage.getTotalElements(),
                commandPage.getTotalPages(),
                commandPage.getNumber(),
                commandPage.getSize()
        );
    }


    @Override
    public PageResult<Command> getAllCommandsByCustomerAndProduct(CustomerProduct customerProduct, int page, int size) {
        log.info("[getAllCommandsByCustomerIdAndProductId] Getting commands by customer and product : {}",
                customerProduct);
        CustomerEntity customerEntity=customerJpaRepository.findById(customerProduct.customer().id())
                .orElseThrow(()-> new EntityNotFoundException("Customer", customerProduct.customer().id().toString()));
        ProductEntity productEntity=productJpaRepository.findById(customerProduct.product().id()).orElseThrow(
                ()-> new EntityNotFoundException("Product", customerProduct.product().id().toString())
        );

        /*Page<CommandEntity> commandPage=commandJpaRepository.findAllByCustomerAndProduct(customerEntity,productEntity,PageRequest.of(page,size));
        List<Command> commandMapperPage=commandMapper.toDTOList(commandPage.getContent());
        return new PageResult<>(
                commandMapperPage,
                (int)commandPage.getTotalElements(),
                commandPage.getTotalPages(),
                commandPage.getNumber(),
                commandPage.getSize()
        );*/
        return null;
    }

    @Override
    public PageResult<Command> getAllCommandsByDate(Date dateDebut, Date dateFin, int page, int size) {
        log.info("[getAllCommandsByDate] Getting command by dateDebut by dateFin  : {},{}",
                dateDebut, dateFin);
        Page<CommandEntity> commandPage=commandJpaRepository.findAllByUpdatedIsBetween(dateDebut,dateFin,PageRequest.of(page,size));
        List<Command> commandMapperPage=commandMapper.toDTOList(commandPage.getContent());
        return new PageResult<>(
                commandMapperPage,
                (int)commandPage.getTotalElements(),
                commandPage.getTotalPages(),
                commandPage.getNumber(),
                commandPage.getSize()
        );
    }
}
