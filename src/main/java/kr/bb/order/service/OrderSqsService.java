package kr.bb.order.service;

import javax.persistence.EntityNotFoundException;
import kr.bb.order.dto.ProductStatusChangeDto;
import kr.bb.order.entity.OrderDeliveryProduct;
import kr.bb.order.repository.OrderDeliveryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderSqsService {
  private final OrderDeliveryProductRepository orderDeliveryProductRepository;

  @Transactional
  public void updateOrderDeliveryReview(ProductStatusChangeDto statusChangeDto) {
    OrderDeliveryProduct orderDeliveryProduct =
        orderDeliveryProductRepository
            .findById(statusChangeDto.getId())
            .orElseThrow(EntityNotFoundException::new);
    String status = statusChangeDto.getStatus();
    orderDeliveryProduct.updateReviewStatus(status);
  }

  @Transactional
  public void updateOrderDeliveryCard(ProductStatusChangeDto statusChangeDto) {
    OrderDeliveryProduct orderDeliveryProduct =
        orderDeliveryProductRepository
            .findById(statusChangeDto.getId())
            .orElseThrow(EntityNotFoundException::new);
    String status = statusChangeDto.getStatus();
    orderDeliveryProduct.updateCardStatus(status);
  }
}
