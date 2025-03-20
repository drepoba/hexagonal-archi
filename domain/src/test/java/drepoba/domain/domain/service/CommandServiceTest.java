package drepoba.domain.domain.service;

import drepoba.domain.PageResult;
import drepoba.domain.Command;
import drepoba.domain.service.CommandService;
import drepoba.domain.spi.CommandSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CommandServiceTest {
    @Mock
    private CommandSpi commandSpi;
    @InjectMocks
    private CommandService commandService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCommand() {
        Command command = new Command(1L,1L, 1L, new Date(), new Date());
        commandService.saveCommand(command);
        verify(commandSpi, times(1)).saveCommand(command); // Vérifie que commandSpi.saveCommand() a été appelé une fois
    }
    @Test
    void testSaveCommandNullCommandShouldThrowException(){
        assertThrows(NullPointerException.class,()->commandService.deleteCommand(null
        ));
    }

    @Test
    void testGetCommandById(){
        Long commandId = 1L;
        Command command = new Command(commandId,1L, 1L, new Date(), new Date());
        when(commandSpi.getCommandById(commandId)).thenReturn(command);
        Command result = commandService.getCommandById(commandId);
        assertNotNull(result);
        assertEquals(commandId,result.id());
        verify(commandSpi, times(1)).getCommandById(commandId);
    }
    @Test
    void testGetAllCommands() {
        int page=0;
        int size=10;
        Command command1=new Command(1L,1L,1L,new Date(), new Date());
        Command command2= new Command(2L,2L,2L,new Date(), new Date());
        PageResult<Command> pageResult = new PageResult<>(Arrays.asList(command1,command2),2,1,page,size);
        when(commandSpi.getAllCommands(page,size)).thenReturn(pageResult);
        PageResult<Command> result = commandService.getAllCommands(page,size);
        assertNotNull(result);
        assertEquals(pageResult,result);
        verify(commandSpi, times(1)).getAllCommands(page,size);
    }

    @Test
    void testUpdateCommand() {
        Command commandUpdate=new Command(1L,1L,1L,new Date(), new Date());
        when(commandSpi.updateCommand(commandUpdate)).thenReturn(commandUpdate);
        Command result = commandService.updateCommand(commandUpdate);
        assertNotNull(result);
        assertEquals(commandUpdate.id(),result.id());
        verify(commandSpi, times(1)).updateCommand(commandUpdate);
    }

    @Test
    void testDeleteCommand(){
        Long commandId=1L;
        commandService.deleteCommand(commandId);
        verify(commandSpi,times(1)).deleteCommand(commandId);
    }

    @Test
    void testUpdateCommandNullCommandShouldThrowException(){
        assertThrows(NullPointerException.class,()->commandService.updateCommand(null));
    }
    
    
}
