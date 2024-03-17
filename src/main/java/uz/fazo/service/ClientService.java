package uz.fazo.service;


import uz.fazo.payload.ClientDto;

import java.util.List;

public interface ClientService {


    List<ClientDto> getAll();

    ClientDto getById(long id);

    ClientDto create(ClientDto memberDto);

    void delete(long id);

}
