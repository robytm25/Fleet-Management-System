package app.vehiclemanagement.parameters.services;

import app.vehiclemanagement.parameters.models.Client;
import app.vehiclemanagement.parameters.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }


    public Client getById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    //Delete Client
    public void delete(int id) {
        clientRepository.deleteById(id);
    }

    //Update Client
    public void save(Client client) {
        clientRepository.save(client);
    }
}
