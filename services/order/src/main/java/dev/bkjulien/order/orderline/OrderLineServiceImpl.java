package dev.bkjulien.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService{
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    @Override
    public Integer createOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }
}
