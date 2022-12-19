const userCardTemplate = document.querySelector("[data-user-template]")
const userCardContainer = document.querySelector("[data-user-cards-container]")
const searchInput = document.querySelector("[data-search]")

let users = []

searchInput.addEventListener("input", e => {
    const value = e.target.value.toLowerCase()
    users.forEach(user => {
        const isVisible = user.name.toLowerCase().includes(value) || user.email.toLowerCase().includes(value)
        user.element.classList.toggle("hide", !isVisible)
    })
   
})
// http://localhost:8888/api/v1/REST/vorlesung Link zu dem JSON File
fetch("http://localhost:8888/api/v1/REST/vorlesung")
.then (res => res.json())
.then(data => {
    users = data.map(user => {
        const card = userCardTemplate.content.cloneNode(true).children[0]
        const link = card.querySelector("[data-link]")
        const header = card.querySelector("[data-header]")
        const body = card.querySelector("[data-body]")
        link.href = user.vorlesungsplan
        header.textContent = user.semester
        body.textContent = user.studiengang
        userCardContainer.append(card)
        return { vorlesungsplan: user.vorlesungsplan, semester: user.semester, studiengang: user.studiengang, element : card}
    })
})