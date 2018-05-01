$(document).ready(function(){
	$("button").click(function(){
		var cryptoId = $("select option:selected").val();
		var url = "https://api.coinmarketcap.com/v2/ticker/" + cryptoId + "/?convert=IDR";
		
		$.getJSON(url, function(data){
			$(".crypto-name").text(data["data"]["name"]);
			$(".crypto-price").text("Rp. " + data["data"]["quotes"]["IDR"]["price"]);
			$(".crypto-market-cap").text("Rp. " + data["data"]["quotes"]["IDR"]["market_cap"]);
			$(".crypto-24h-volume").text("Rp. " + data["data"]["quotes"]["IDR"]["volume_24h"]);
		});
	});
});
