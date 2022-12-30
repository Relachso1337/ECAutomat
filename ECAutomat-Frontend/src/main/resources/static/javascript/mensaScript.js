setTimeout(function() {
    var container = document.getElementById("maincontainer");
    container.classList.remove("slideOut");
    void container.offsetWidth;
    container.classList.add("slideOut");
    setTimeout(function() {
        window.location.replace("../html/newsticker.html");
    }, 1400);
    
}, 5000);