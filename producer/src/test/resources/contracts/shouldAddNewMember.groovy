package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return member data"
    request {
        method POST()
        url("/api/members/add")
        headers {
            contentType(applicationJson())
        }
        body(
                firstName: "Andrzej",
                lastName: "Duda"
        )
    }
    response {
        headers {
            contentType(applicationJson())
        }
        body(
                id: 9,
                firstName: "Andrzej",
                lastName: "Duda"
        )
        status 200
    }
}