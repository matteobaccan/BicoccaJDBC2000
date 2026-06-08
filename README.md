# Java Flavour Day — JDBC @ Bicocca 2000

Materiale didattico preparato per il **Java Flavour Day** tenuto all'Università degli Studi di Milano-Bicocca il **24 novembre 2000**.

Il repository contiene le slide della presentazione e una serie di esempi pratici su **JDBC (Java Database Connectivity)**, dal collegamento base tramite ponte JDBC-ODBC fino alle funzionalità avanzate di JDBC 3 (connection pooling, savepoint, cached rowset).

---

## Struttura del repository

```
BicoccaJDBC2000/
├── bicocca.ppt          # Presentazione PowerPoint originale
├── bicocca.pdf          # Versione PDF della presentazione (27 pagine)
└── esempi/
    ├── esempio1/        # Esempio base: connessione JDBC-ODBC e SELECT
    ├── jdbc3/           # Novità JDBC 3: connection pool e savepoint
    ├── query/           # Tool grafico (AWT) per eseguire query arbitrarie
    ├── jsp/             # Esempio web: query JDBC via pagina JSP
    └── ottimizzaioni/   # Tecniche di ottimizzazione: PreparedStatement e CachedRowSet
```

---

## Esempi

### esempio1 — Connessione base JDBC-ODBC

Carica il driver `sun.jdbc.odbc.JdbcOdbcDriver`, apre una connessione verso un datasource ODBC locale ed esegue una semplice `SELECT` sui primi 10 clienti.

```bash
cd esempi/esempio1
compile.BAT   # javac esempio1.java
run1.BAT      # java esempio1
```

### query — Query tool grafico (AWT)

Applicazione desktop che permette di inserire driver, stringa di connessione, credenziali e una query SQL arbitraria. I risultati vengono visualizzati con intestazioni di colonna e righe separate da tabulazione.

```bash
cd esempi/query
compile.BAT    # javac frmQuery.java
runQuery.BAT   # javaw frmQuery
```

### jsp — Query via browser

Pagina `index.html` con un form che invia la query a `select.jsp`, la quale esegue la `SELECT` tramite JDBC-ODBC e restituisce i risultati in HTML.

Richiede un servlet container (es. Tomcat) configurato con un datasource ODBC.

### jdbc3 — Funzionalità JDBC 3

| File | Argomento |
|------|-----------|
| `esempio2.java` | Connection pool con `ConnectionPoolDS` e JNDI |
| `savepoint.txt` | Gestione dei savepoint (`setSavepoint` / `rollback`) |

### ottimizzaioni — Tecniche di ottimizzazione

| File | Argomento |
|------|-----------|
| `prepared.txt` | `PreparedStatement` con parametri `?` per prestazioni e sicurezza |
| `cacherowset.txt` | `CachedRowSet` per tenere i dati in memoria ed evitare query ripetute |

---

## Tecnologie

- **Java** (JDK ~1.3, anno 2000)
- **JDBC** — API standard per l'accesso ai database relazionali
- **JDBC-ODBC Bridge** — `sun.jdbc.odbc.JdbcOdbcDriver`
- **JDBC 3** — Connection pooling, `DataSource`, `Savepoint`
- **AWT** — toolkit grafico per la GUI del query tool
- **JSP** — JavaServer Pages per l'esempio web
- **JNDI** — registro dei datasource nel contesto applicativo

---

## Compilazione e requisiti

Gli esempi richiedono un **JDK 1.3** o superiore. Non è presente un sistema di build moderno: la compilazione avviene tramite i file `.BAT` inclusi in ogni sottodirectory.

```bat
javac NomeClasse.java
java  NomeClasse
```

> **Nota:** il ponte JDBC-ODBC (`sun.jdbc.odbc`) è stato rimosso a partire da **Java 8**. Per eseguire gli esempi con JDK moderni occorre sostituirlo con un driver JDBC nativo.

---

## Licenza

Distribuito sotto licenza **GNU General Public License v3.0** — vedi il file [LICENSE](LICENSE) per i dettagli.

---

## Autore

**Matteo Baccan** — [matteo@baccan.it](mailto:matteo@baccan.it) — [https://www.baccan.it](https://www.baccan.it)
