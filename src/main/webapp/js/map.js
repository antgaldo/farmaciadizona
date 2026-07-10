   const mapElement = document.getElementById('map');	
	// Recupera la stringa JSON dal dataset e la converte in array
  const listaMappa = JSON.parse(mapElement.dataset.locations || '[]');
  const map = L.map('map').setView([listaMappa[0].lat, listaMappa[0].lon], 9.5)
  L.maplibreGL({
    style: 'https://tiles.openfreemap.org/styles/liberty',
  }).addTo(map);
  
  //aggiungiamo i marcatori alla mappa effettiva
  const markerGroup = L.featureGroup().addTo(map);
  var myIcon = L.divIcon({
	  html: '<i class="bi bi-geo-alt-fill text-success fs-2"></i>',
      className: 'bg-transparent', 
      iconSize: [30, 30],
      iconAnchor: [15, 30], 
      popupAnchor: [0, -30]
	});
	
  //mettiamo i marcatori in una lista per hover
  const mappaMarcatori = {};	
  listaMappa.forEach(f => {
      const marker = L.marker([parseFloat(f.lat), parseFloat(f.lon)], {icon: myIcon,opacity: 0.5})
      .bindPopup('<b>' + f.farmaciaNome + '</b><br>' + f.indirizzo);
	  marker.addTo(markerGroup);
	  mappaMarcatori[f.id] = marker;
   });
   
   //catturo l'evento per cambio opacità marcatore
   function evidenziaMarcatore(idFarmacia) {
      const marcatore = mappaMarcatori[idFarmacia]; 
      if (marcatore) {
         marcatore.setOpacity(1.0); 
         //marcatore.bringToFront();  
      }
   }

   function ripristinaMarcatore(idFarmacia) {
      const marcatore = mappaMarcatori[idFarmacia];
      
      if (marcatore) {
         marcatore.setOpacity(0.5);
      }
   }
   
  //settaggio di base della mappa
  if (listaMappa.length > 0) {
      map.fitBounds(markerGroup.getBounds(), {
          padding: [50, 50], 
          maxZoom: 15        
      });
  } else {
      map.setView([41.9028, 12.4964], 6); 
  }