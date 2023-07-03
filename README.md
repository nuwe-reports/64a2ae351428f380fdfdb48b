TechHub Accenture
➡️ Contexto:
Recientemente varios hospitales de la zona han recibido una serie de ataques informáticos y se ha propuesto renovar el sistema de control de citas en el hospital AccWe, ya que es lo que mas tiempo lleva sin actualizarse. Los desarrolladores han salvado parte del proyecto anterior y lo han limpiado para evitar posibles errores, actualizando sobretodo librerías. Tu tarea sera implementar, arreglar y desarrollar las diferentes necesidades del hospital en cuanto a la gestión de citas.

🎯 Objetivos:
El objetivo principal es terminar de implementar y desarrollar la gestión de citas del proyecto de Backend. Este proyecto utiliza versiones antiguas de JAVA, en concreto JAVA 8. Se pide por tanto desarrollar las siguientes tareas:

Tu primera tarea va a ser crear citas mediante la API. Debes tener en cuenta las limitaciones en cuanto a la creación de citas. Asegúrate de cumplir con las especificaciones hechas en JUnit, ya que son una ayuda esencial para el desarrollo de código. Solamente se requiere modificar el archivo AppointmentController.java.
Las entidades creadas no están siendo "testeadas", y por lo tanto, pueden surgir posibles errores debido a una mala implementación. Para solucionar esto, se deben crear diferentes pruebas JUnit para cada una de las entidades en el archivo "EntityUnitTest.java" asi como cada uno de los controladores de estas entidades en el archivo "EntityControllerUnitTest.java" que se encuentra en el directorio de pruebas.
Asegúrate realizar código limpio. Como estás trabajando en un hospital, se espera que sigas medidas estrictas para garantizar que el código sea aceptable. Por lo tanto, debes escribir código que esté libre de errores y vulnerabilidades, especialmente de vulnerabilidades.
Se quiere hacer un despliegue escalable de la API, para ello se plantea usar Kubernetes. Cree un Dockerfile que permita ejecutar una base de datos MySQL y el microservicio. Para este último requerimiento, se solicita un Dockerfile Multistage donde se ejecuten primero las pruebas, y si todas pasan, se compile y ejecute el microservicio. Los nombres de los Dockerfiles serán Dockerfile.mysql y Dockerfile.maven, respectivamente.
Extra OPCIONAL : Desarrolla un diagrama UML con todas las relaciones entre clases.

✅ Formato de entrega:
Repositorio público con todos los puntos completados, creado a partir del template ofrecido. Es obligatorio utilizar el template como base, de lo contrario, la evaluación no reflejará correctamente el trabajo realizado. Además, se requiere un archivo README en el que se especifiquen las decisiones tomadas durante las diferentes tareas realizadas.

👍 Link correcto: https://github.com/nuwe-reports/accenture-nuwe-accenture (link prueba)
👎 Link incorrecto: https://github.com/CarlosIbCu/example_se.git
✍️ Evaluación:
La evaluación tendrá en cuenta principalmente los objetivos cumplidos, que suman un total de 4. Si se cumplen 2 objetivos, la nota final será de 500/1000, si se cumplen 3, de 750/1000 y así sucesivamente.

Cabe remarcar que una vez completados los 4 objetivos, se revisará la calidad, complejidad, mantenibilidad, fiabilidad y seguridad del código así como la documentación aportada y los objetivos opcionales. Esta revisión no se reflejará directamente en la puntuación.
