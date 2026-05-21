<div>
	<div class="card mb-3 col-md-6">
	  <div class="row g-0">
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
</div>
<script>
function initDashboardPage() {

    const ctx = document.getElementById("myChart");

    if (!ctx || typeof Chart === "undefined") return;

    new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          borderWidth: 1
        }]
      },
      options: {
    		animations: {
    		     tension: {
    		       duration: 1000,
    		       easing: 'linear',
    		       from: 1,
    		       to: 0,
    		       loop: true
    		     }
    		   },
    	   scales: {
    	         y: { // defining min and max so hiding the dataset does not change scale range
    	           min: 0,
    	           max: 100
    	         }
    	       }
      }
    });
}
</script>