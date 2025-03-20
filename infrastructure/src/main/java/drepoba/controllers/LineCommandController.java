package drepoba.controllers;
import drepoba.domain.LineCommand;
import drepoba.domain.PageResult;
import drepoba.domain.api.LineCommandApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/linecommand")
public class LineCommandController {
    private final LineCommandApi lineCommandApi;

    public LineCommandController(LineCommandApi lineCommandApi) {
        this.lineCommandApi = lineCommandApi;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveLineCommand(@RequestBody LineCommand lineCommand) {
        lineCommandApi.saveLineCommand(lineCommand);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Response-Header", "LineCommandSavedSuccessfully");
        headers.add("X-Request-ID", UUID.randomUUID().toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineCommand> getLineCommand(@PathVariable Long id) {
        return ResponseEntity.ok().body(lineCommandApi.getLineCommandById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteLineCommand(@PathVariable Long id) {
        lineCommandApi.deleteLineCommand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<LineCommand>> getLineCommand(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        PageResult<LineCommand> lineCommands=lineCommandApi.findAllByOrderByIdDesc(page, size);
        return ResponseEntity.ok(lineCommands);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLineCommand(@RequestBody LineCommand updatedLineCommand) {
        LineCommand updated = lineCommandApi.updateLineCommand(updatedLineCommand);
        return ResponseEntity.ok(updated);
    }
}
