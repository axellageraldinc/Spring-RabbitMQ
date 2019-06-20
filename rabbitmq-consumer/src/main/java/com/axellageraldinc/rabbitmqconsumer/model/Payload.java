package com.axellageraldinc.rabbitmqconsumer.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private String message;
}
