
<main class="container">
	<h2 class="text-center">Choisissez une ville</h2>
	<div class="mainWindow">
		<form method="post" action="/city_temparature/landing">
			<input type="text" name="cityName" placeholder="ville" id="cityName">
			<button type="submit">Chercher</button>
		</form>
	</div>
	<br/>
	<div class="text-center">
		<c:if test="${not empty search}">
			<strong>Votre recherche: ${search}</strong>
		</c:if>
		<br/>
		<c:if test="${not empty weatherData}">
			<p>Ville: ${weatherData.cityname}</p>
            <p>Temps: ${weatherData.weather}</p>
            <p>Détails: ${weatherData.weatherDetails}</p>
            <p>Températures: ${weatherData.temps} deg C°</p>
            <p>Ressenti: ${weatherData.feltTemp} deg C°</p>
            <p>Vent: ${weatherData.wind} km/h</p>
		</c:if>
	</div>
</main>
