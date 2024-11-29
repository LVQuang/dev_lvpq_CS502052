package dev.lvpq.CS502052.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.lvpq.CS502052.Dto.Response.BrandListing;
import dev.lvpq.CS502052.Mapper.BrandMapper;
import dev.lvpq.CS502052.Repository.BrandRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class BrandService {
    BrandRepository brandRepository;
    BrandMapper brandMapper;

    public List<BrandListing> getBrandListings() {
        return brandRepository.findAll().stream()
            .map(brandMapper::toBrandListing)
            .collect(Collectors.toList());
    }
}
