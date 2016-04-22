# language: es
 Característica: Tipos de elecciones
   Escenario: Acceso a Tipos Elecciones: Listas Abiertas
     Cuando el cliente entra en la web "/listasAbiertas.xhtml"
     Entonces el cliente ve la tabla de con las respectivas elecciones de tipo "Listas Abiertas"
     Y el cliente elije aquella que sea de su interés 
     
   Escenario: Acceso a Tipos Elecciones: Listas Cerradas
     Cuando el cliente entra en la web "/listaCerradas.xhtml"
     Entonces el cliente ve la tabla de con las respectivas elecciones de tipo "Listas Cerradas"
     Y el cliente elije aquella que sea de su interés
     
    Escenario: Acceso a Tipos Elecciones: Referendum
     Cuando el cliente entra en la web "/listaReferendum.xhtml"
     Entonces el cliente ve la tabla de con las respectivas elecciones de tipo "Referendum"
     Y el cliente elije aquella que sea de su interés