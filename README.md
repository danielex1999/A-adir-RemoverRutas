![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)<img src="https://cdn.iconscout.com/icon/free/png-512/java-43-569305.png" width="35px" alt="java" align="right">

![](https://img.shields.io/github/v/tag/danielex1999/ClientRouteManagement-BakeryDivision?style=social&logo=github
)
![](https://img.shields.io/github/last-commit/danielex1999/ClientRouteManagement-BakeryDivision?style=social&logo=github
)

## Configuración de Rutas

Para este proyecto se busca mediante un Excel la Agencia, Código de Cliente y la Ruta Pre-Venta, una vez definido los
campos lo que se realiza es el ingreso al portal, el inicio de sesión del cliente registrado dentro del código asociado.

El proyecto debe de tener el excel estructurado de la siguiente manera:

|   | A | B       | C      | D              |
|---|---|---------|--------|----------------|
| 1 |   | AGENCIA | CODIGO | RUTA PRE-VENTA |
| 2 |   |         |        |                |

---

### Funcionamiento de los métodos

1. **Asignación de Ruta del Cliente**

   Se encarga de gestionar la asignación de rutas a clientes dentro de un entorno de aplicación
   específico, utilizando Selenium para la automatización de navegadores web y Apache POI para el manejo de archivos
   Excel.

   Esta clase incluye métodos para:

    - [x] Filtrar y seleccionar clientes mediante un código específico.
    - [x] Asignar una ruta preventa a un cliente, verificando su estado activo.
    - [x] Manejar errores y excepciones cuando no se encuentra la ruta o al intentar modificarla.
    - [x] Guardar el estado anterior y actual de la ruta para fines de registro y seguimiento.

2. **Desasignación de Ruta del Cliente**

   Se encarga de gestionar la desasignación de rutas de clientes dentro de un entorno de aplicación específico,
   utilizando Selenium para la automatización de navegadores web y Apache POI para el manejo de archivos Excel.

   Esta clase incluye métodos para:

    - [x] Filtrar y seleccionar clientes mediante un código específico.
    - [x] Retirar una ruta preventa asignada a un cliente, verificando su estado activo.
    - [x] Manejar errores y excepciones cuando no se encuentra la ruta o al intentar modificarla.
    - [x] Guardar el estado anterior y actual de la ruta para fines de registro y seguimiento.

---

### Resultados generados dentro del Excel

Se generan las siguientes celdas en el archivo Excel:

|   | A | B       | C      | D              | E               | F             |
|---|---|---------|--------|----------------|-----------------|---------------|
| 1 |   | AGENCIA | CODIGO | RUTA PRE-VENTA | Estado Anterior | Estado Actual |
| 2 |   |         |        |                |                 |               |

Dentro de `Asignación de Ruta del Cliente` se considera de la siguiente forma:

- `Estado Anterior`: Representa el estado anterior de algún proceso:
    - **Ruta Asignada:** Indica que la ruta estaba activa antes de cualquier cambio.
    - **Ruta Desasignada:** Indica que la ruta no estaba activa antes de cualquier cambio.
    - **Ruta no encontrada:** Indica que no se encontró la ruta en la agencia especificada.
- `Estado Actual`: Representa el estado actual de algún proceso:
    - **Ruta Asignada:** Indica que la ruta ha sido agregada al cliente.
    - **Ruta Desasignada:** Indica que la ruta no está activa después de las modificaciones.
    - **Ruta no encontrada:** Indica que no se encontró la ruta en la agencia especificada.

Dentro de `Desasignación de Ruta del Cliente` se considera de la siguiente forma:

- `Estado Anterior`: Representa el estado anterior de algún proceso:
    - **Ruta Asignada:** Indica que la ruta estaba activa antes de cualquier cambio.
    - **Ruta Desasignada:** Indica que la ruta no estaba activa antes de cualquier cambio.
    - **Ruta no encontrada:** Indica que no se encontró la ruta en la agencia especificada.
- `Estado Actual`: Representa el estado actual de algún proceso:
    - **Ruta Asignada:** Indica que la ruta no está desactiva después de las modificaciones.
    - **Ruta Desasignada:** Indica que la ruta ha sido retirada del cliente.
    - **Ruta no encontrada:** Indica que no se encontró la ruta en la agencia especificada.

---
<div align="center">

![215002917 Brave Shift.png](src%2Fmain%2Fresources%2Fimg%2F215002917%20Brave%20Shift.png)

</div>


> [!NOTE]
> Se está utilizando para este proyecto las siguientes tecnologías.

<code><a href="" target="_blank"><img src="src/main/resources/img/selenium.png"	width="26px" alt="selenium"></a></code>
<code><a href="" target="_blank"><img src="src/main/resources/img/java.png"	width="30px" alt="java"></a></code>
<code><a href="" target="_blank"><img src="src/main/resources/img/Intellj.svg.png"	width="26px" alt="intellj"></a></code>
<code><a href="" target="_blank"><img src="src/main/resources/img/excel.svg"	width="30px" alt="intellj"></a></code>
