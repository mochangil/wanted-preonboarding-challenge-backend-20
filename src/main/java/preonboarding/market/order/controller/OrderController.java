package preonboarding.market.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.*;
import preonboarding.market.order.dto.request.OrderApprovalReqeustDto;
import preonboarding.market.order.dto.request.OrderSaveRequestDto;
import preonboarding.market.order.service.OrderService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public void saveOrders(@RequestBody OrderSaveRequestDto orderSaveRequestDto){
        //member정보 받아오는 로직 수정 필요
        Long memberId = 1L;
        orderService.saveOrders(memberId, orderSaveRequestDto);
    }

    @PostMapping("/approval")
    public void approveOrders(@RequestBody OrderApprovalReqeustDto orderApprovalReqeustDto){
        //member정보 인증식으로 받아오기
        Long memberId = 1L;
        orderService.approveOrders(memberId, orderApprovalReqeustDto);
    }


}
