package com.bool.market.persistence.mapper;

import com.bool.market.domain.Category;
import com.bool.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration //se hace un mapeo inverso al que ya se tiene
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
