package drepoba.controllers;

import drepoba.domain.*;
import drepoba.domain.api.CommandApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/command")
public class CommandController {
    private final CommandApi commandApi;

    public CommandController(CommandApi commandApi) {
        this.commandApi = commandApi;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCommand(@RequestBody Command command) {
        commandApi.saveCommand(command);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Response-Header", "CommandSavedSuccessfully");
        headers.add("X-Request-ID", UUID.randomUUID().toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Command> getCommand(@PathVariable Long id) {
        return ResponseEntity.ok().body(commandApi.getCommandById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCommand(@PathVariable Long id) {
        commandApi.deleteCommand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<Command>> getCommands(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Command> commands=commandApi.getAllCommands(page, size);
        return ResponseEntity.ok(commands);
    }

    @PutMapping("/update")
    public ResponseEntity<Command> updateCommand(@RequestBody Command updatedCommand) {
        Command updated = commandApi.updateCommand(updatedCommand);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/getcommandbycustomer")
    public ResponseEntity<PageResult<Command>> getAllCommandsByCustomer(@RequestBody Customer customer,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Command> commands=commandApi.getAllCommandsByCustomer(customer,page, size);
        return ResponseEntity.ok(commands);
    }

    @PostMapping("/getcommandbycustomerandproduct")
    public ResponseEntity<PageResult<Command>> getAllCommandsByCustomerAndProduct(@RequestBody CustomerProduct customerProduct, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Command> commands=commandApi.getAllCommandsByCustomerAndProduct(customerProduct,page, size);
        return ResponseEntity.ok(commands);
    }

    @GetMapping("/getAllCommandsByDate")
    public ResponseEntity<PageResult<Command>> getAllCommandsByDate(@RequestParam Date datedebut,@RequestParam Date dateFin,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Command> commands=commandApi.getAllCommandsByDate(datedebut,dateFin,page, size);
        return ResponseEntity.ok(commands);
    }

}
