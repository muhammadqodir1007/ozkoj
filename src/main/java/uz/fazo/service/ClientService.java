package uz.fazo.service;


import uz.fazo.payload.ClientDto;

import java.util.List;

public interface ClientService {


    List<ClientDto> getAll();

    ClientDto getById(long id);

    ClientDto create(ClientDto memberDto);

    ClientDto update(long id, ClientDto clientDto);

    void delete(long id);

}
