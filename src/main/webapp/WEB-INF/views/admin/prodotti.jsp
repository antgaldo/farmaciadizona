<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="row">
	<div class="card mb-3 col-md-12 p-5 border-0">
		<div class="col-md-12 pb-5">
			<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
			  Inserisci prodotto
			</button>
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">Inserisci prodotto</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <form action="${pageContext.request.contextPath}/admin/prodotti" method="POST" enctype="multipart/form-data">
			          <input type="hidden" name="action" value="addprodotto">
					  <div class="mb-3">
					    <label class="form-label">Nome</label>
					    <input id="nome" class="form-control" type="text" name="nome" placeholder="nome">
					  </div>
					  <div class="mb-3">
					  	<label>descrizione</label>
						<textarea id="descrizione" type="text" class="form-control" name="descrizione" placeholder="descrizione"></textarea>
					  </div>
					  <div class="mb-3">
						<label>prezzo</label>
						<input id="prezzo" type="int" class="form-control" name="prezzo" placeholder="prezzo">
					  </div>
					  <div class="mb-3">
						<label>quantita</label>
						<input id="quantita" type="int" class="form-control" name="quantita" placeholder="quantita">
					  </div>
					  <div class="mb-3">
						<label>immagine</label>
						<input type="file" id="img" name="img" id="img" class="form-control" accept="image/*"/>
					  </div>
					  <div class="mb-3">
						<label>categoria</label>
						<input id="categoria" type="text" class="form-control" name="categoria" placeholder="categoria">
					  </div>
					  <button type="submit" value="submit" class="btn btn-success">Salva prodotto</button>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
		<div class="col-md-12">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">nome prodotto</th>
			      <th scope="col">descrizione</th>
			      <th scope="col">prezzo</th>
			      <th scope="col">quantità</th>
			      <th scope="col">categoria</th>
			      <th scope="col">immagine</th>
			      <th scope="col">azioni</th>
			    </tr>
			  </thead>
			  <tbody>
			    <c:forEach var="p" items="${prodotti}">
					<tr>
					<td>${p.nomeProdotto}</td>
					<td>${p.descrizione}</td>
					<td>${p.prezzo}</td>
					<td>${p.quantita}</td>
					<td>${p.categoria}</td>
					<td><img width="60" height="60" src="${pageContext.request.contextPath}/uploads/${p.pathImg}"></td>
					<td>
					    <i class="bi bi-pencil-square me-1"
					       role="button"
					       data-bs-toggle="modal"
					       data-bs-target="#exampleModal1"></i>
					
					    <i class="bi bi-trash"
					       role="button"
					       data-bs-toggle="modal"
					       data-bs-target="#exampleModal2"></i>
					</td>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Modifica</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						         <form action="${pageContext.request.contextPath}/admin/prodotti" method="POST">
						          <input type="hidden" name="action" value="editprodotto">
						          <input type="hidden" name="id" value="${p.idProdotto}">
								  <div class="mb-3">
								    <label class="form-label">Nome</label>
								    <input id="nome" class="form-control" type="text" name="nome" placeholder="nome" disabled value="${p.nomeProdotto}">
								  </div>
								  <div class="mb-3">
								  	<label>descrizione</label>
									<textarea id="descrizione" type="text" class="form-control" name="descrizione" placeholder="descrizione">${p.descrizione}</textarea>
								  </div>
								  <div class="mb-3">
									<label>prezzo</label>
									<input id="prezzo" type="int" class="form-control" name="prezzo" placeholder="prezzo" value="${p.prezzo}">
								  </div>
								  <div class="mb-3">
									<label>quantita</label>
									<input id="quantita" type="int" class="form-control" name="quantita" placeholder="quantita" value="${p.quantita}">
								  </div>
								  <div class="mb-3">
									<label>categoria</label>
									<input id="categoria" type="text" class="form-control" name="categoria" placeholder="categoria" disabled value="${p.categoria}">
								  </div>
								  <button type="submit" value="submit" class="btn btn-primary">Salva</button>
								</form>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
						      </div>
						    </div>
						  </div>
						</div>
						<!-- Modal -->
						<!-- Modal -->
						<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Sicuro vuoi eliminare ?</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <form action="/farmaciadizona/admin/prodotti" method="POST">
						        	<input type="hidden" name="action" value="deletevendeprodotto">
								    <input type="hidden" name="idProdotto" value="${p.idProdotto}">
								    <button type="submit" class="btn btn-secondary">
								        Elimina
								    </button>
								</form>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						      </div>
						    </div>
						  </div>
						</div>
						<!-- Modal -->
					</tr>
				</c:forEach>
			</tbody>
		  </table>
		</div>
	</div>
</div>
