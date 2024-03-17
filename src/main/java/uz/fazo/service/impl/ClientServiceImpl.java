package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Client;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.ClientMapper;
import uz.fazo.payload.ClientDto;
import uz.fazo.repository.ClientRepository;
import uz.fazo.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::clientToClientDto)
                .collect(Collectors.toList());

    }

    @Override
    public ClientDto getById(long id) {
        return clientMapper.clientToClientDto(clientRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        return clientMapper.clientToClientDto(clientRepository.save(client));
    }

    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }
}
