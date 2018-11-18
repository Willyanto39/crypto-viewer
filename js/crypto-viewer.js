$(document).ready(function(){
	$("button").click(function(){
		//workaround for CORS issue
		var proxyUrl = 'https://cors-anywhere.herokuapp.com/';

		var cryptoId = $("select option:selected").val();
		var url = 'https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest';
		
		url = proxyUrl + url;

		$.ajax({
			url : url,
			type: 'GET',
			data: {
				id: cryptoId,
				convert: 'IDR'
			},
			headers: {
				'X-CMC_PRO_API_KEY' : config.KEY
			},
			dataType: 'json',

			success: function(data) {
				$(".crypto-name").text(data["data"][cryptoId]["name"]);
				$(".crypto-price").text("Rp. " + data["data"][cryptoId]["quote"]["IDR"]["price"].toLocaleString("id"));
				$(".crypto-market-cap").text("Rp. " + data["data"][cryptoId]["quote"]["IDR"]["market_cap"].toLocaleString("id"));
				$(".crypto-24h-volume").text("Rp. " + data["data"][cryptoId]["quote"]["IDR"]["volume_24h"].toLocaleString("id"));
				$(".circulating-supply").text(data["data"][cryptoId]["circulating_supply"].toLocaleString("id") + " " + data["data"][cryptoId]["symbol"]);
				$(".total-supply").text(data["data"][cryptoId]["total_supply"].toLocaleString("id") + " " + data["data"][cryptoId]["symbol"]);
			},

			fail: function() {
				alert("API call failed");
			}
		});
	});
});
