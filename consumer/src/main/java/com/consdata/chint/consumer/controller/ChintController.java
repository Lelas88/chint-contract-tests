package com.consdata.chint.consumer.controller;

import com.consdata.chint.consumer.connector.MembersConnector;
import com.consdata.chint.consumer.model.MemberData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chint")
@RequiredArgsConstructor
public class ChintController {
    private static final Logger log = LoggerFactory.getLogger(ChintController.class);
    private final MembersConnector connector;

    @GetMapping("/member/{id}")
    public MemberData getChintMember(@PathVariable("id") int id) {
        log.info(">>CONSUMER<< Getting CHINT member's data for id={}", id);
        MemberData result = this.connector.getMember(id);
        log.info(">>CONSUMER<< Returning member's data: {}", result);
        return result;
    }

    @GetMapping("/members")
    public List<MemberData> getChintMembers() {
        log.info(">>CONSUMER<< Getting CHINT members");
        List<MemberData> result = this.connector.getMembers();
        log.info(">>CONSUMER<< Returning members list: {}", result);
        return result;
    }
}