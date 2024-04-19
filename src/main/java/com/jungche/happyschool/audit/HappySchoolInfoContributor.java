package com.jungche.happyschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HappySchoolInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("App Name", "HappySchool");
        infoMap.put("App Description", "Happy School Web Application for Students and Admin");
        infoMap.put("App Version", "1.0.0");
        infoMap.put("Contact Email", "info@happyschool.com");
        infoMap.put("Contact Mobile", "+1(458) 673 4587");
        builder.withDetail("happyschool-info", infoMap);
    }
}
