package m1sir.groupe1.applicationwallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "client")
@Tag(name = "Client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void creer(@RequestBody Client client){
        this.clientService.creer(client);

    }

    @Operation(
            description = "First Get endpoint for Client",
            summary = "It gives Customer by Id"
    )
     @GetMapping("/{userID}")
    public Client getClientByUserID(@PathVariable int userID){
        return clientService.findUserByUserId(userID);
    }


    @Operation(
            description = "Second Get endpoint for Client",
            summary = "It gives all Customers"
    )
    @GetMapping("/all")
    public List<Client> getAllClients(){
         return clientService.getClients();
    }

}
