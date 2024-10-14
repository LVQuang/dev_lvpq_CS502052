package dev.lvpq.CS502052.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Brand {
    private String name;
    private String thumbnail_url;
    private Boolean status;
    public Brand(String name, String thumbnail_url, Boolean status){
        this.name = name;
        this.thumbnail_url = thumbnail_url;
        this.status = status;
    }
}
