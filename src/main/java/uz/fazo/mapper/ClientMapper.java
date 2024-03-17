package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Client;
import uz.fazo.payload.ClientDto;

@Mapper
public interface ClientMapper {

    ClientDto clientToClientDto(Client client);

    Client clientDtoToClient(ClientDto clientDto);
}
