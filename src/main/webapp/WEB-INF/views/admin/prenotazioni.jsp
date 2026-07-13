<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="row">
	<div class="card mb-3 col-md-12 p-5 border-0">
		
		<div class="col-md-12">
			<table class="table">
			    <thead>
			        <tr>
			            <th scope="col">id Ordine</th>
			            <th scope="col">prezzo totale</th>
			            <th scope="col">data di acquisto</th>
			            <th scope="col">Cliente</th>
			            <th scope="col">Dettaglio Prodotti</th>
			        </tr>
			    </thead>
			    <tbody>
			        <c:forEach var="l" items="${prenotazioni}">
			            <tr>
			                <td>${l.ordineId}</td>
			                <td>&euro; ${l.totale}</td>
			                <td>${l.dataAcquisto}</td>
			                <td>${l.nomeUtente}</td>
			                <td>
			                    <table class="table table-sm table-borderless mb-0">
			                        <tbody>
			                            <c:forEach var="o" items="${l.listaOrdini}">
			                                <tr>
			                                    <td>${o.nomeProdotto}</td>
			                                    <td>x${o.quantitaProdotto}</td>
			                                    <td>&euro; ${o.prezzoProdotto}</td>
			                                </tr>
			                            </c:forEach>
			                        </tbody>
			                    </table>
			                </td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
	</div>
</div>
