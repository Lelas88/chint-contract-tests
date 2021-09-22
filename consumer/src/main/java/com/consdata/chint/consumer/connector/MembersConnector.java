package com.consdata.chint.consumer.connector;

import com.consdata.chint.consumer.model.MemberData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class MembersConnector {

    @Value("${chint.memberService.url}")
    private String membersControllerUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public MemberData getMember(int memberId) {
        String url = String.format("%s/%s", this.membersControllerUrl, memberId);
        ResponseEntity<MemberData> result = this.restTemplate.getForEntity(url, MemberData.class);
        return result.hasBody() ? result.getBody() : null;
    }

    public List<MemberData> getMembers() {
        ResponseEntity<MemberData[]> result = this.restTemplate.getForEntity(this.membersControllerUrl, MemberData[].class);
        return result.hasBody() ? Arrays.asList(result.getBody().clone()) : null;
    }
}