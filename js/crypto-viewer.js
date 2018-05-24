$(document).ready(function(){
	$("button").click(function(){
		var cryptoId = $("select option:selected").val();
		var url = "https://api.coinmarketcap.com/v2/ticker/" + cryptoId + "/?convert=IDR";
		
		$.getJSON(url, function(data){
			$(".crypto-name").text(data["data"]["name"]);
			$(".crypto-price").text("Rp. " + data["data"]["quotes"]["IDR"]["price"].toLocaleString("id"));
			$(".crypto-market-cap").text("Rp. " + data["data"]["quotes"]["IDR"]["market_cap"].toLocaleString("id"));
			$(".crypto-24h-volume").text("Rp. " + data["data"]["quotes"]["IDR"]["volume_24h"].toLocaleString("id"));
			$(".circulating-supply").text(data["data"]["circulating_supply"].toLocaleString("id") + " " + data["data"]["symbol"]);
			$(".total-supply").text(data["data"]["total_supply"].toLocaleString("id") + " " + data["data"]["symbol"]);
		});
	});
});
