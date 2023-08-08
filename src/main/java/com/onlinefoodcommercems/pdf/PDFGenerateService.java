package com.onlinefoodcommercems.pdf;


import com.lowagie.text.DocumentException;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.OrderDetail;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public interface PDFGenerateService {
    void generate(Order order, HttpServletResponse response) throws IOException, DocumentException;

}