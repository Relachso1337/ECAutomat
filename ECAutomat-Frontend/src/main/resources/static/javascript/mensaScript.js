
fetch('http://127.0.0.1/news/scrapeNews')
.then((response) => response.json())
.then((data) => console.log(data));




setTimeout(function() {
    setTimeout(function() {
        window.location.replace("/news");
    }, 1400);
    
}, 180000);