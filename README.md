# Spring Data JPA + EclipseLink: Configuring Spring-Boot to use EclipseLink as the JPA provider

Source code for tutorial published on https://blog.marcnuri.com/spring-data-jpa-eclipselink-configuring-spring-boot-to-use-eclipselink-as-the-jpa-provider/

Simple Spring Boot application that uses EclipseLink as the JPA provider (see
[CustomJpaConfiguraton](src/main/java/com/marcnuri/demo/springeclipselink/CustomJpaConfiguration.java)).

To run the application:
```shell script
$ ./gradlew bootRun
```

Once the application is running you can test the provided endpoints:
```shell script
$ curl -s localhost:8080/mounts | jq
[
  {
    "id": "E08A6EFD-E208-42BC-83CC-0AFE5408783F",
    "alias": "everest",
    "name": "Mount Everest"
  },
  {
    "id": "30CAB297-B27A-4D79-BF04-EEF9ACA09EB8",
    "alias": "k2",
    "name": "K2"
  },
  {
    "id": "DADB5D54-54E3-4AFD-A1CC-E563FCE34678",
    "alias": "aitana",
    "name": "Aitana"
  }
]
```
```shell script
$ curl -s localhost:8080/climbers | jq
[
  {
    "id": "12EED719-7A08-4620-9ADF-7A1FD233FFD1",
    "name": "Alex",
    "climbedMounts": [
      {
        "id": "E08A6EFD-E208-42BC-83CC-0AFE5408783F",
        "alias": "everest",
        "name": "Mount Everest"
      },
      {
        "id": "DADB5D54-54E3-4AFD-A1CC-E563FCE34678",
        "alias": "aitana",
        "name": "Aitana"
      }
    ],
    "phoneNumbers": [
      {
        "id": "043772AA-495C-412B-AFC4-6BF3F918C756",
        "number": "+00 123 45 78"
      }
    ]
  },
  {
    "id": "114970D3-67F0-41EF-B328-CAA7B5EEA5C9",
    "name": "Charles",
    "climbedMounts": [
      {
        "id": "30CAB297-B27A-4D79-BF04-EEF9ACA09EB8",
        "alias": "k2",
        "name": "K2"
      },
      {
        "id": "E08A6EFD-E208-42BC-83CC-0AFE5408783F",
        "alias": "everest",
        "name": "Mount Everest"
      }
    ],
    "phoneNumbers": [
      {
        "id": "3E90D601-F2FE-4B6D-883C-A53DB6005AC6",
        "number": "+11 555 13 37"
      }
    ]
  },
  {
    "id": "E37222EF-6FF9-452D-AACF-E8C8E306C8B1",
    "name": "Julia",
    "climbedMounts": [
      {
        "id": "30CAB297-B27A-4D79-BF04-EEF9ACA09EB8",
        "alias": "k2",
        "name": "K2"
      },
      {
        "id": "E08A6EFD-E208-42BC-83CC-0AFE5408783F",
        "alias": "everest",
        "name": "Mount Everest"
      }
    ],
    "phoneNumbers": []
  }
]
```
