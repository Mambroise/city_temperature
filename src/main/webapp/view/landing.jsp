
<main class="container">
	<div class="mainWindow">
		<form method="post" action="/city_temparature/landing">
			<label>Donnez le nom d'une ville :</label>
			<input type="text" name="cityName" placeholder="ville" id="cityName">
			<button type="submit">Chercher</button>
		</form>
	</div>
	<div class="">
		<c:if test="${not empty weatherData}">
            <p>${weatherData.coord.lat}</p>
		</c:if>
	</div>
</main>
