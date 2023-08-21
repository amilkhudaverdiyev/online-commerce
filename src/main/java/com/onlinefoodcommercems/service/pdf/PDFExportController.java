package com.onlinefoodcommercems.service.pdf;


import com.lowagie.text.DocumentException;
import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class PDFExportController {
    private final PDFGenerateService pdfGenerateService;

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/pdf/generate/{id}")
    public void generatePDF(@PathVariable Long id, HttpServletResponse httpServletResponse) throws DocumentException, IOException {
        var customer = customerRepository.findByIdAndAuthority(id, Roles.USER)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        httpServletResponse.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + customer.getUsername() + ".pdf";
        httpServletResponse.setHeader(headerKey, headerValue);
        var orderList = orderRepository.findCustomerInOrder(id, OrderStatus.LOADING);
        for (Order order : orderList
        ) {
            this.pdfGenerateService.generate(order, httpServletResponse);
        }

    }
}
