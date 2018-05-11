# ApplianceAPI
A backend service to control an oven.

# Tools
This API is REST based using Spring MVC. As the in/out data format JSON is used. Parser library is Jackson.

Oven's state is persisted to embedded H2 database, which is interacted through Spring Data and Hibernate.

Lombok is used to simplify code writing.

Tests are written with Spring Test, JUnit, Mockito.

Project is managed with Maven.

# Build
Make `mvn package` from the root project directory. Move resulted 'war' from target directory to servlet container.

# Use
It is proposed to be several status and state in the system:
+ "Power": {"On" | "Off"}
+ "Door": {"Open" | "Closed"}
+ "Program": {"Top/bottom" | "Circulation air" | "Pre heat" | "Pizza"}

To get get all statuses use `curl -X GET -i http://localhost:8080/ovenApi/`

To get concret status use `curl -X GET -i http://localhost:8080/ovenApi/{status}`, where {status} is Power, Door or Program

To change status state use `curl -X PUT -H 'Content-Type: application/json' -i http://localhost:8080/ovenApi/ --data '{"status":"Power","state":"On"}'`, where status and state can take values listed above
