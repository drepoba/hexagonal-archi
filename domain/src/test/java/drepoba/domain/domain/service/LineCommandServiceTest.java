package drepoba.domain.domain.service;

import drepoba.domain.PageResult;
import drepoba.domain.LineCommand;
import drepoba.domain.service.LineCommandService;
import drepoba.domain.spi.LineCommandSpi;
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

public class LineCommandServiceTest {
    @Mock
    private LineCommandSpi lineCommandSpi; // Mock de la dependance
    @InjectMocks
    private LineCommandService lineCommandService; // Service à tester

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLineCommand() {
        LineCommand lineCommand = new LineCommand(1L,1L, 1L,100,new Date());
        lineCommandService.saveLineCommand(lineCommand);
        verify(lineCommandSpi, times(1)).saveLineCommand(lineCommand); // Vérifie que LineCommandSpi.saveLineCommand() a été appelé une fois
    }
    @Test
    void testSaveLineCommandNullLineCommandShouldThrowException(){
        assertThrows(NullPointerException.class,()->lineCommandService.deleteLineCommand(null
        ));
    }

    @Test
    void testGetLineCommandById(){
        Long lineCommandId = 1L;
        LineCommand LineCommand = new LineCommand(1L,1L, 1L,100,new Date());
        when(lineCommandSpi.getLineCommandById(lineCommandId)).thenReturn(LineCommand);
        LineCommand result = lineCommandService.getLineCommandById(lineCommandId);
        assertNotNull(result);
        assertEquals(lineCommandId,result.id());
        verify(lineCommandSpi, times(1)).getLineCommandById(lineCommandId);
    }
    @Test
    void testGetAllLineCommands() {
        int page=0;
        int size=10;
        LineCommand lineCommand1=new LineCommand(1L,1L, 1L,100,new Date());
        LineCommand lineCommand2= new LineCommand(2L,2L, 2L,100,new Date());
        PageResult<LineCommand> pageResult = new PageResult<>(Arrays.asList(lineCommand1,lineCommand2),2,1,page,size);
        when(lineCommandSpi.findAllByOrderByIdDesc(page,size)).thenReturn(pageResult);
        PageResult<LineCommand> result = lineCommandService.findAllByOrderByIdDesc(page,size);
        assertNotNull(result);
        assertEquals(pageResult,result);
        verify(lineCommandSpi, times(1)).findAllByOrderByIdDesc(page,size);
    }

    @Test
    void testUpdateLineCommand() {
        LineCommand lineCommandUpdate= new LineCommand(1L,1L, 1L,100,new Date());
        when(lineCommandSpi.updateLineCommand(lineCommandUpdate)).thenReturn(lineCommandUpdate);
        LineCommand result = lineCommandService.updateLineCommand(lineCommandUpdate);
        assertNotNull(result);
        assertEquals(lineCommandUpdate.id(),result.id());
        verify(lineCommandSpi, times(1)).updateLineCommand(lineCommandUpdate);
    }

    @Test
    void testDeleteLineCommand(){
        Long lineCommandId=1L;
        lineCommandService.deleteLineCommand(lineCommandId);
        verify(lineCommandSpi,times(1)).deleteLineCommand(lineCommandId);
    }

    @Test
    void testUpdateLineCommandNullLineCommandShouldThrowException(){
        assertThrows(NullPointerException.class,()->lineCommandService.updateLineCommand(null));
    }
}
