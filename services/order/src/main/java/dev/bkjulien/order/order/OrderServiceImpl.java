package dev.bkjulien.order.order;

import dev.bkjulien.order.customer.CustomerClient;
import dev.bkjulien.order.exception.BusinessException;
import dev.bkjulien.order.orderline.OrderLineRequest;
import dev.bkjulien.order.orderline.OrderLineService;
import dev.bkjulien.order.product.ProductClient;
import dev.bkjulien.order.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    @Override
    public Integer createOrder(OrderRequest request) {
        //chech the customer ---> OpenFeign
        var customer = this.customerClient.getCustomer(request.customerId())
                .orElseThrow(()-> new BusinessException("Can't create order:: No customer exists with the provided ID"));

        //purchase the products ---> product-ms (RestTemplate)
        this.productClient.purchaseProduct(request.products());

        //persist order
        var order = repository.save(mapper.toOrder(request));

        //persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.createOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start payment process


        //send the order confirmation ---> notification-ms (kafka)


        return 0;
    }
}
