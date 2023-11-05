package com.gorkemersizer.userservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("country")
public class Country {
    @Id
    private String id;
    private String countryCode;
    private String currency;
}
