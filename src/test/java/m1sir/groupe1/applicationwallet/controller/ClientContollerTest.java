package m1sir.groupe1.applicationwallet.test;

import m1sir.groupe1.applicationwallet.controller.ClientController;
import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientControllerTest {
    @Mock
    ClientService clientService;

    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientService);
    }

    @Test
    void testGetAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "assane", "doum", "azou@gmail.com", "motdepasse1"));
        clients.add(new Client(2, "abdou", "fall", "abdou@gmail.com", "motdepasse1"));
        when(clientService.getClients()).thenReturn(clients);
        List<Client> client = clientService.getClients();

        ResponseEntity<List<Client>> response = ResponseEntity.ok(client);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    void testGetClientById() {

        int clientId = 1;
        Client mockClient = new Client(clientId, "azou", "doum", "azou@gmail.com", "motdepasse1");
        when(clientService.findUserByUserId(clientId)).thenReturn(mockClient);
        Client cl=clientService.findUserByUserId(clientId);
        ResponseEntity.ok(cl);
        ResponseEntity<Client> response = ResponseEntity.ok(cl);;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockClient, response.getBody());
    }


}
