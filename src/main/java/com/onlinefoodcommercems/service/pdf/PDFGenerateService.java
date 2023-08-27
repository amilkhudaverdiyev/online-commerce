package com.onlinefoodcommercems.service.pdf;


import com.lowagie.text.DocumentException;
import com.onlinefoodcommercems.entity.Order;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface PDFGenerateService {
    void generate(Order order, HttpServletResponse response) throws IOException, DocumentException;

}