# DOSW_Parcial_T2_JuanGarzon# DOSW_Parcial_T2_JuanGarzon

## 📑 Índice

1. [Datos del estudiante](#1-datos-del-estudiante)
2. [Descripción del proyecto](#2-descripción-del-proyecto)
3. [Despliegue](#3-despliegue)
4. [Estructura del proyecto](#4-estructura-del-proyecto)
5. [Tecnologías y dependencias](#5-tecnologías-y-dependencias)
6. [Configuración y variables de entorno](#6-configuración-y-variables-de-entorno)
7. [Cómo ejecutar](#7-cómo-ejecutar)
8. [Pruebas](#8-pruebas)
9. [Docker](#9-docker)
10. [CI/CD](#10-cicd)
11. [Herramientas de modelado y diseño](#11-herramientas-de-modelado-y-diseño)

---

## 1. Datos del estudiante

- **Nombre completo:** Juan Garzón
- **Grupo:** DOSW Grupo 1
- **Profesor agregado al repositorio:** ✔
- **Parcial Corte 1:** University Food Hub — Sistema de pedidos de comida para la cafetería universitaria

---

## 2. Descripción del proyecto

Servicio base (scaffolding) para el parcial del segundo tercio de DOSW. Este repositorio contiene la estructura MVC completa, las dependencias, la configuración de MongoDB, el `Dockerfile`, el pipeline CI/CD y el despliegue en Azure. El servicio arranca y expone un endpoint de salud `/api/v1/health` y Swagger UI, pero **no incluye todavía lógica de negocio** — esta se implementará durante el parcial.

---

## 3. Despliegue

Servicio desplegado en **Azure App Service** (región Mexico Central).

| Recurso | URL |
|---------|-----|
| Health | https://dosw-parcial-juan-garzon-f4cjc5grbvgqb2ab.mexicocentral-01.azurewebsites.net/api/v1/health |
| Swagger | https://dosw-parcial-juan-garzon-f4cjc5grbvgqb2ab.mexicocentral-01.azurewebsites.net/swagger-ui/index.html |
| App Service | https://portal.azure.com/#@/resource/subscriptions/6ad317e9-c953-4bd4-80ae-f47dba1a7992/resourceGroups/rg-dosw-parcial-t2/providers/Microsoft.Web/sites/dosw-parcial-juan-garzon |
| Docker Hub | https://hub.docker.com/r/juangv1/dosw-parcial-t2 |
| GitHub Actions | https://github.com/juangv1/DOSW_Parcial_T2_JuanGarzon/actions |

---

## 4. Estructura del proyecto

```
DOSW_Parcial_T2_JuanGarzon/
│
├── docs/
│   ├── uml/                  → Diagramas fuente (draw.io)
│   ├── images/               → Exportes PNG/SVG y evidencias
│   └── requirements/         → Análisis de requerimientos
│
├── src/main/java/edu/dosw/parcial/
│   ├── controller/
│   │   ├── dtos/
│   │   │   ├── request/      → DTOs de entrada (@Valid)
│   │   │   └── response/     → DTOs de salida
│   │   ├── mappers/          → Mapper IN: DTO ↔ Dominio
│   │   └── handlers/         → GlobalExceptionHandler
│   ├── core/
│   │   ├── services/         → Lógica de negocio
│   │   ├── models/           → Objetos de dominio
│   │   ├── validators/       → Reglas de negocio
│   │   └── utils/            → Utilidades
│   ├── persistence/
│   │   ├── entities/         → Clases @Document (MongoDB)
│   │   ├── repositories/     → MongoRepository
│   │   └── mappers/          → Mapper OUT: Dominio ↔ Document
│   ├── config/               → SecurityConfig, OpenApiConfig
│   └── main/                 → Clase principal
│
├── src/test/java/edu/dosw/parcial/    → Pruebas unitarias
├── Dockerfile
├── .dockerignore
├── .github/workflows/        → Pipeline CI/CD
├── pom.xml
└── README.md
```

---

## 5. Tecnologías y dependencias

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Java | 21 | Lenguaje base |
| Spring Boot | 3.4.1 | Framework principal |
| Spring Data MongoDB | 3.4.1 | Persistencia NoSQL |
| Spring Security | 3.4.1 | Seguridad |
| JJWT | 0.12.6 | Tokens JWT |
| Lombok | 1.18.34 | Reducción de boilerplate |
| MapStruct | 1.6.3 | Mapeo entre capas |
| Springdoc OpenAPI | 2.7.0 | Swagger UI |
| JUnit 5 + Mockito | (BOM Spring Boot) | Testing |
| JaCoCo | 0.8.12 | Cobertura de tests |
| SonarQube | 9.9 LTS | Análisis estático |

---

## 6. Configuración y variables de entorno

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `MONGODB_URI` | URI de conexión a MongoDB | `mongodb://localhost:27017/dosw_parcial` |
| `JWT_SECRET` | Secret Base64 para firmar JWT | `VGVzdFNlY3JldEtleVRlc3RTZWNyZXRLZXlUZXN0U2VjcmV0S2V5VGVzdFNlY3JldEtleQ==` |
| `PORT` | Puerto del servidor | `8080` |

> **Ninguna credencial se sube al repositorio.**

---

## 7. Cómo ejecutar

### Local con Maven

```bash
# 1. Levantar MongoDB (Docker)
docker run -d --name mongo-parcial -p 27017:27017 mongo:7

# 2. Compilar y ejecutar
mvn clean package -DskipTests
java -jar target/DOSW-ParcialT2-1.0.0-SNAPSHOT.jar
```

O directamente:

```bash
mvn spring-boot:run
```

### Verificar

- Health: http://localhost:8080/api/v1/health
- Swagger: http://localhost:8080/swagger-ui.html

---

## 8. Pruebas

```bash
mvn clean test
```

Reporte de cobertura:

```bash
mvn clean test
# Abrir: target/site/jacoco/index.html
```

---

## 9. Docker

```bash
# Construir imagen
docker build -t dosw-parcial:local .

# Ejecutar
docker run -p 8080:8080 \
  -e MONGODB_URI=mongodb://host.docker.internal:27017/dosw_parcial \
  -e JWT_SECRET=VGVzdFNlY3JldEtleVRlc3RTZWNyZXRLZXlUZXN0U2VjcmV0S2V5VGVzdFNlY3JldEtleQ== \
  dosw-parcial:local
```

---

## 10. CI/CD

Pipeline en `.github/workflows/ci-cd.yml` con 3 jobs:

1. **test** — Compila y ejecuta pruebas
2. **docker** — Construye y publica imagen en Docker Hub
3. **deploy** — Despliega en Azure App Service (solo en `main`)

### Secrets configurados en GitHub

| Secret | Descripción |
|--------|-------------|
| `DOCKERHUB_USERNAME` | Usuario de Docker Hub |
| `DOCKERHUB_TOKEN` | Access Token de Docker Hub |
| `AZURE_CREDENTIALS` | JSON del Service Principal de Azure |
| `AZURE_WEBAPP_NAME` | Nombre del App Service |
| `JWT_SECRET` | Secret JWT Base64 |

---

## 11. Herramientas de modelado y diseño

- **Modelado UML:** Draw.io
- **Diseño de interfaces:** Figma