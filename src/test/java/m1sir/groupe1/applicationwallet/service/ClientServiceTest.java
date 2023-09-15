package m1sir.groupe1.applicationwallet.service;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.repository.ClientRepository;
import m1sir.groupe1.applicationwallet.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        clientRepository = Mockito.mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void testLireOUCreerClientExistant() {


        Client client = new Client();
        client.setEmail("azou@gmail.com");
        when(clientRepository.findByEmail("azou@gmail.com")).thenReturn(client);
        Client result = clientService.lireOUCreer(client);
        assertEquals(client, result);
    }

    @Test
    void testLireOUCreerClientInexistant() {
        Client client = new Client();
        client.setEmail("azou@gmail.com");
        when(clientRepository.findByEmail("azou@gmail.com")).thenReturn(null);
        when(clientRepository.save(client)).thenReturn(client);
        Client result = clientService.lireOUCreer(client);
        assertEquals(client, result);
    }
}