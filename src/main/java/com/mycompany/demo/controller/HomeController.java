package com.mycompany.demo.controller;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("companyName", "MY COMPANY");
        model.addAttribute("currentYear", Year.now().getValue());
        model.addAttribute("values", List.of(
                new CompanyValue("01", "신뢰", "약속을 지키는 태도", "작은 약속부터 성실하게 지키며, 투명한 소통으로 오래가는 신뢰를 쌓습니다."),
                new CompanyValue("02", "도전", "더 나은 내일을 향해", "익숙한 방식에 머무르지 않고 새로운 가능성을 탐색하며 한 걸음씩 나아갑니다."),
                new CompanyValue("03", "동반 성장", "함께 만드는 좋은 변화", "서로의 경험과 생각을 나누며 고객, 동료, 파트너와 함께 성장합니다.")));
        return "index";
    }

    public record CompanyValue(String number, String title, String subtitle, String description) {
    }
}
