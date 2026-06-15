<div class="row">
	<div class="card mb-3 col p-5 border-0">
	  <div class="row">
	    <div class="col-md-6">
	      <canvas id="myChart"></canvas>
	    </div>
	    <div class="col-md-6">
	      <div class="card-body">
	        <h5 class="card-title">Card title</h5>
	        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
	        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="card mb-3 col ms-2 p-5 ">
	  <div class="row g-0">
	    <div class="col-md-2">
	      <h1 class="card-body bottom-50 end-50">${nprodotti}</h1>
	    </div>
	    <div class="col-md-10">
	      <div class="card-body">
	        <h5 class="card-title">Prodotti attivi</h5>
	        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
	        <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
	      </div>
	    </div>
	  </div>
	</div>
</div>
<script>
function initDashboardPage() {

    const ctx = document.getElementById("myChart");

    if (!ctx || typeof Chart === "undefined") return;
    
    new Chart(ctx, {
	  type: 'bar',
	  data: {
		  labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange','Black'],
		  datasets: [{
		    label: 'My First Dataset',
		    data: [65, 59, 80, 81, 56, 55, 40],
		    backgroundColor: [
		      'rgba(255, 99, 132, 0.2)',
		      'rgba(255, 159, 64, 0.2)',
		      'rgba(255, 205, 86, 0.2)',
		      'rgba(75, 192, 192, 0.2)',
		      'rgba(54, 162, 235, 0.2)',
		      'rgba(153, 102, 255, 0.2)',
		      'rgba(201, 203, 207, 0.2)'
		    ],
		    borderColor: [
		      'rgb(255, 99, 132)',
		      'rgb(255, 159, 64)',
		      'rgb(255, 205, 86)',
		      'rgb(75, 192, 192)',
		      'rgb(54, 162, 235)',
		      'rgb(153, 102, 255)',
		      'rgb(201, 203, 207)'
		    ],
		    borderWidth: 1
		  }]
	  },
    });
}
</script>