const userCardTemplate = document.querySelector("[data-user-template]")
const userCardContainer = document.querySelector("[data-user-cards-container]")
const searchInput = document.querySelector("[data-search]")

let users = []



searchInput.addEventListener("input", e => {
    const value = e.target.value.toLowerCase()
    users.forEach(user => {
        const isVisible = user.semester.toLowerCase().includes(value)
        user.element.classList.toggle("hide", !isVisible)
    })
   
})
fetch("http://87.162.240.81:8888/api/v1/REST/vorlesung")
.then (res => res.json())
.then(data => {
    users = data.map(user => {
        const card = userCardTemplate.content.cloneNode(true).children[0]
        //const link = card.querySelector("[data-link]")
        const header = card.querySelector("[data-header]")
        const body = card.querySelector("[data-body]")
        card.link = user.vorlesungsplan
        //link.src = user.vorlesungsplan
        header.textContent = user.semester
        body.textContent = user.studiengang
        userCardContainer.append(card)
        return { vorlesungsplan: user.vorlesungsplan, semester: user.semester, studiengang: user.studiengang, element : card}
    })
})

function anklicken(e){
   const iframe = document.getElementById("fenster")
   iframe.src = e.link
}
