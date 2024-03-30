package uz.fazo.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;
import uz.fazo.service.ClientService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ClientDto>> getAllClientsByUserId(@PathVariable int id) {
        List<ClientDto> clients = clientService.getAllByUserId(id);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable long id) {
        ClientDto client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportClientsToExcel() {
        try {
            byte[] excelBytes = clientService.exportMembersToExcel();
            ByteArrayResource resource = new ByteArrayResource(excelBytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clients.xlsx")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/upload")
    public ResponseEntity<List<ClientDto>> uploadClientsFromFile(@RequestParam("file") MultipartFile file) {
        try {
            List<ClientDto> clients = clientService.createFromFile(file);
            return ResponseEntity.ok(clients);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        ClientDto createdClient = clientService.create(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable int id, @RequestBody ClientDto clientDto) {
        ClientDto update = clientService.update(id, clientDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
