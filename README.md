# -_generador_de_secuencia_2_en_2_- :.
# Proyecto Java 21 + IntelliJ IDEA + MySQL:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/e8ef2cca-9b2f-4b87-98fe-7364f8804923" />  

```

# Generador de Secuencia (de 2 en 2)

## Descripción

Proyecto desarrollado en **Java 21**, utilizando **IntelliJ IDEA**, **JDBC** y **MySQL**, que permite:

- ✅ Generar una secuencia de números de **2 en 2**.
- ✅ Guardar la secuencia en una base de datos **MySQL**.
- ✅ Consultar los números almacenados.
- ✅ Utilizar **JDBC** (sin Spring Boot).
- ✅ Compatible con **IntelliJ IDEA**.
- ✅ Aplicar Programación Orientada a Objetos (POO).
- ✅ Implementar el patrón **DAO (Data Access Object)**.

---

# Estructura del Proyecto

```text
GeneradorSecuencia
│
├── src
│   └── main
│       └── java
│            │
│            ├── conexion
│            │      ConexionMySQL.java
│            │
│            ├── modelo
│            │      Numero.java
│            │
│            ├── dao
│            │      NumeroDAO.java
│            │
│            └── Principal.java
│
└── pom.xml
```

---

# Base de Datos MySQL

```sql
CREATE DATABASE secuencia_db;

USE secuencia_db;

CREATE TABLE numeros(

    id INT AUTO_INCREMENT PRIMARY KEY,

    numero INT NOT NULL

);
```

---

# Archivo pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.secuencia</groupId>
    <artifactId>GeneradorSecuencia</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <dependencies>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.3.0</version>
        </dependency>

    </dependencies>

</project>
```

---

# Clase ConexionMySQL.java

```java
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    private static final String URL =
            "jdbc:mysql://localhost:3306/secuencia_db";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
```

---

# Clase Numero.java

```java
package modelo;

public class Numero {

    private int id;
    private int numero;

    public Numero() {
    }

    public Numero(int numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
```

---

# Clase NumeroDAO.java

```java
package dao;

import conexion.ConexionMySQL;
import modelo.Numero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NumeroDAO {

    public void guardar(Numero numero) {

        String sql =
                "INSERT INTO numeros(numero) VALUES(?)";

        try(Connection con = ConexionMySQL.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, numero.getNumero());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    public void mostrarTodos(){

        String sql =
                "SELECT * FROM numeros";

        try(Connection con = ConexionMySQL.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            System.out.println();

            System.out.println("NUMEROS ALMACENADOS");

            System.out.println("-----------------------");

            while(rs.next()){

                System.out.println(
                        rs.getInt("id")
                        +" -> "
                        +rs.getInt("numero"));

            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
```

---

# Clase Principal.java

```java
import dao.NumeroDAO;
import modelo.Numero;

public class Principal {

    public static void main(String[] args) {

        NumeroDAO dao = new NumeroDAO();

        System.out.println("Generando secuencia...");

        for(int i = 2; i <= 100; i += 2){

            Numero numero = new Numero(i);

            dao.guardar(numero);

            System.out.println("Guardado: " + i);

        }

        System.out.println();

        dao.mostrarTodos();

    }

}
```

---

# Resultado en Consola

```text
Generando secuencia...

Guardado: 2
Guardado: 4
Guardado: 6
Guardado: 8
Guardado: 10
Guardado: 12
...
Guardado: 98
Guardado: 100


NUMEROS ALMACENADOS

1 -> 2
2 -> 4
3 -> 6
4 -> 8
5 -> 10
6 -> 12
...
49 -> 98
50 -> 100
```

---

# Resultado en MySQL

| id | numero |
|---:|-------:|
| 1 | 2 |
| 2 | 4 |
| 3 | 6 |
| 4 | 8 |
| 5 | 10 |
| 6 | 12 |
| ... | ... |
| 50 | 100 |

---

# Uso de Condicionales

El programa puede enriquecerse utilizando tres estructuras condicionales (`if`, `else if` y `else`) para clasificar cada número antes de almacenarlo en la base de datos.

```java
if (i % 10 == 0) {

    System.out.println(i + " es múltiplo de 10");

} else if (i % 4 == 0) {

    System.out.println(i + " es múltiplo de 4");

} else {

    System.out.println(i + " es un número par");

}
```

Con ello se emplean las tres estructuras condicionales mientras se genera y almacena la secuencia.

---

# Ejemplo de Datos Almacenados

| id | numero |
|---:|-------:|
| 1 | 2 |
| 2 | 4 |
| 3 | 6 |
| 4 | 8 |
| 5 | 10 |
| 6 | 12 |
| 7 | 14 |
| 8 | 16 |
| 9 | 18 |
| 10 | 20 |

---

# Tecnologías Utilizadas

- Java 21
- IntelliJ IDEA
- Maven
- JDBC
- MySQL
- Programación Orientada a Objetos (POO)
- Patrón DAO

---

# Características del Proyecto

- Generación automática de números pares.
- Persistencia de datos mediante JDBC.
- Consulta de registros almacenados.
- Organización por paquetes.
- Código modular y reutilizable.
- Compatible con Java 21.
- Fácil de ampliar con nuevas funcionalidades.

---

# Conclusión

Este proyecto constituye un ejemplo académico sencillo para practicar conceptos fundamentales del desarrollo de aplicaciones Java, incluyendo:

- Programación Orientada a Objetos (POO).
- Conexión a bases de datos mediante JDBC.
- Persistencia de datos en MySQL.
- Implementación del patrón DAO.
- Uso de ciclos `for`.
- Uso de estructuras condicionales (`if`, `else if`, `else`).
- Organización de proyectos Maven en IntelliJ IDEA.

Es una excelente base para evolucionar posteriormente hacia aplicaciones con interfaces gráficas, servicios web o frameworks como Spring Boot.
:. . / .  
