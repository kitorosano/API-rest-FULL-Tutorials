# springboot-server

Esto es una aplicación de ejemplo que provee una API REST de tutoriales.

La API es un proyecto creado en Java utilizando Springboot como framework.

## Ejecutar la app

    mvnw.cmd spring-boot:run

# API REST

La API REST de la aplicación se explica a continuación

| METODO HTTP | POST            | GET         | PUT         | DELETE |
| ----------- | --------------- | ----------- | ----------- | ------ |
| CRUD OP     | CREATE          | READ        | UPDATE      | DELETE |
| /tutorials       | Crear nuevo tutorial | Listar tutoriales   | Error | Eliminar todos los tutoriales |
| /tutorials/1  | Error           | Mostrar tutorial    | Si existe, modificar tutorial; Si no, error | Eliminar tutorial |
| /tutorials/1/price  | Error           | Mostrar precio del tutorial     | Error | Error |
| /tutorials/published  | Error           | Listar tutoriales publicados    | Error | Error |
| /tutorials?title=abc  | Error           | Error     | Modificar tutoriales cuyo titulo coincida | Eliminar tutoriales cuyo titulo coincida |

## Obtener listado de tutoriales

`GET /tutorials`

![GET /tutorials](src/main/resources/images/getAll.jpg)

## Crear nuevo tutorial

`POST /tutorials`

![POST /tutorials](src/main/resources/images/post.jpg)

## Obtener tutorial mediante ID

`GET /tutorials/{id}`

![GET /tutorials/id](src/main/resources/images/getId.jpg)

## Obtener tutorial publicados

`GET /tutorials/published`

![GET /tutorials/published](src/main/resources/images/getPublished.jpg)

## Obtener precio de tutoriales mediante ID

`GET /tutorials/{id}/price`

![GET /tutorials/{id}/price](src/main/resources/images/post.jpg)

## Modificar un tutorial mediante ID

`PUT /tutorials/{id}`

![PUT /tutorials/{id}](src/main/resources/images/putId.jpg)

## Modificar un tutorial mediante titulo

`PUT /tutorials?title=`

![PUT /tutorials?title=](src/main/resources/images/putTitle.jpg)

## Eliminar un tutorial mediante ID

`DELETE /tutorials/{id}`

![DELETE /tutorials/{id}](src/main/resources/images/deleteId.jpg)
    
## Eliminar un tutorial mediante titulo

`DELETE /tutorials?title=`

![DELETE /tutorials?title?](src/main/resources/images/deleteTitle.jpg)

## Eliminar todos los tutoriales

`DELETE /tutorials`

![DELETE /tutorials](src/main/resources/images/deleteAll.jpg)


