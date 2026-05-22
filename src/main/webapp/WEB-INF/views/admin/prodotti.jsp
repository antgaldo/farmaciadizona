<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="row">
	<div class="card mb-3 col-md-12 p-5 border-0">
		<div class="col-md-12 pb-5">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
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
			        <form action="/farmaciadizona/admin/prodotti" method="POST">
			          <input type="hidden" name="action" value="addprodotto">
					  <div class="mb-3">
					    <label class="form-label">Nome</label>
					    <input id="nome" class="form-control" type="text" name="nome" placeholder="nome">
					  </div>
					  <div class="mb-3">
					  	<label>descrizione</label>
						<input id="descrizione" type="text" class="form-control" name="descrizione" placeholder="descrizione">
					  </div>
					  <div class="mb-3">
						<label>prezzo</label>
						<input id="prezzo" type="int" class="form-control" name="prezzo" placeholder="prezzo">
					  </div>
					  <div class="mb-3">
						<label>quantita</label>
						<input id="quantita" type="int" class="form-control" name="quantita" placeholder="quantita">
					  </div>
					  <button type="submit" value="submit" class="btn btn-primary">Save changes</button>
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
					<td><i class="bi bi-pencil-square me-1" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal1"></i>
						<i class="bi bi-trash" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal2"></i>
					</td>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        ...
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						        <button type="button" class="btn btn-primary">Save changes</button>
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
