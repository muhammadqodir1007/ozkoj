package uz.fazo.mapper;

import org.springframework.stereotype.Component;
import uz.fazo.entity.Material;
import uz.fazo.payload.MaterialDto;

import java.time.LocalDateTime;

@Component
public class MaterialMapperImpl implements MaterialMapper {


    @Override
    public Material materialDtoToMaterial(MaterialDto materialDto) {
        return Material.builder().name(materialDto.getName())
                .createdAt(LocalDateTime.now())
                .link(materialDto.getLink()).build();
    }

    @Override
    public MaterialDto materialToMaterialDto(Material material) {
        return MaterialDto.builder().id(material.getId())
                .createdAt(material.getCreatedAt())
                .link(material.getLink())
                .name(material.getName()).build();
    }

    @Override
    public Material updateToMaterialDto(Material material, MaterialDto materialDto) {
        if (material == null || materialDto == null) return null;

        if (materialDto.getLink() != null) {
            material.setLink(materialDto.getLink());
        }
        if (materialDto.getName() != null) {
            material.setName(materialDto.getName());
        }
        return material;


    }

}
