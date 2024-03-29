package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Material;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.MaterialMapper;
import uz.fazo.payload.MaterialDto;
import uz.fazo.repository.MaterialRepository;
import uz.fazo.service.MaterialService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public List<MaterialDto> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(materialMapper::materialToMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDto getById(long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return materialMapper.materialToMaterialDto(material);
    }

    @Override
    public MaterialDto create(MaterialDto materialDto) {
        Material material = materialMapper.materialDtoToMaterial(materialDto);
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.materialToMaterialDto(savedMaterial);
    }

    @Override
    public MaterialDto update(long id, MaterialDto materialDto) {
        Material material = materialRepository.findById(id).orElseThrow(NullPointerException::new);
        Material material1 = materialMapper.updateToMaterialDto(material, materialDto);
        Material save = materialRepository.save(material1);
        return materialMapper.materialToMaterialDto(save);
    }


    @Override
    public void delete(long id) {
        materialRepository.deleteById(id);
    }
}
