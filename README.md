 # Rockkonzert Simulation

 ## Verwendete Design Patterns

 ### Mediator

 ### Observer

 ### Command

 ## Aufgabenstellung

 > Eine Band gibt ein Konzert in einem Stadion. Das Konzert ist mit 70000 Teilnehmern ausverkauft.
Jeder Teilnehmer besitzt ein Ticket. Auf dem Ticket ist der Bereich und Sektor vermerkt. Zu
Simulationszwecken wird einmalig die Zuordnung von Ticket zu Teilnehmer durchgeführt und in
einer CSV-Datei gespeichert. Das Stadion hat 16 Außenbereiche und einen Innenbereich. Der
Innenbereich hat die Kennung A und die Außenbereiche B-Q. Aus Sicherheitsaspekten ist jeder
Bereich in Sektoren unterteilt. Der Innenbereich hat 10 Sektoren A01-A10. Jeder Außenbereich hat
fünf Sektoren wie beispielsweise B01-B05. Jeder Sektor im Innenbereich bietet ausreichend Platz
für 1000 Menschen. Jeder Sektor im Außenbereich bietet ausreichend Platz für 750 Menschen.
Die Anreise der Teilnehmer erfolgt in drei Phasen: (i) Außenbereich: 50%, Innenbereich: 20%; (ii)
Außenbereich: 80%, Innenbereich: 70%; (iii) Außenbereich: 100%, Innenbereich: 100%. 80% der
Teilnehmer reisen mit dem ÖPNV und 20% mit dem Auto an. Für anreisende Teilnehmer existieren
Wartebereiche (i) 8 für ÖPNV und (ii) 2 für Auto. Zu Simulationszwecken werden einmalig - auf
Grundlage der CSV-Datei mit der Zuordnung - drei CSV-Dateien mit den anreisenden Teilnehmern
pro Phase und eine weitestgehend gleichmäßige Verteilung auf die Wartebereiche generiert. In
jedem Wartebereich existiert eine Anzeige. Es existieren fünf Eingänge in Abhängigkeit von den
Bereichen (i) A (ii) B-D (iii) E-G (iv) H-K (v) L-O und (vi) P, Q. Je Sektor sind die Plätze von vorne
links nach hinten rechts aufsteigend nummeriert. Je Phase wird die korrespondierende CSV-Datei
eingelesen und die Teilnehmer nach folgendem Schema im Wartebereich aufgerufen: Jeder Sektor
(vom ersten bis letzten) für Innen- und Außenbereich werden sukzessive mit den noch nicht
besetzten Plätzen aufgerufen. Nach jeder Phase fliegen Drohnen (eine über Innenbereich und vier
über Außenbereiche) und erfassen die nicht besetzten Plätze. Die Drohnen sind bei einem
zentralen Mediator registriert und werden über die Kommandos (DEPART, COLLECT, NEXT,
LAND) gesteuert. Mit COLLECT wird die Erfassung (Dauer 500ms) gestartet. Mit NEXT wird ggf.
der nächste Bereich erfasst. Der Mediator beobachtet kontinuierlich die Drohnen. Sobald die
Informationen beim Mediator vorliegen, beginnt die nächste Phase mit anreisenden Teilnehmern.
Die Anzeige beobachtet hierzu kontinuierlich den Mediator. Die Wartezeit zwischen zwei Aufrufen
ist 500ms. Mehr als drei aufeinanderfolgende Plätze werden zusammen-gefasst. Beispiel: Phase I
A1[1-1000],B1[1-1000],C11[1-1000]... ** 2 Sekunden Wartezeit ** A2[1-1000],B2[1-1000]...;
Phase II - A1[55,92,212-231]... Die aufgerufenen Teilnehmer begeben sich umgehend zu Ihren
Plätzen.

 ## UML Diagramm

 <insert here>

 ## Intention 
 Das Projekt wurde im Rahmen einer individuellen Leistung als Testat für die Vorlesung Software Engeneering #2 erstellt.
