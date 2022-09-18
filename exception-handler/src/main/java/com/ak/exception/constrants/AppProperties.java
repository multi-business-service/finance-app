package com.ak.exception.constrants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppProperties {
    @Value("${application.name}")
    private String appName;
    @Value("${applicaiton.time.zoneid:UTC}")
    private String zoneId;
}
