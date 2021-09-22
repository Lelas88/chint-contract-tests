package com.consdata.chint.consumer.connector;

import com.consdata.chint.consumer.model.NewMember;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

/**
 * StubRunnerProperties.StubsMode.CLASSPATH (default value) - This is the default mode. It will scan the classpath and pick stubs from there. We need to add the dependency of the stub with classifier as a stub in the pom.xml with test scope.
 * StubRunnerProperties.StubsMode.LOCAL - It will pick stubs from a local m2 repository.
 * StubRunnerProperties.StubsMode.REMOTE - It will pick stubs from a remote location e.g. Nexus. We need to initialize repositoryRoot property with the URL of the remote repository in the AutoConfigureStubRunner annotation.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
        ids = "com.consdata.chint:producer:+:stubs:8590",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class MembersConnectorIT {

    @Test
    public void shouldGetMembersData() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(RequestEntity
                .get(URI.create("http://localhost:8590/api/members/3"))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build(), String.class);

        BDDAssertions.then(result.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(result.getBody()).isEqualTo("{\"id\":3,\"firstName\":\"Robert\",\"lastName\":\"Mastalerek\"}");
    }

    @Test
    public void shouldAddMember() {
        ResponseEntity<String> result = new TestRestTemplate().exchange(RequestEntity
                .post(URI.create("http://localhost:8590/api/members/add"))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(NewMember.builder().firstName("Andrzej").lastName("Duda").build()), String.class);

        BDDAssertions.then(result.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(result.getBody()).isEqualTo("{\"id\":9,\"firstName\":\"Andrzej\",\"lastName\":\"Duda\"}");
    }
}