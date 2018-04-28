$(document).ready(function(){
	$("button").click(function(){
		var selectedCrypto = $("select option:selected").text();
		var url = "https://api.coinmarketcap.com/v1/ticker/" + selectedCrypto + "/?convert=IDR";
		
		$.getJSON(url, function(data){
			$(".crypto-name").text(data[0]["name"]);
			$(".crypto-price").text("Rp. " + data[0]["price_idr"]);
			$(".crypto-market-cap").text("Rp. " + data[0]["market_cap_idr"]);
			$(".crypto-24h-volume").text("Rp. " + data[0]["24h_volume_idr"]);
		});
	});
});
