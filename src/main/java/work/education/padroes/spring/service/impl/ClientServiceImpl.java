package work.education.padroes.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.education.padroes.spring.model.Adress;
import work.education.padroes.spring.model.Client;
import work.education.padroes.spring.repositories.AdressRepository;
import work.education.padroes.spring.repositories.ClientRepository;
import work.education.padroes.spring.service.ClientService;
import work.education.padroes.spring.service.ViaCepService;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Client> buscarTodos() {
        return clientRepository.findAll();
    }

    @Override
    public Client buscarPorId(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void inserir(Client client) {
        clientSaveWithCep(client);
    }

    @Override
    public void atualizar(Long id, Client client) {
        Optional<Client> clientBd = clientRepository.findById(id);
        if(clientBd.isPresent()) {
            clientSaveWithCep(client);
        }
    }

    @Override
    public void deletar(Long id) {
        clientRepository.deleteById(id);
    }

    private void clientSaveWithCep(Client client) {
        String cep = client.getAdress().getCep();
        Adress adress = adressRepository.findById(cep).orElseGet(() -> {
            Adress newAdress = viaCepService.consultarCep(cep);
            adressRepository.save(newAdress);
            return newAdress;
        });
        client.setAdress(adress);
        clientRepository.save(client);
    }
}
