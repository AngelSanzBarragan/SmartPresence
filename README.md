# SmartPresence

> **La digitalización definitiva del control horario del profesorado.**

Proyecto optativo intermodular desarrollado para los ciclos formativos **DAM1 / ASIX1**.

---

## Objetivo del repositorio
Centralizar el código fuente, la infraestructura de despliegue, el diseño de hardware y la documentación técnica del sistema **SmartPresence**. El proyecto integra un lector físico RFID con microcontrolador ESP32, un backend modular con API REST y base de datos relacional desplegados con Docker, y una aplicación móvil desarrollada en Flutter para la consulta en tiempo real del estado del profesorado.

---

## Equipo y Roles
* **Ángel Sanz Barragán**: Scrum Master -> Responsable de Hardware e Infraestructura Backend (ESP32, API REST, Docker).
* **Mark Ruiz Correa**: Product Owner auxiliar & Responsable de Cliente Móvil y Documentación (Flutter, UX/UI, Memoria LaTeX).

---

## Definition of Done (DoD)
Para que una tarea o Issue se considere completada y se mueva a la columna **Hecho (Done)**, debe cumplir:
1. El código o entregable cumple con los requisitos descritos en el Issue.
2. No contiene errores de compilación ni ejecución en local.
3. El código sigue las convenciones de estilo del proyecto y está comentado donde sea necesario.
4. Los cambios se han incorporado mediante **Pull Request** revisada y aprobada por otro miembro del equipo (no push directo a `main`).
5. La documentación técnica asociada (o memoria en LaTeX) se ha actualizado si la tarea lo requería.
6. El Issue correspondiente queda cerrado y vinculado al commit o PR.

---

## Estructura del proyecto
```text
├── docs/
│   ├── memoria/          # Fuentes LaTeX (.tex) y PDF compilado
│   ├── actas/            # Actas de reuniones y ceremonias
│   └── retrospectivas/   # Revisiones y retrospectivas de cada sprint
├── src/
│   ├── hardware/         # Código C++ para ESP32 (Arduino IDE / Wokwi)
│   ├── backend/          # API REST, modelos de BD y docker-compose.yml
│   └── mobile/           # Aplicación móvil en Flutter
└── README.md
