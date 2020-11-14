package com.vv.vvspringbootreactivemongo.management;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document
@ToString
public class Order {

    @Id
    private String _id;
    private boolean process;

}

