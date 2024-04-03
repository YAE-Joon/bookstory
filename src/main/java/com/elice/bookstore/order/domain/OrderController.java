package com.elice.bookstore.order.domain;

import com.elice.bookstore.cart.domain.Cart;
import com.elice.bookstore.orderbook.domain.OrderBook;
import com.elice.bookstore.orderbook.domain.OrderBookRepository;
import com.elice.bookstore.orderbook.domain.OrderBookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;
    public final OrderBookService orderBookService;

    /* **** 회원 CRUD *****/
    /* 나의 주문 내역 조회 */
    @GetMapping("/orders")
    public ResponseEntity<Page<OrderBook>> getAllMyOrders(Long userId, @PageableDefault(size = 15) Pageable pageable) {
        Page<OrderBook> allMyOrders = orderBookService.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
        return ResponseEntity.ok().body(allMyOrders);
    }

    /* orders 테이블에 데이터 입력 (장바구니에 담은것 가져와서 주문) */
    @PutMapping("/orders/order")
    public ResponseEntity<String> saveOrder(Order order) {
        orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order saved.");
    }

    /* 배송 전 주문 취소 */
    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        orderService.updateOrderStatusById(id);
        return ResponseEntity.ok("order cancel successfully.");
    }


    /* **** 관리자 CRUD *****/
    /* 전체 주문 내역 */
    @GetMapping("/admin/orders")
    public ResponseEntity<Page<OrderBook>> getAllOrders(@PageableDefault(size = 15) Pageable pageable) {
        Page<OrderBook> allOrders = orderBookService.findAllByOrderByCreatedAtDesc(pageable);
        return ResponseEntity.ok().body(allOrders);
    }

    /* 주문 상태 변경 (결제 완료 / 배송 준비 등) */
    @PutMapping("/admin/orders/{id}/{orderStatus}")
    public ResponseEntity<Order> adminUpdateOrderStatus(@PathVariable Long id, @PathVariable("orderStatus") OrderStatus orderStatus) {
        orderService.adminUpdateOrderStatueById(orderStatus, id);
        return ResponseEntity.ok().build();
    }


    /* 주문 내역 삭제(delete로 구현) */
    @DeleteMapping("/admin/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}