🏥 Farmacia di Zona
Farmacia di Zona è un'applicazione web progettata per individuare facilmente le farmacie più vicine, consultare gli orari di apertura e verificare le farmacie di turno nella propria area geografica.
🚀 Caratteristiche Principali
📍 Geolocalizzazione in Tempo Reale: Individua la posizione dell'utente e mostra le farmacie nelle immediate vicinanze.
🕒 Farmacie di Turno & Orari: Consulta i servizi attivi, inclusi i turni notturni e festivi.
🗺️ Mappa Interattiva: Visualizzazione chiara con marker e schede informative per ciascun punto d'interesse.
🔍 Ricerca e Filtri: Cerca per indirizzo, città o CAP e filtra i risultati in base alle tue esigenze.
📞 Contatti Diretti: Chiama la farmacia o avvia le indicazioni stradali con un solo click.
🛠️ Tecnologie Utilizzate
Frontend: React.js / JavaScript (ES6+)
Stili: Tailwind CSS / CSS3
Mappe: Leaflet / OpenStreetMap
Dati: Open Data / API Ministero della Salute
💻 Installazione e Avvio Locale
Segui questi passaggi per eseguire il progetto sul tuo computer:
Prerequisiti
Node.js (v16.x o superiore)
npm oppure yarn
Passaggi
Clona il repository:
git clone https://github.com/antgaldo/farmaciadizona.git
cd farmaciadizona


Installa le dipendenze:
npm install
# oppure
yarn install


Configura le variabili d'ambiente (se necessario):
Crea un file .env nella root del progetto prendendo spunto da .env.example:
VITE_API_URL=https://api.example.com


Avvia il server di sviluppo:
npm run dev
# oppure
npm start


Apri il browser:
Naviga su http://localhost:5173 (o la porta indicata nel terminale).
📂 Struttura del Progetto
farmaciadizona/
├── public/              # Asset statici
├── src/
│   ├── assets/          # Immagini, icone e stili globali
│   ├── components/      # Componenti UI (Mappa, Card, Navbar, Filtri)
│   ├── services/        # Gestione API e recupero dati
│   ├── utils/           # Funzioni di utilità (calcolo distanze, formattazione)
│   ├── App.jsx          # Componente principale
│   └── main.jsx         # Entry point dell'applicazione
├── .gitignore
├── package.json
└── README.md


🤝 Contribuire
Le contribuzioni, le segnalazioni di bug e i suggerimenti sono i benvenuti!
Fai il Fork del repository.
Crea un branch per la tua funzionalità (git checkout -b feature/NuovaFunzionalita).
Salva le modifiche (git commit -m 'Aggiunta nuova funzionalità').
Invia i cambiamenti al branch (git push origin feature/NuovaFunzionalita).
Apri una Pull Request.
📄 Licenza
Questo progetto è distribuito sotto licenza MIT.
✉️ Autore & Contatti
Sviluppato da Antonio Galdo (@antgaldo).
Per domande, feedback o segnalazioni, apri un'Issue su GitHub.
