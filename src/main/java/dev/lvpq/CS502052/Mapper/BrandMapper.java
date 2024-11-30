package dev.lvpq.CS502052.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import dev.lvpq.CS502052.Dto.Response.BrandListing;
import dev.lvpq.CS502052.Entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(target = "expirationDate", source = "expirationDate", qualifiedByName = "localDateToString")
    @Mapping(target = "registrationDate", source = "registrationDate", qualifiedByName = "localDateToString")
    BrandListing toBrandListing(Brand brand);

    @Named("localDateToString")
    default String localDateToString(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
} 
