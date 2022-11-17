package java.ru.anmo.shared.api.customer_service;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerServiceResponse {
    private Long id;
    private String name;
}
