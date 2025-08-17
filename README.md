🏦 Simulador de Cajero Automático (ATM) – Proyecto
--------------------------------------------------
Este proyecto es una simulación básica de un cajero automático (ATM) desarrollado en Java como parte de un trabajo académico para practicar conceptos fundamentales de programación orientada a objetos, manejo de entradas/salidas, validación de datos y diseño de interfaces por consola. 
 
🎯 Objetivo del Proyecto 

El objetivo principal es implementar un sistema funcional que simule las operaciones básicas de un cajero automático, con un enfoque en: 

*    Aplicar programación orientada a objetos (POO).
*    Gestionar datos de usuarios y transacciones.
*    Validar entradas y gestionar errores.
*    Ofrecer una experiencia de usuario interactiva mediante consola.


     

 
🛠️ Tecnologías y Herramientas Utilizadas 

    Lenguaje: Java (versión 8 o superior)
    Paradigma: Programación Orientada a Objetos (POO)
    Herramientas: Java SE, Scanner (entrada por consola)
    Estructuras de datos: HashMap, ArrayList, Date
     

 
✅ Funcionalidades Implementadas 

    Inicio de sesión seguro 
        Autenticación con número de cuenta y PIN.
        Bloqueo tras 3 intentos fallidos.
         

    Operaciones bancarias básicas 
        Consultar saldo.
        Depositar dinero.
        Retirar dinero.
        Transferir entre cuentas (simulación).
         

    Funcionalidades adicionales 
        Historial de transacciones con marca de tiempo.
        Cambio de PIN.
        Transferencias entre cuentas internas.
        Mensajes de validación y manejo de errores.
         

    Datos persistentes en memoria 
        Cuentas pre-cargadas con saldo inicial.
        Registro de transacciones en tiempo real.
         
     

 
🧱 Diseño del Sistema 

El sistema está estructurado en una sola clase (ATM.java) por simplicidad (acorde al nivel del curso), pero sigue principios de POO: 

    Clase Account: Representa una cuenta bancaria con atributos como número, PIN, saldo y historial.
    Mapa global accounts: Almacena todas las cuentas (simulando una base de datos en memoria).
    Menú interactivo: Implementado con bucles y switch, permite navegar entre opciones.
    Validaciones robustas: Evita entradas inválidas (textos en lugar de números, retiros mayores al saldo, etc.).
    
        
 
📚 Aprendizajes Obtenidos 

Este proyecto me permitió reforzar conceptos clave como: 

  *  Uso de clases y objetos.
  *  Manejo de colecciones (Map, List).
  *  Control de flujo y excepciones.
  *  Diseño de una interfaz de usuario por consola.
  *  Simulación de un sistema del mundo real con lógica coherente.
     

 
🚀 Futuras Mejoras (ideas) 

    Agregar persistencia con archivos (JSON o CSV).
    Implementar una interfaz gráfica con JavaFX o Swing.
    Simular autenticación biométrica (huella).
    Añadir opciones de préstamo o pago de servicios.
     

 
