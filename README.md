[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/r1iBXMxX)

In proiect dezvolt un API care permite gestionarea alertelor de sistem.

Am 3 clase pentru utilizatori: User, Operator si Admin.
Operator extinde User si Admin extinde Operator.
UserCreation creeaza dinamic tipul de utilizator (dintre acestia 3) in fucntie de rol si clearenceLevel.
In plus, se asigura ca numele si rolul nu pot fi goale.

Server contine date despre locatie, owner si resurse.
Acesta are si un mecanism de notificare pentru alerte (setListener si notifyListener).

Location retine informatii despre locatie si arunca LocationException daca nu e completata tara.

ResourceGroup tine o lista de membrii si are operatiile de add, remove si has member.
In plus, implementeaza si AlertListener pentru a primi alerte de la server.

Alert reprezinta o alerta cu tip, severitate, mesaj si ipaddress.

Database este baza de date a aplicatiei.
Are servers, resourceGroups si alerts si are operatii de add si cautari dupa adresa ip.
Reset reseteaza instanta pentru a porni ok la fiecare rulare.

Command este o interfata si cu ajutorul ei fac comenzile care o implementeaza.
Comenzi: - AddServer = construieste un server cu validari si il adauga in database.
        - AddGroup = creeaza un resource group si il adauga in database.
        - FindGroup = cauta grupul dupa ip si afiseaza rezultatul.
        - RemoveGroup = sterge grupul dupa ip si afiseaza rezultatul.
        - AddMember = adauga utilizator in grupul tinta indentificat prin ip
        - FindMember = cauta membrul in grupul tinta
        - RemoveMember = elimina membrul din grupul tinta
        - AddEvent = creeaza Alert si o adauga in database, apoi notifica serverul

Mainul citeste fisierele .in, parseaza liniile, construieste si executa comenzile.

Design patterns:
    - Singleton in Database = cerinta spune ca trebuie o implementare care previne instantierea multipla a bazei de date.
        In plus, asa toate comenzile lucreaza cu aceeasi stare globala - serverele, grupurile, alertele
    - Command (interfata Command si clasele care sunt pentru comenzi) = deoarece inputul vine sub forma de comenzi.
        Asa fiecare comanda este izolata intr o clasa separata, fiind mai usor sa adaugi comenzi noi.
    - Builder pentru construirea obiectelor din Server = asa evit constructorii foarte mari si codul e mai clar
    - Factory facand crearea dinamica a utilizatorilor = deoarece pot aparea tipuri diferite de utilizatori.
        Astfel, logica din spatele lor este intr un singur loc.
    - Listener = cerinta spune ca serverele notifica grupurile conecatate la aparitia unei alerte.
        Asa serverul doar apeleaz onAlert si nu trebuie sa aiba alte detalii de cum se trateaza alerta.
