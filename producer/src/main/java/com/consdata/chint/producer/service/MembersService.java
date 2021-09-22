package com.consdata.chint.producer.service;

import com.consdata.chint.producer.data.MembersData;
import com.consdata.chint.producer.model.AddMemberRequest;
import com.consdata.chint.producer.model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembersService {
    public Member getMemberById(int memberId) {
        return MembersData.instance().getMembers().stream().filter(member -> member.getId() == memberId).findAny().orElse(null);
    }

    public List<Member> getMembers() {
        return MembersData.instance().getMembers();
    }

    public Member addMember(AddMemberRequest member) {
        Member newMember = Member.builder().id(MembersData.instance().getMembers().size()).firstName(member.getFirstName()).lastName(member.getLastName()).build();
        List<Member> list = new ArrayList(MembersData.instance().getMembers());
        list.add(newMember);
        MembersData.instance().setMembers(list);
        return newMember;
    }
}
