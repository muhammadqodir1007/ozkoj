package uz.fazo.mapper;

import org.springframework.stereotype.Component;
import uz.fazo.entity.Client;
import uz.fazo.payload.ClientDto;

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientDto clientToClientDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder().id(client.getId())
                .address(client.getAddress())
                .fullName(client.getFullName())
                .birthDate(client.getBirthDate())
                .groupNumber(client.getGroupNumber())
                .state(client.getState())
                .passportNumber(client.getPassportNumber())
                .phoneNumber(client.getPhoneNumber())
                .passportSeries(client.getPassportSeries())
                .build();

    }

    @Override
    public Client clientDtoToClient(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return Client.builder().id(clientDto.getId())
                .address(clientDto.getAddress())
                .fullName(clientDto.getFullName())
                .birthDate(clientDto.getBirthDate())
                .groupNumber(clientDto.getGroupNumber())
                .state(clientDto.getState())
                .passportNumber(clientDto.getPassportNumber())
                .phoneNumber(clientDto.getPhoneNumber())
                .passportSeries(clientDto.getPassportSeries())
                .build();

    }
}
