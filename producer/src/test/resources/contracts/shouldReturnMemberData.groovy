package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return member data"
    request {
        method GET()
        url("/api/members/3")
    }
    response {
        headers {
            contentType(applicationJson())
        }
        body(
                id: 3,
                firstName: "Robert",
                lastName: "Mastalerek"
        )
        status 200
    }
}