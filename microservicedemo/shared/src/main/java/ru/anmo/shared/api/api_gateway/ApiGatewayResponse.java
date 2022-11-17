package java.ru.anmo.shared.api.api_gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ApiGatewayResponse {
    private final String customerName;
    private final List<OrderInfo> orders;

    @AllArgsConstructor
    @Getter
    public static class OrderInfo {
        private final Long id;
        private final Long productId;
        private final String productTitle;
    }
}
