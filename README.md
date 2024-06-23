![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)<img src="https://cdn.iconscout.com/icon/free/png-512/java-43-569305.png" width="35px" alt="java" align="right">

## Configuración de Rutas MC1

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

   - [ ] Filtrar y seleccionar clientes mediante un código específico.
   - [ ] Asignar una ruta preventa a un cliente, verificando su estado activo.
   - [ ] Manejar errores y excepciones cuando no se encuentra la ruta o al intentar modificarla.
   - [ ] Guardar el estado anterior y actual de la ruta para fines de registro y seguimiento.

2. **Desasignación de Ruta del Cliente**
   Se encarga de gestionar la desasignación de rutas de clientes dentro de un entorno de aplicación específico,
   utilizando Selenium para la automatización de navegadores web y Apache POI para el manejo de archivos Excel.

   Esta clase incluye métodos para:
   
   - [ ] Filtrar y seleccionar clientes mediante un código específico.
   - [ ] Retirar una ruta preventa asignada a un cliente, verificando su estado activo.
   - [ ] Manejar errores y excepciones cuando no se encuentra la ruta o al intentar modificarla.
   - [ ] Guardar el estado anterior y actual de la ruta para fines de registro y seguimiento.
   
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
