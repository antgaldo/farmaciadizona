<div>
	<div class="card mb-3 col-md-12 p-5 row">
		<div class="col-md-12 pb-5">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
			  Launch demo modal
			</button>
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <h2>Inserisci prodotto</h2>
					<form action="/farmaciadizona/admin" method="POST">
						<input type="hidden" name="action" value="addprodotto">
						<label>nome</label>
						<input id="nome" type="text" name="nome" placeholder="nome">
						<label>descrizione</label>
						<input id="descrizione" type="text" name="descrizione" placeholder="descrizione">
						<label>prezzo</label>
						<input id="prezzo" type="int" name="prezzo" placeholder="prezzo">
							<label>quantita</label>
						<input id="quantita" type="int" name="quantita" placeholder="quantita">
						<input type="submit" value="submit">
					</form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-primary">Save changes</button>
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
			    </tr>
			  </thead>
			  <tbody>
			    <c:forEach var="p" items="${prodotti}">
		<tr>
		<td>${p.nomeProdotto}</td>
		<td>${p.descrizione}</td>
		<td>${p.prezzo}</td>
		<td>${p.quantita}</td>
		</tr>
		</c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
</div>
