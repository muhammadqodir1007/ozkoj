package uz.fazo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.fazo.entity.Material;
import uz.fazo.payload.MaterialDto;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    @Mapping(target = "id", ignore = true)
    Material materialDtoToMaterial(MaterialDto materialDto);

    MaterialDto materialToMaterialDto(Material material);

    Material updateToMaterialDto(Material material, MaterialDto materialDto);


}
