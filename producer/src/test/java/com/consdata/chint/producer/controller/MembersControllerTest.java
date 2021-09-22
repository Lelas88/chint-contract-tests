package com.consdata.chint.producer.controller;

import com.consdata.chint.producer.contractTest.BaseTestClass;
import com.consdata.chint.producer.model.AddMemberRequest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

class MembersControllerTest extends BaseTestClass {

    @Test
    public void shouldGetAllMembers() {
        // given
        MockMvcRequestSpecification request = given();

        // when
        ResponseOptions response = given().spec(request)
                .get("/api/members");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isEqualTo("[{\"id\":0,\"firstName\":\"Krzysztof\",\"lastName\":\"Bocer\"},{\"id\":1,\"firstName\":\"Jakub\",\"lastName\":\"Goszczurny\"},{\"id\":2,\"firstName\":\"Katarzyna\",\"lastName\":\"Kur\"},{\"id\":3,\"firstName\":\"Robert\",\"lastName\":\"Mastalerek\"},{\"id\":4,\"firstName\":\"Barbara\",\"lastName\":\"Mitan\"},{\"id\":5,\"firstName\":\"Filip\",\"lastName\":\"Philavong\"},{\"id\":6,\"firstName\":\"Piotr\",\"lastName\":\"Stachowiak\"},{\"id\":7,\"firstName\":\"Jakub\",\"lastName\":\"Wilczewski\"}]");
    }

    @Test
    public void shouldGetValidMembersData() {
        // given
        MockMvcRequestSpecification request = given();

        // when
        ResponseOptions response = given().spec(request)
                .get("/api/members/3");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isEqualTo("{\"id\":3,\"firstName\":\"Robert\",\"lastName\":\"Mastalerek\"}");
    }

    @Test
    public void shouldAddNewMember() {
        // given
        MockMvcRequestSpecification request = given()
                .body(AddMemberRequest.builder()
                    .firstName("Andrzej")
                    .lastName("Duda")
                    .build()
                );

        // when
        ResponseOptions response = given().spec(request)
                .contentType(ContentType.JSON)
                .post("/api/members/add");

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        String responseBody = response.getBody().asString();
        assertThat(responseBody).isEqualTo("{\"id\":8,\"firstName\":\"Andrzej\",\"lastName\":\"Duda\"}");
    }
}