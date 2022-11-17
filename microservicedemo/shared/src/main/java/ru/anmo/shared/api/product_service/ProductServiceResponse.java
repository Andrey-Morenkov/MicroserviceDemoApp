package java.ru.anmo.shared.api.product_service;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductServiceResponse {
    private Long id;
    private String title;
}
