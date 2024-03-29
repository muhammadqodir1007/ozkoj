package uz.fazo.service;

import uz.fazo.payload.MaterialDto;

import java.util.List;

public interface MaterialService {
    List<MaterialDto> getAll();

    MaterialDto getById(long id);

    MaterialDto create(MaterialDto materialDto);

    MaterialDto update(long id, MaterialDto materialDto);

    void delete(long id);
}
