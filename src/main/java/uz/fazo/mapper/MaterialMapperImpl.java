package uz.fazo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.fazo.entity.Material;
import uz.fazo.payload.MaterialDto;
import uz.fazo.user.User;
import uz.fazo.user.UserRepository;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class MaterialMapperImpl implements MaterialMapper {

    UserRepository userRepository;

    @Override
    public Material materialDtoToMaterial(MaterialDto materialDto) {
        User user = userRepository.findById(materialDto.getUserId()).orElseThrow(NullPointerException::new);
        return Material.builder().name(materialDto.getName())
                .createdAt(LocalDateTime.now())
                .user(user)
                .link(materialDto.getLink()).build();
    }

    @Override
    public MaterialDto materialToMaterialDto(Material material) {
        return MaterialDto.builder().id(material.getId())
                .userId(material.getUser().getId())
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
