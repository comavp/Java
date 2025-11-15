package ru.comavp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.comavp.dto.Company;
import ru.comavp.service.CompanyService;

@RestController
@AllArgsConstructor
public class CompanyController {

    CompanyService companyService;

    @GetMapping("/get")
    public HttpEntity<Company> getCompany() throws InterruptedException {
        return new HttpEntity<>(companyService.getCompany("test"));
    }

    @GetMapping("/get/error")
    public HttpEntity<Company> getError() throws InterruptedException {
        return new HttpEntity<>(companyService.getCompany("error"));
    }
}
