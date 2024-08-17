
<main class="container">
	<c:if test="${not empty errorMessage}">
	<div class='errorMessage'>
		<p>${errorMessage}</p>
	</div>
	</c:if>
	<h2 class="text-center">Choisissez une ville</h2>
	<div class='d-flex-center'>
		<div class="mainWindow">
			<form method="post" action="/city_temparature/">
				<input type="text" name="cityName" placeholder="ville" id="cityName">
				<button type="submit">Chercher</button>
			</form>
			<br/>
			<div class="data-countainer">
				<c:if test="${not empty search}">
					<strong>Votre recherche: </strong><p class="font-size-medium">${search}</p>
				</c:if>
				<br/>
				<c:if test="${not empty weatherData}">
					<p>Point central des données: ${weatherData.cityname}</p>
		            <p>Temps: ${weatherData.weather}</p>
		            <p>Détails: ${weatherData.weatherDetails}</p>
		            <p>Températures: ${weatherData.temps} deg C°</p>
		            <p>Ressenti: ${weatherData.feltTemp} deg C°</p>
		            <p>Vent: ${weatherData.wind} km/h</p>
				</c:if>
			</div>
		</div>
	</div>
</main>
