package uz.fazo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import uz.fazo.entity.Client;
import uz.fazo.payload.ClientDto;

@Mapper
public interface ClientMapper {

    ClientDto clientToClientDto(Client client);

    Client clientDtoToClient(ClientDto clientDto);


    @Mappings({
            @Mapping(target = "id", ignore = true) // Ignore mapping id from entity to DTO
    })
    Client updateClientFromDto(ClientDto clientDto, @MappingTarget Client client);
}
