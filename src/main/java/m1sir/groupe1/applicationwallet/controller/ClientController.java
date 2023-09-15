package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
     @GetMapping("/{userID}")
    public Client getClientByUserID(@PathVariable int userID){
        return clientService.findUserByUserId(userID);
    }

    @GetMapping("/all")
    public List<Client> getAllClients(){
         return clientService.getClients();
    }

}
