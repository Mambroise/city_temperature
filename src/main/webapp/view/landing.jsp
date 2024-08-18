
<main class="container">
	<c:if test="${not empty errorMessage}">
	<div class='errorMessage'>
		<p>${errorMessage}</p>
	</div>
	</c:if>
	<h2 class="text-center">Choose a location</h2>
	<div class='d-flex-center'>
		<div class="mainWindow">
			<form method="post" action="/city_temparature/">
				<input type="text" name="cityName" placeholder="location" id="cityName" required>
				<button type="submit">Search</button>
			</form>
			<br/>
			<div class="data-countainer">
				<c:if test="${not empty search}">
					<strong>Your result: </strong><p class="font-size-medium">${search}</p>
				</c:if>
				<br/>
				<c:if test="${not empty weatherData}">
					<p>Data center point: ${weatherData.cityname}</p>
		            <p>Weather: ${weatherData.weather}</p>
		            <p>Details: ${weatherData.weatherDetails}</p>
		            <p>Temperatures: ${weatherData.temps} deg C°</p>
		            <p>Felt: ${weatherData.feltTemp} deg C°</p>
		            <p>Wind: ${weatherData.wind} km/h</p>
				</c:if>
			</div>
		</div>
	</div>
</main>
