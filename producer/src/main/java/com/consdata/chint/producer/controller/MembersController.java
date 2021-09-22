package com.consdata.chint.producer.controller;

import com.consdata.chint.producer.model.AddMemberRequest;
import com.consdata.chint.producer.model.Member;
import com.consdata.chint.producer.service.MembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MembersController {
    private final MembersService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Member> getChintMembers() {
        log.info(">>PRODUCER<< Getting all CHINT member");
        List<Member> members = this.service.getMembers();
        log.info(">>PRODUCER<< Returning all CHINT members: {}", members);
        return members;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Member getChintMember(@PathVariable("id") int id) {
        log.info(">>PRODUCER<< Getting CHINT member data with id={}", id);
        Member member = this.service.getMemberById(id);
        log.info(">>PRODUCER<< Returning CHINT member data={}", member);
        return member;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Member addChintMember(@RequestBody AddMemberRequest member) {
        log.info(">>PRODUCER<< Add CHINT member with firstName: {} and lastName: {}", member.getFirstName(), member.getLastName());
        Member newMember = this.service.addMember(member);
        log.info(">>PRODUCER<< New member added with id={}", newMember.getId());
        return newMember;
    }
}