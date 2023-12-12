package kr.bb.order.entity.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import kr.bb.order.dto.request.orderForDelivery.OrderInfoByStore;
import kr.bb.order.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Builder
@Table(name = "order_delivery")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDelivery extends BaseEntity {
  @Id private String orderDeliveryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_group_id")
  private OrderGroup orderGroup;

  @Column(name = "store_id", nullable = false)
  private Long storeId;

  @Column(name = "delivery_id", nullable = false)
  private Long deliveryId;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "order_delivery_status", nullable = false)
  private OrderDeliveryStatus orderDeliveryStatus = OrderDeliveryStatus.PENDING;

  @Column(name = "order_delivery_total_amount", nullable = false)
  private Long orderDeliveryTotalAmount;

  @Column(name = "order_delivery_coupon_amount", nullable = false)
  private Long orderDeliveryCouponAmount;

  public static OrderDelivery toEntity(
      String orderDeliveryId,
      Long deliveryId,
      OrderGroup orderGroup,
      OrderInfoByStore orderInfoByStore) {
    return OrderDelivery.builder()
        .orderDeliveryId(orderDeliveryId)
        .orderGroup(orderGroup)
        .storeId(orderInfoByStore.getStoreId())
        .deliveryId(deliveryId)
        .orderDeliveryStatus(OrderDeliveryStatus.PENDING)
        .orderDeliveryTotalAmount(orderInfoByStore.getTotalAmount())
        .orderDeliveryCouponAmount(orderInfoByStore.getCouponAmount())
        .build();
  }
}