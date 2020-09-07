package com.kalkix.Controller;

import com.kalkix.zerodha.TickerImpl;
import com.zerodhatech.models.Tick;
import com.zerodhatech.ticker.OnTicks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestServices {
    public static List<Tick> tick = new ArrayList<>();

    @GetMapping("/get_tickers")
    public List<Tick> getTickers() {
            return tick;
    }

}
