
fetch('http://127.0.0.1/news/scrapeNews')
.then((response) => response.json())
.then((data) => console.log(data));




setTimeout(function() {
    var container = document.getElementById("maincontainer");
    container.classList.remove("slideOut");
    void container.offsetWidth;
    container.classList.add("slideOut");
    setTimeout(function() {
        window.location.replace("/news");
    }, 1400);
    
}, 180000);