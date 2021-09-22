package com.consdata.chint.producer.data;

import com.consdata.chint.producer.model.Member;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public final class MembersData {
    private static final MembersData INSTANCE = new MembersData();
    private List<Member> members = Arrays.asList(
            createMember(0, "Krzysztof", "Bocer"),
            createMember(1, "Jakub", "Goszczurny"),
            createMember(2, "Katarzyna", "Kur"),
            createMember(3, "Robert", "Mastalerek"),
            createMember(4, "Barbara", "Mitan"),
            createMember(5, "Filip", "Philavong"),
            createMember(6, "Piotr", "Stachowiak"),
            createMember(7, "Jakub", "Wilczewski")
    );

    public static MembersData instance() {
        return INSTANCE;
    }

    private Member createMember(int id, String firstName, String lastName) {
        return Member.builder().id(id).firstName(firstName).lastName(lastName).build();
    }
}
